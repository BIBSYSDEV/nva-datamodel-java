package no.unit.nva;

import static no.unit.nva.DatamodelConfig.dataModelObjectMapper;
import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValues;
import static no.unit.nva.model.PublicationStatus.DRAFT;
import static no.unit.nva.model.PublicationStatus.NEW;
import static no.unit.nva.model.PublicationStatus.PUBLISHED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.AdditionalIdentifier;
import no.unit.nva.model.ModelTest;
import no.unit.nva.model.Publication;
import no.unit.nva.model.PublicationStatus;
import no.unit.nva.model.exceptions.InvalidPublicationStatusTransitionException;
import no.unit.nva.model.testing.PublicationGenerator;
import no.unit.nva.model.testing.PublicationInstanceBuilder;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

public class PublicationTest extends ModelTest {
    
    public static final String TIMESTAMP_REGEX = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]+";
    public static final String SOME_TIMESTAMP = "2020-09-23T09:51:23.044996Z";
    public static final String DOCUMENTATION_PATH_TEMPLATE = "../documentation/%s.json";
    public static final SortableIdentifier REPLACEMENT_IDENTIFIER_1 =
        new SortableIdentifier("c443030e-9d56-43d8-afd1-8c89105af555");
    public static final Javers JAVERS = JaversBuilder.javers().build();
    
    public static Stream<Class<?>> publicationInstanceProvider() {
        return PublicationInstanceBuilder.listPublicationInstanceTypes().stream();
    }

    @ParameterizedTest(name = "Test that publication with InstanceType {0} can be round-tripped to and from JSON")
    @MethodSource("publicationInstanceProvider")
    void publicationReturnsValidPublicationWhenInputIsValid(Class<?> instanceType) throws Exception {
        Publication expected = PublicationGenerator.randomPublication(instanceType);
        
        String publication = dataModelObjectMapper.writeValueAsString(expected);
        Publication roundTripped = dataModelObjectMapper.readValue(publication, Publication.class);
        Diff diff = JAVERS.compare(expected, roundTripped);
        assertThatPublicationDoesNotHaveEmptyFields(expected);
        assertEquals(expected, roundTripped);
        assertThat(diff.prettyPrint(), roundTripped, is(equalTo(expected)));
        
        writePublicationToFile(instanceType, expected);
    }
    
    @ParameterizedTest(name = "Test that publication with InstanceType {0} can be copied without loss of data")
    @MethodSource("publicationInstanceProvider")
    void copyReturnsBuilderWithAllDataOfAPublication(Class<?> referenceInstanceType) {
        Publication publication = PublicationGenerator.randomPublication(referenceInstanceType);
        Publication copy = publication.copy().build();
        assertThatPublicationDoesNotHaveEmptyFields(publication);
        Diff diff = compareAsObjectNodes(publication, copy);
        assertThat(diff.prettyPrint(), copy, is(equalTo(publication)));
        assertThat(copy, is(not(sameInstance(publication))));
    }
    
    @ParameterizedTest(name = "Test that publication with InstanceType {0} can be round-tripped to and from JSON")
    @MethodSource("publicationInstanceProvider")
    void projectsAreSetAsListsWhenInputIsSingleProject(Class<?> instanceType) {
        Publication expected = PublicationGenerator.randomPublication(instanceType);
        assertThat(expected.getProjects(), instanceOf(List.class));
    }
    
    @ParameterizedTest
    @EnumSource(value = PublicationStatus.class, names = {"DRAFT_FOR_DELETION", "PUBLISHED"})
    void updateStatusForDraftPublication(PublicationStatus target) throws Exception {
        Publication publication = PublicationGenerator.randomPublication();
        publication.setStatus(DRAFT);
        publication.updateStatus(target);
        
        assertThat(publication.getStatus(), is(equalTo(target)));
    }
    
    @Test
    void updateStatusThrowsExceptionForInvalidStatusTransition() {
        Publication publication = PublicationGenerator.randomPublication();
        publication.setStatus(NEW);
        
        InvalidPublicationStatusTransitionException exception =
            assertThrows(InvalidPublicationStatusTransitionException.class, () -> publication.updateStatus(PUBLISHED));
        
        String expectedError = String.format(InvalidPublicationStatusTransitionException.ERROR_MSG_TEMPLATE,
            NEW, PUBLISHED);
        assertThat(exception.getMessage(), is(equalTo(expectedError)));
    }
    
    // This test is included because of a bizarre error.
    @Test
    void initializingPublicationShouldNotThrowException() {
        assertDoesNotThrow(Publication::new);
    }
    
    private void assertThatPublicationDoesNotHaveEmptyFields(Publication expected) {
        assertThat(expected, doesNotHaveEmptyValues());
    }
    
    private Diff compareAsObjectNodes(Publication publication, Publication copy) {
        var publicationObjectNode = dataModelObjectMapper.convertValue(publication, ObjectNode.class);
        var copyObjectNode = dataModelObjectMapper.convertValue(copy, ObjectNode.class);
        return JAVERS.compare(publicationObjectNode, copyObjectNode);
    }
    
    private void writePublicationToFile(Class<?> instanceType, Publication publication) throws IOException {
        publication.setIdentifier(REPLACEMENT_IDENTIFIER_1);
        publication.setAdditionalIdentifiers(Set.of(new AdditionalIdentifier("fakesource", "1234")));
        String path = String.format(DOCUMENTATION_PATH_TEMPLATE, instanceType.getSimpleName());
        var publicationJson = dataModelObjectMapper.writeValueAsString(publication)
                                  .replaceAll(TIMESTAMP_REGEX, SOME_TIMESTAMP);
        Files.write(Paths.get(path), publicationJson.getBytes());
    }
}

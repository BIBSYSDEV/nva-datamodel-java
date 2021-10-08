package no.unit.nva;

import static no.unit.nva.DatamodelConfig.objectMapper;
import static no.unit.nva.model.PublicationStatus.DRAFT;
import static no.unit.nva.model.PublicationStatus.NEW;
import static no.unit.nva.model.PublicationStatus.PUBLISHED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import no.unit.nva.hamcrest.DoesNotHaveEmptyValues;
import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.AdditionalIdentifier;
import no.unit.nva.model.File;
import no.unit.nva.model.ModelTest;
import no.unit.nva.model.Publication;
import no.unit.nva.model.PublicationStatus;
import no.unit.nva.model.exceptions.InvalidPublicationStatusTransitionException;
import no.unit.nva.model.util.PublicationGenerator;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EnumSource;
import provider.InstanceTypeProvider;

public class PublicationTest extends ModelTest {

    public static final String TIMESTAMP_REGEX = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]+";
    public static final String SOME_TIMESTAMP = "2020-09-23T09:51:23.044996Z";
    public static final String DOCUMENTATION_PATH_TEMPLATE = "../documentation/%s.json";
    public static final SortableIdentifier REPLACEMENT_IDENTIFIER_1 =
        new SortableIdentifier("c443030e-9d56-43d8-afd1-8c89105af555");
    public static final UUID REPLACEMENT_IDENTIFIER_2 = UUID.fromString("5032710d-a326-43d3-a8fb-57a451873c78");
    public static final String JOURNAL_ARTICLE = "JournalArticle";
    public static final Javers JAVERS = JaversBuilder.javers().build();

    @ParameterizedTest(name = "Test that publication with InstanceType {0} can be round-tripped to and from JSON")
    @ArgumentsSource(InstanceTypeProvider.class)
    void publicationReturnsValidPublicationWhenInputIsValid(String instanceType) throws Exception {
        Publication expected = PublicationGenerator.generatePublication(instanceType);

        String publication = objectMapper.writeValueAsString(expected);
        Publication roundTripped = objectMapper.readValue(publication, Publication.class);
        Diff diff = JAVERS.compare(expected, roundTripped);
        assertThat(expected, DoesNotHaveEmptyValues.doesNotHaveEmptyValues());
        assertEquals(expected, roundTripped);
        assertThat(diff.prettyPrint(), roundTripped, is(equalTo(expected)));

        writePublicationToFile(instanceType, expected);
    }

    @ParameterizedTest(name = "Test that publication with InstanceType {0} can be copied without loss of data")
    @ArgumentsSource(InstanceTypeProvider.class)
    void copyReturnsBuilderWithAllDataOfAPublication(String referenceInstanceType) throws Exception {
        Publication publication = PublicationGenerator.generatePublication(referenceInstanceType);
        Publication copy = publication.copy().build();
        assertThat(publication, DoesNotHaveEmptyValues.doesNotHaveEmptyValues());
        Diff diff = JAVERS.compare(publication, copy);
        assertThat(diff.prettyPrint(), copy, is(equalTo(publication)));
        assertThat(copy, is(not(sameInstance(publication))));
    }

    @ParameterizedTest(name = "Test that publication with InstanceType {0} can be round-tripped to and from JSON")
    @ArgumentsSource(InstanceTypeProvider.class)
    void projectsAreSetAsListsWhenInputIsSingleProject(String instanceType) throws Exception {
        Publication expected = PublicationGenerator.generatePublication(instanceType);
        assertThat(expected.getProjects(), instanceOf(List.class));
    }

    @ParameterizedTest
    @EnumSource(value = PublicationStatus.class, names = {"DRAFT_FOR_DELETION", "PUBLISHED"})
    void updateStatusForDraftPublication(PublicationStatus target) throws Exception {
        Publication publication = PublicationGenerator.generatePublication(JOURNAL_ARTICLE);
        publication.setStatus(DRAFT);
        publication.updateStatus(target);

        assertThat(publication.getStatus(), is(equalTo(target)));
    }

    @Test
    void updateStatusThrowsExceptionForInvalidStatusTransition() throws Exception {
        Publication publication = PublicationGenerator.generatePublication(JOURNAL_ARTICLE);
        publication.setStatus(NEW);

        InvalidPublicationStatusTransitionException exception =
            assertThrows(InvalidPublicationStatusTransitionException.class, () -> publication.updateStatus(PUBLISHED));

        String expectedError = String.format(InvalidPublicationStatusTransitionException.ERROR_MSG_TEMPLATE,
                                             NEW, PUBLISHED);
        assertThat(exception.getMessage(), is(equalTo(expectedError)));
    }

    private void writePublicationToFile(String instanceType, Publication publication) throws IOException {
        publication.setIdentifier(REPLACEMENT_IDENTIFIER_1);
        publication.setAdditionalIdentifiers(Set.of(new AdditionalIdentifier("fakesource", "1234")));
        publication.getFileSet().getFiles().forEach(file -> publication.getFileSet()
                                                                .setFiles(List.of(copyWithNewIdentifier(file))));
        String path = String.format(DOCUMENTATION_PATH_TEMPLATE, instanceType);
        var publicationJson = objectMapper.writeValueAsString(publication)
                                  .replaceAll(TIMESTAMP_REGEX, SOME_TIMESTAMP);
        Files.write(Paths.get(path), publicationJson.getBytes());
    }

    private File copyWithNewIdentifier(File file) {
        return new File(PublicationTest.REPLACEMENT_IDENTIFIER_2,
                        file.getName(),
                        file.getMimeType(),
                        file.getSize(),
                        file.getLicense(),
                        file.isAdministrativeAgreement(),
                        file.isPublisherAuthority(),
                        file.getEmbargoDate());
    }
}

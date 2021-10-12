package no.unit.nva;

import static no.unit.nva.DatamodelConfig.dataModelObjectMapper;
import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValues;
import static no.unit.nva.model.PublicationStatus.DRAFT;
import static no.unit.nva.model.PublicationStatus.NEW;
import static no.unit.nva.model.PublicationStatus.PUBLISHED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;
import java.util.stream.Collectors;
import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.AdditionalIdentifier;
import no.unit.nva.model.File;
import no.unit.nva.model.ModelTest;
import no.unit.nva.model.Publication;
import no.unit.nva.model.PublicationStatus;
import no.unit.nva.model.Reference;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.exceptions.InvalidPublicationStatusTransitionException;
import no.unit.nva.model.exceptions.InvalidUnconfirmedSeriesException;
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
    public static final String PUBLICATION_CONTEXT_TYPE_JSON_POINTER =
        "/entityDescription/reference/publicationContext/type";
    public static final String PUBLICATION_INSTANCE_TYPE_JSON_POINTER =
        "/entityDescription/reference/publicationInstance/type";

    @ParameterizedTest(name = "Test that publication with InstanceType {0} can be round-tripped to and from JSON")
    @ArgumentsSource(InstanceTypeProvider.class)
    void publicationReturnsValidPublicationWhenInputIsValid(String instanceType) throws Exception {
        Publication expected = PublicationGenerator.generatePublication(instanceType);

        String publication = dataModelObjectMapper.writeValueAsString(expected);
        Publication roundTripped = dataModelObjectMapper.readValue(publication, Publication.class);
        Diff diff = JAVERS.compare(expected, roundTripped);
        assertThat(expected, doesNotHaveEmptyValues());
        assertEquals(expected, roundTripped);
        assertThat(diff.prettyPrint(), roundTripped, is(equalTo(expected)));

        writePublicationToFile(instanceType, expected);
    }

    @ParameterizedTest(name = "Test that publication with InstanceType {0} can be copied without loss of data")
    @ArgumentsSource(InstanceTypeProvider.class)
    void copyReturnsBuilderWithAllDataOfAPublication(String referenceInstanceType) throws Exception {
        Publication publication = PublicationGenerator.generatePublication(referenceInstanceType);
        Publication copy = publication.copy().build();
        assertThat(publication, doesNotHaveEmptyValues());
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

    @Test
    void publicationSerializationReturnsPublicationContainingNonNullCompositeObjects()
        throws InvalidIssnException, InvalidIsbnException, InvalidUnconfirmedSeriesException, JsonProcessingException {
        String[] presentFieldsOfNonEmptyPublication = expectedPresentFieldsInAllPublications();

        Publication emptyPublication = new Publication();
        ObjectNode serializedEmptyPublication = toObjectNode(emptyPublication);
        ArrayList<String> presentFieldsOfEmptyPublication = expectedFieldNames(emptyPublication);

        final String publicationContextType = serializedEmptyPublication.at(PUBLICATION_CONTEXT_TYPE_JSON_POINTER)
            .textValue();
        final String publicationInstanceType = serializedEmptyPublication.at(PUBLICATION_INSTANCE_TYPE_JSON_POINTER)
            .textValue();

        assertThat(presentFieldsOfEmptyPublication, containsInAnyOrder(presentFieldsOfNonEmptyPublication));
        assertThat(publicationContextType, is(equalTo("EmptyPublicationContext")));

        assertThat(publicationInstanceType, is(equalTo("EmptyPublicationInstance")));
    }

    private String[] expectedPresentFieldsInAllPublications()
        throws InvalidIssnException, InvalidIsbnException, InvalidUnconfirmedSeriesException, JsonProcessingException {
        Publication initialPublication = PublicationGenerator.generatePublication(JOURNAL_ARTICLE);
        assertThat(initialPublication, doesNotHaveEmptyValues());
        return expectedFieldNames(initialPublication).toArray(String[]::new);
    }

    private ObjectNode toObjectNode(Publication publication) throws JsonProcessingException {
        String jsonString = dataModelObjectMapper.writeValueAsString(publication);
        return dataModelObjectMapper.readValue(jsonString, ObjectNode.class);
    }

    private ArrayList<String> expectedFieldNames(Publication publication) throws JsonProcessingException {
        ObjectNode json = toObjectNode(publication);
        return expectedFieldNames(json);
    }

    private ArrayList<String> expectedFieldNames(ObjectNode json) {
        Stack<JsonNode> unvisitedNodes = new Stack<>();
        ArrayList<String> fieldNames = new ArrayList<>();
        unvisitedNodes.push(json);
        while (!unvisitedNodes.isEmpty()) {
            JsonNode currentNode = unvisitedNodes.pop();
            if (currentNode.isObject()) {
                List<JsonNode> children = children(currentNode);
                unvisitedNodes.addAll(children);
                fieldNames.addAll(childrenFieldNames(currentNode));
            }
        }
        return fieldNames;
    }

    private List<String> childrenFieldNames(JsonNode currentNode) {
        return ImmutableList.copyOf(currentNode.fields())
            .stream()
            .filter(this::nodeShouldBeExpanded)
            .map(Entry::getKey)
            .collect(Collectors.toList());
    }

    private List<JsonNode> children(JsonNode currentNode) {
        return ImmutableList.copyOf(currentNode.fields())
            .stream()
            .filter(this::nodeShouldBeExpanded)
            .map(Entry::getValue)
            .collect(Collectors.toList());
    }

    private boolean nodeShouldBeExpanded(Entry<String, JsonNode> child) {
        return !(
            Reference.PUBLICATION_CONTEXT.equals(child.getKey())
            || Reference.PUBLICATION_INSTANCE.equals(child.getKey())
            || "publisher".equals(child.getKey())
            || "fileSet".equals(child.getKey())
            || "additionalIdentifiers".equals(child.getKey())
            || "doiRequest".equals(child.getKey())
            || "alternativeTitles".equals(child.getKey())
        );
    }

    private void writePublicationToFile(String instanceType, Publication publication) throws IOException {
        publication.setIdentifier(REPLACEMENT_IDENTIFIER_1);
        publication.setAdditionalIdentifiers(Set.of(new AdditionalIdentifier("fakesource", "1234")));
        publication.getFileSet().getFiles().forEach(file -> publication.getFileSet()
            .setFiles(List.of(copyWithNewIdentifier(file))));
        String path = String.format(DOCUMENTATION_PATH_TEMPLATE, instanceType);
        var publicationJson = dataModelObjectMapper.writeValueAsString(publication)
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

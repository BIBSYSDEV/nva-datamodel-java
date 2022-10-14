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
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import no.unit.nva.file.model.File;
import no.unit.nva.file.model.FileSet;
import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.AdditionalIdentifier;
import no.unit.nva.model.ModelTest;
import no.unit.nva.model.Publication;
import no.unit.nva.model.PublicationStatus;
import no.unit.nva.model.associatedartifacts.AssociatedArtifact;
import no.unit.nva.model.associatedartifacts.AssociatedFile;
import no.unit.nva.model.exceptions.InvalidPublicationStatusTransitionException;
import no.unit.nva.model.instancetypes.book.BookMonograph;
import no.unit.nva.model.testing.FileSetGenerator;
import no.unit.nva.model.testing.PublicationGenerator;
import no.unit.nva.model.testing.PublicationInstanceBuilder;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

public class PublicationTest extends ModelTest {

    public static final String TIMESTAMP_REGEX = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]+";
    public static final String SOME_TIMESTAMP = "2020-09-23T09:51:23.044996Z";
    public static final String DOCUMENTATION_PATH_TEMPLATE = "../documentation/%s.json";
    public static final SortableIdentifier REPLACEMENT_IDENTIFIER_1 =
        new SortableIdentifier("c443030e-9d56-43d8-afd1-8c89105af555");
    public static final UUID REPLACEMENT_IDENTIFIER_2 = UUID.fromString("5032710d-a326-43d3-a8fb-57a451873c78");
    public static final Javers JAVERS = JaversBuilder.javers().build();

    public static Stream<Class<?>> publicationInstanceProvider() {
        return PublicationInstanceBuilder.listPublicationInstanceTypes().stream();
    }

    public static Stream<FileSet> nullAndEmptyFileSetProvider() {
        return Stream.of(null, new FileSet(null), new FileSet(Collections.emptyList()));
    }

    // Test is temporary
    @Test
    void shouldDeserializeJsonWhenInputIsExpandedResource() {
        var input = "{\n" +
                "  \"type\" : \"Publication\",\n" +
                "  \"publicationContextUris\" : [ \"https://api.dev.nva.aws.unit.no/publication-channels/Lp7vxLEIJzSBaQWGQ\", \"https://api.dev.nva.aws.unit.no/publication-channels/ElWcyuT0H0OJlU\" ],\n" +
                "  \"@context\" : {\n" +
                "    \"@vocab\" : \"https://bibsysdev.github.io/src/nva/ontology.ttl#\",\n" +
                "    \"id\" : \"@id\",\n" +
                "    \"type\" : \"@type\",\n" +
                "    \"contributors\" : {\n" +
                "      \"@container\" : \"@set\"\n" +
                "    },\n" +
                "    \"additionalIdentifiers\" : {\n" +
                "      \"@container\" : \"@set\"\n" +
                "    },\n" +
                "    \"affiliations\" : {\n" +
                "      \"@container\" : \"@set\"\n" +
                "    },\n" +
                "    \"subjects\" : {\n" +
                "      \"@container\" : \"@set\"\n" +
                "    },\n" +
                "    \"projects\" : {\n" +
                "      \"@container\" : \"@set\"\n" +
                "    },\n" +
                "    \"tags\" : {\n" +
                "      \"@container\" : \"@set\"\n" +
                "    },\n" +
                "    \"isbnList\" : {\n" +
                "      \"@container\" : \"@set\"\n" +
                "    },\n" +
                "    \"venues\" : {\n" +
                "      \"@container\" : \"@set\"\n" +
                "    },\n" +
                "    \"files\" : {\n" +
                "      \"@container\" : \"@set\"\n" +
                "    },\n" +
                "    \"associatedArtifacts\" : {\n" +
                "      \"@container\" : \"@set\"\n" +
                "    },\n" +

                "    \"grants\" : {\n" +
                "      \"@container\" : \"@set\"\n" +
                "    },\n" +
                "    \"approvals\" : {\n" +
                "      \"@container\" : \"@set\"\n" +
                "    },\n" +
                "    \"messages\" : {\n" +
                "      \"@container\" : \"@set\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"id\" : \"https://api.dev.nva.aws.unit.no/publication/0183cc58e155-607479b9-fc23-4a2f-8992-213971e4693e\",\n" +
                "  \"additionalIdentifiers\" : [ {\n" +
                "    \"type\" : \"AdditionalIdentifier\",\n" +
                "    \"source\" : \"VINFyvkAse60jQ8d3\",\n" +
                "    \"value\" : \"TFHmuCBaOzST7XPfU6t\"\n" +
                "  } ],\n" +
                "  \"associatedArtifacts\" : [{\n" +
                "    \"type\" : \"UnpublishableFile\",\n" +
                "    \"administrativeAgreement\" : true,\n" +
                "    \"embargoDate\" : \"1997-04-03T14:44:15.970Z\",\n" +
                "    \"identifier\" : \"3b4edaff-b382-47a9-a035-4fd82b61a4cf\",\n" +
                "    \"license\" : {\n" +
                "      \"type\" : \"License\",\n" +
                "      \"identifier\" : \"4v5Gp22sKYMe3P\",\n" +
                "      \"labels\" : {\n" +
                "        \"ZsXuQTRx0kuhV0o3TUA\" : \"1bEYBhi1mRNN7cl0\"\n" +
                "      },\n" +
                "      \"link\" : \"https://www.example.com/9g42CyFOFO7Xhox\"\n" +
                "    },\n" +
                "    \"mimeType\" : \"JhRX1QbSfK\",\n" +
                "    \"name\" : \"PkelhSQGtn17\",\n" +
                "    \"publisherAuthority\" : false,\n" +
                "    \"size\" : 939788541\n" +
                "  }],\n" +
                "  \"createdDate\" : \"2022-10-12T13:18:40.462531Z\",\n" +
                "  \"entityDescription\" : {\n" +
                "    \"type\" : \"EntityDescription\",\n" +
                "    \"abstract\" : \"pkTN4MgZZBuDEhtAmk\",\n" +
                "    \"alternativeTitles\" : {\n" +
                "      \"pt\" : \"f7AbcnGmD1n2mohU\"\n" +
                "    },\n" +
                "    \"contributors\" : [ {\n" +
                "      \"type\" : \"Contributor\",\n" +
                "      \"affiliations\" : [ {\n" +
                "        \"id\" : \"https://www.example.com/6tBalQo2pvrfHsjWsbA\",\n" +
                "        \"type\" : \"Organization\",\n" +
                "        \"labels\" : {\n" +
                "          \"ZW15fnMo4TQvmj\" : \"qYeO0BHcOU4\"\n" +
                "        }\n" +
                "      } ],\n" +
                "      \"correspondingAuthor\" : false,\n" +
                "      \"identity\" : {\n" +
                "        \"id\" : \"https://www.example.com/ymvgS6CEa7UDh1\",\n" +
                "        \"type\" : \"Identity\",\n" +
                "        \"name\" : \"qMc60s4idZSrC\",\n" +
                "        \"nameType\" : \"Personal\",\n" +
                "        \"orcId\" : \"ioWZf70cy5sKHNzr\"\n" +
                "      },\n" +
                "      \"role\" : \"ArtisticDirector\",\n" +
                "      \"sequence\" : 5\n" +
                "    }, {\n" +
                "      \"type\" : \"Contributor\",\n" +
                "      \"affiliations\" : [ {\n" +
                "        \"id\" : \"https://www.example.com/l3kPx92RKu\",\n" +
                "        \"type\" : \"Organization\",\n" +
                "        \"labels\" : {\n" +
                "          \"gAD1WtRde0OC5jR\" : \"2OaO5F6FcWjd8\"\n" +
                "        }\n" +
                "      } ],\n" +
                "      \"correspondingAuthor\" : false,\n" +
                "      \"identity\" : {\n" +
                "        \"id\" : \"https://www.example.com/cRdwOkSPrxXyeGuAb\",\n" +
                "        \"type\" : \"Identity\",\n" +
                "        \"name\" : \"CzmsupvXFo\",\n" +
                "        \"nameType\" : \"Personal\",\n" +
                "        \"orcId\" : \"fTqFAWue54FqRiYLHG\"\n" +
                "      },\n" +
                "      \"role\" : \"WorkPackageLeader\",\n" +
                "      \"sequence\" : 6\n" +
                "    } ],\n" +
                "    \"date\" : {\n" +
                "      \"type\" : \"PublicationDate\",\n" +
                "      \"day\" : \"XGWg4mmNszbC3ap\",\n" +
                "      \"month\" : \"nKX7BPK8vXbwEP978e\",\n" +
                "      \"year\" : \"elXorCjtz5FuuQV\"\n" +
                "    },\n" +
                "    \"description\" : \"IdBtubvBAyS3\",\n" +
                "    \"language\" : \"http://lexvo.org/id/iso639-3/swe\",\n" +
                "    \"mainTitle\" : \"k2FIoR5dHj55WQu\",\n" +
                "    \"metadataSource\" : \"https://www.example.com/b0NmMNLqU6v39vN4uUS\",\n" +
                "    \"npiSubjectHeading\" : \"2Vc7kjyoB1Npt\",\n" +
                "    \"reference\" : {\n" +
                "      \"type\" : \"Reference\",\n" +
                "      \"doi\" : \"https://www.example.com/7VKECK6dHcYM\",\n" +
                "      \"publicationContext\" : {\n" +
                "        \"type\" : \"Degree\",\n" +
                "        \"isbnList\" : [ \"9780836298208\" ],\n" +
                "        \"publisher\" : {\n" +
                "          \"id\" : \"https://api.dev.nva.aws.unit.no/publication-channels/Lp7vxLEIJzSBaQWGQ\",\n" +
                "          \"type\" : \"Publisher\"\n" +
                "        },\n" +
                "        \"series\" : {\n" +
                "          \"id\" : \"https://api.dev.nva.aws.unit.no/publication-channels/ElWcyuT0H0OJlU\",\n" +
                "          \"type\" : \"Series\"\n" +
                "        },\n" +
                "        \"seriesNumber\" : \"UpQoFbRi1qhtGKMc\"\n" +
                "      },\n" +
                "      \"publicationInstance\" : {\n" +
                "        \"type\" : \"DegreeMaster\",\n" +
                "        \"pages\" : {\n" +
                "          \"type\" : \"MonographPages\",\n" +
                "          \"illustrated\" : false,\n" +
                "          \"introduction\" : {\n" +
                "            \"type\" : \"Range\",\n" +
                "            \"begin\" : \"0pmtBZnKPdJEBiGo\",\n" +
                "            \"end\" : \"iAo6God6fgscL\"\n" +
                "          },\n" +
                "          \"pages\" : \"WYsintzSXqhCOxZaVtm\"\n" +
                "        },\n" +
                "        \"peerReviewed\" : false,\n" +
                "        \"submittedDate\" : {\n" +
                "          \"type\" : \"PublicationDate\",\n" +
                "          \"day\" : \"6GYsQGz765\",\n" +
                "          \"month\" : \"gTOE7zZKKN4uCphJQA\",\n" +
                "          \"year\" : \"U1ixuoDBgWtRampJa\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"tags\" : [ \"1hKZDpNzUiz\" ]\n" +
                "  },\n" +
                "  \"fileSet\" : {\n" +
                "    \"type\" : \"FileSet\",\n" +
                "    \"files\" : [ {\n" +
                "      \"type\" : \"UnpublishableFile\",\n" +
                "      \"administrativeAgreement\" : true,\n" +
                "      \"embargoDate\" : \"1997-04-03T14:44:15.970Z\",\n" +
                "      \"identifier\" : \"3b4edaff-b382-47a9-a035-4fd82b61a4cf\",\n" +
                "      \"license\" : {\n" +
                "        \"type\" : \"License\",\n" +
                "        \"identifier\" : \"4v5Gp22sKYMe3P\",\n" +
                "        \"labels\" : {\n" +
                "          \"ZsXuQTRx0kuhV0o3TUA\" : \"1bEYBhi1mRNN7cl0\"\n" +
                "        },\n" +
                "        \"link\" : \"https://www.example.com/9g42CyFOFO7Xhox\"\n" +
                "      },\n" +
                "      \"mimeType\" : \"JhRX1QbSfK\",\n" +
                "      \"name\" : \"PkelhSQGtn17\",\n" +
                "      \"publisherAuthority\" : false,\n" +
                "      \"size\" : 939788541\n" +
                "    } ]\n" +
                "  },\n" +
                "  \"handle\" : \"https://www.example.org/autquia\",\n" +
                "  \"identifier\" : \"0183cc58e155-607479b9-fc23-4a2f-8992-213971e4693e\",\n" +
                "  \"indexedDate\" : \"1990-11-04T01:51:40.923Z\",\n" +
                "  \"link\" : \"https://www.example.org/aitaque\",\n" +
                "  \"modelVersion\" : \"0.18.7\",\n" +
                "  \"modifiedDate\" : \"2022-10-12T13:18:40.462531Z\",\n" +
                "  \"owner\" : \"3y1R5CyXfTgqIH\",\n" +
                "  \"projects\" : [ {\n" +
                "    \"id\" : \"https://www.example.org/ina\",\n" +
                "    \"type\" : \"ResearchProject\",\n" +
                "    \"approvals\" : [ {\n" +
                "      \"type\" : \"Approval\",\n" +
                "      \"applicationCode\" : \"JqFzhGtQpI3Ej\",\n" +
                "      \"approvalStatus\" : \"NOTAPPLIED\",\n" +
                "      \"approvedBy\" : \"DIRHEALTH\",\n" +
                "      \"date\" : \"2006-10-23T11:05:41.592Z\"\n" +
                "    } ],\n" +
                "    \"grants\" : [ {\n" +
                "      \"id\" : \"Su2DpagQIpOvwhGem0Y\",\n" +
                "      \"type\" : \"Grant\",\n" +
                "      \"source\" : \"AeHE5Drj34CK1zyk\"\n" +
                "    } ],\n" +
                "    \"name\" : \"APL51XCBeU5AuTFTC\"\n" +
                "  } ],\n" +
                "  \"publishedDate\" : \"2008-07-21T10:02:04.456Z\",\n" +
                "  \"publisher\" : {\n" +
                "    \"id\" : \"https://www.example.org/aliquamvoluptate\",\n" +
                "    \"type\" : \"Organization\",\n" +
                "    \"labels\" : { }\n" +
                "  },\n" +
                "  \"resourceOwner\" : {\n" +
                "    \"owner\" : \"3y1R5CyXfTgqIH\",\n" +
                "    \"ownerAffiliation\" : \"https://www.example.org/fugaut\"\n" +
                "  },\n" +
                "  \"status\" : \"DRAFT\",\n" +
                "  \"subjects\" : [ \"https://www.example.org/estaut\" ]\n" +
                "}";
        assertDoesNotThrow(() -> dataModelObjectMapper.readValue(input, Publication.class));
    }

    // Test is temporary and will be deleted
    @ParameterizedTest
    @MethodSource("nullAndEmptyFileSetProvider")
    void shouldProduceEmptyListForAssociatedArtifactWhenFileSetIsEmpty(FileSet fileSet) {
        var publication = PublicationGenerator.randomPublication();
        publication.setFileSet(fileSet);
        assertThat(publication.getAssociatedArtifacts(), is(empty()));
    }

    // Test is temporary and will be deleted

    @Test
    void shouldRemoveFileWhenFileIsRemovedFromFileSet() {
        var publication = PublicationGenerator.randomPublication();
        var files = new ArrayList<>(List.copyOf(publication.getFileSet().getFiles()));
        var initialSize = files.size();
        var fileToBeRemoved = files.get(0);
        files.remove(fileToBeRemoved);
        assertThat(files.size(), is(lessThan(initialSize)));
        publication.setFileSet(new FileSet(files));
        var updated = publication.getFileSet().getFiles();
        assertThat(updated, not(containsInAnyOrder(fileToBeRemoved)));
        List<File> associatedArtifacts = toFileSet(publication.getAssociatedArtifacts());
        assertThat(associatedArtifacts, is(equalTo(updated)));
    }

    // Test is temporary and will be deleted
    @Test
    void publicationShouldPresentFilesInAssociatedArtifacts() {
        var publication = PublicationGenerator.randomPublication(BookMonograph.class);
        var files = publication.getFileSet().getFiles();
        var fileArtifacts = toFileSet(publication.getAssociatedArtifacts());
        assertThat(fileArtifacts, is(equalTo(files)));
    }

    private static List<File> toFileSet(Collection<AssociatedArtifact> artifacts) {
        return artifacts.stream()
            .filter(f -> f instanceof AssociatedFile).map(f -> (File) f)
            .collect(Collectors.toList());
    }

    // Test is temporary and will be deleted
    @Test
    void publicationShouldSetAssociatedArtifactsWhenFilesIsSet() {
        var publication = PublicationGenerator.randomPublication(BookMonograph.class);
        publication.setFileSet(FileSetGenerator.randomFileSet());
        var associatedArtifacts = publication.getAssociatedArtifacts().stream()
                .filter(f -> f instanceof AssociatedFile)
                .collect(Collectors.toList());
        assertThat(toFileSet(associatedArtifacts), is(equalTo(publication.getFileSet().getFiles())));
    }

    // Test is temporary and will be deleted
    @Test
    void shouldSetFileSetWhenAssociatedFilesIsSet() {
        var publication = PublicationGenerator.randomPublication(BookMonograph.class);
        publication.setFileSet(new FileSet(Collections.emptyList()));
        assertThat(toFileSet(publication.getAssociatedArtifacts()), is(equalTo(publication.getFileSet().getFiles())));
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
        publication.getFileSet().getFiles().forEach(file -> publication
            .setFileSet(new FileSet(List.of(copyWithNewIdentifier(file)))));
        String path = String.format(DOCUMENTATION_PATH_TEMPLATE, instanceType.getSimpleName());
        var publicationJson = dataModelObjectMapper.writeValueAsString(publication)
            .replaceAll(TIMESTAMP_REGEX, SOME_TIMESTAMP);
        Files.write(Paths.get(path), publicationJson.getBytes());
    }

    private File copyWithNewIdentifier(File file) {
        return new File(file.getType(),
                        PublicationTest.REPLACEMENT_IDENTIFIER_2,
                        file.getName(),
                        file.getMimeType(),
                        file.getSize(),
                        file.getLicense(),
                        file.isAdministrativeAgreement(),
                        file.isPublisherAuthority(),
                        file.getEmbargoDate().orElse(null));
    }
}

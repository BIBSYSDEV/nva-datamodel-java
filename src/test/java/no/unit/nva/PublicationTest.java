package no.unit.nva;

import static no.unit.nva.hamcrest.DoesNotHaveNullOrEmptyFields.doesNotHaveNullOrEmptyFields;
import static no.unit.nva.model.PublicationStatus.DRAFT;
import static no.unit.nva.model.PublicationStatus.NEW;
import static no.unit.nva.model.PublicationStatus.PUBLISHED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.File;
import no.unit.nva.model.ModelTest;
import no.unit.nva.model.Publication;
import no.unit.nva.model.PublicationStatus;
import no.unit.nva.model.Reference;
import no.unit.nva.model.exceptions.InvalidPublicationStatusTransitionException;
import nva.commons.core.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EnumSource;
import provider.InstanceTypeProvider;

public class PublicationTest extends ModelTest {

    public static final String TIMESTAMP_REGEX = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]+";
    public static final String SOME_TIMESTAMP = "2020-09-23T09:51:23.044996Z";
    public static final String DOCUMENTATION_PATH_TEMPLATE = "documentation/%s.json";
    public static final SortableIdentifier REPLACEMENT_IDENTIFIER_1 =
        new SortableIdentifier("c443030e-9d56-43d8-afd1-8c89105af555");
    public static final UUID REPLACEMENT_IDENTIFIER_2 = UUID.fromString("5032710d-a326-43d3-a8fb-57a451873c78");
    public static final String JOURNAL_ARTICLE = "JournalArticle";
    ObjectMapper objectMapper = JsonUtils.objectMapper;


    @DisplayName("Test that each publication type can be round-tripped to and from JSON")
    @ParameterizedTest(name = "Test that publication with InstanceType {0} can be round-tripped to and from JSON")
    @ArgumentsSource(InstanceTypeProvider.class)
    void publicationReturnsValidPublicationWhenInputIsValid(String instanceType) throws Exception {
        Publication expected = generatePublication(instanceType);

        String publication = objectMapper.writeValueAsString(expected);
        Publication roundTripped = objectMapper.readValue(publication, Publication.class);
        assertThat(expected, doesNotHaveNullOrEmptyFields());
        assertThat(roundTripped, is(equalTo(expected)));

        writePublicationToFile(instanceType, expected);
    }

    @DisplayName("copy returns a builder with all data of a publication")
    @ParameterizedTest(name = "Test that publication with InstanceType {0} can be copied without loss of data")
    @ArgumentsSource(InstanceTypeProvider.class)
    void copyReturnsBuilderWithAllDataOfAPublication(String referenceInstanceType) throws Exception {
        Publication publication = generatePublication(referenceInstanceType);
        Publication copy = publication.copy().build();
        assertThat(publication, doesNotHaveNullOrEmptyFields());
        assertThat(copy, is(equalTo(publication)));
        assertThat(copy, is(not(sameInstance(publication))));
    }

    @DisplayName("Test that each publication type can be round-tripped to and from JSON")
    @ParameterizedTest(name = "Test that publication with InstanceType {0} can be round-tripped to and from JSON")
    @ArgumentsSource(InstanceTypeProvider.class)
    void projectsAreSetAsListsWhenInputIsSingleProject(String instanceType) throws Exception {
        Publication expected = generatePublication(instanceType);
        assertThat(expected.getProjects(), instanceOf(List.class));
    }

    @ParameterizedTest
    @EnumSource(value = PublicationStatus.class, names = {"DRAFT_FOR_DELETION", "PUBLISHED"})
    void updateStatusForDraftPublication(PublicationStatus target) throws Exception {
        Publication publication = generatePublication(JOURNAL_ARTICLE);
        publication.setStatus(DRAFT);
        publication.updateStatus(target);

        assertThat(publication.getStatus(), is(equalTo(target)));
    }

    @Test
    void updateStatusThrowsExceptionForInvalidStatusTransition() throws Exception {
        Publication publication = generatePublication(JOURNAL_ARTICLE);
        publication.setStatus(NEW);

        InvalidPublicationStatusTransitionException exception =
            assertThrows(InvalidPublicationStatusTransitionException.class,
                () -> publication.updateStatus(PUBLISHED));

        String expectedError = String.format(InvalidPublicationStatusTransitionException.ERROR_MSG_TEMPLATE,
            NEW, PUBLISHED);
        assertThat(exception.getMessage(), is(equalTo(expectedError)));
    }

    private Publication generatePublication(String instanceType) throws Exception {
        Reference reference = generateReference(instanceType);
        Instant now = Instant.now();

        return new Publication.Builder()
            .withCreatedDate(now)
            .withDoi(URI.create("https://example.org/yet/another/fake/doi/1231/12311"))
            .withDoiRequest(generateDoiRequest(now))
            .withEntityDescription(generateEntityDescription(reference))
            .withFileSet(generateFileSet())
            .withHandle(URI.create("https://example.org/fakeHandle/13213"))
            .withIdentifier(SortableIdentifier.next())
            .withIndexedDate(now)
            .withLink(URI.create("https://this.should.have.been.removed"))
            .withModifiedDate(now)
            .withOwner("me@example.org")
            .withProjects(generateProject())
            .withPublishedDate(now)
            .withPublisher(generateOrganization())
            .withStatus(PUBLISHED)
            .build();
    }

    private Reference generateReference(String instanceType) throws Exception {
        Reference reference;
        switch (instanceType) {
            case "BookAbstracts":
                reference = generateBookAbstracts();
                break;
            case "BookAnthology":
                reference = generateBookAnthology();
                break;
            case "BookMonograph":
                reference = generateBookMonograph();
                break;
            case "CartographicMap":
                reference = generateCartographicMap();
                break;
            case "ChapterArticle":
                reference = generateChapterArticle();
                break;
            case "DegreeBachelor":
                reference = generateDegreeBachelor();
                break;
            case "DegreeMaster":
                reference = generateDegreeMaster();
                break;
            case "DegreePhd":
                reference = generateDegreePhd();
                break;
            case "FeatureArticle":
                reference = generateFeatureArticle();
                break;
            case "JournalArticle":
                reference = generateJournalArticle();
                break;
            case "JournalCorrigendum":
                reference = generateJournalCorrigendum();
                break;
            case "JournalInterview":
                reference = generateJournalInterview();
                break;
            case "JournalLeader":
                reference = generateJournalLeader();
                break;
            case "JournalLetter":
                reference = generateJournalLetter();
                break;
            case "JournalReview":
                reference = generateJournalReview();
                break;
            case "JournalShortCommunication":
                reference = generateJournalShortCommunication();
                break;
            case "MusicNotation":
                reference = generateMusicNotation();
                break;
            case "OtherStudentWork":
                reference = generateOtherStudentWork();
                break;
            case "ReportBasic":
                reference = generateReportBasic();
                break;
            case "ReportPolicy":
                reference = generateReportPolicy();
                break;
            case "ReportResearch":
                reference = generateReportResearch();
                break;
            case "ReportWorkingPaper":
                reference = generateReportWorkingPaper();
                break;
            default:
                throw new Exception("Unknown instanceType");
        }
        return reference;
    }

    private void writePublicationToFile(String instanceType, Publication publication) throws IOException {
        publication.setIdentifier(REPLACEMENT_IDENTIFIER_1);
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

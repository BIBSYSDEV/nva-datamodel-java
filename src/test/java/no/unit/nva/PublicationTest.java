package no.unit.nva;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.File;
import no.unit.nva.model.ModelTest;
import no.unit.nva.model.Publication;
import no.unit.nva.model.PublicationStatus;
import no.unit.nva.model.Reference;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static no.unit.nva.hamcrest.DoesNotHaveNullOrEmptyFields.doesNotHaveNullOrEmptyFields;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

public class PublicationTest extends ModelTest {

    public static final String TIMESTAMP_REGEX = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]+";
    public static final String SOME_TIMESTAMP = "2020-09-23T09:51:23.044996Z";
    public static final String DOCUMENTATION_PATH_TEMPLATE = "documentation/%s.json";
    public static final UUID REPLACEMENT_IDENTIFIER_1 = UUID.fromString("c443030e-9d56-43d8-afd1-8c89105af555");
    public static final UUID REPLACEMENT_IDENTIFIER_2 = UUID.fromString("5032710d-a326-43d3-a8fb-57a451873c78");
    ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("Test that each publication type can be round-tripped to and from JSON")
    @ParameterizedTest(name = "Test that publication with InstanceType {0} can be round-tripped to and from JSON")
    @CsvSource({
            "BookAnthology",
            "BookMonograph",
            "CartographicMap",
            "ChapterArticle",
            "DegreeBachelor",
            "DegreeMaster",
            "DegreePhd",
            "FeatureArticle",
            "JournalArticle",
            "JournalCorrigendum",
            "JournalLeader",
            "JournalLetter",
            "JournalReview",
            "JournalShortCommunication",
            "MusicNotation",
            "OtherStudentWork",
            "OtherArticle",
            "ReportPolicy",
            "ReportResearch",
            "ReportWorkingPaper"
    })
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
    @CsvSource({
            "BookAnthology",
            "BookMonograph",
            "CartographicMap",
            "ChapterArticle",
            "DegreeBachelor",
            "DegreeMaster",
            "DegreePhd",
            "FeatureArticle",
            "JournalArticle",
            "JournalCorrigendum",
            "JournalLeader",
            "JournalLetter",
            "JournalReview",
            "JournalShortCommunication",
            "MusicNotation",
            "OtherStudentWork",
            "OtherArticle",
            "ReportPolicy",
            "ReportResearch",
            "ReportWorkingPaper"
    })
    void copyReturnsBuilderWithAllDataOfAPublication(String referenceInstanceType) throws Exception {
        Publication publication = generatePublication(referenceInstanceType);
        Publication copy = publication.copy().build();
        assertThat(publication, doesNotHaveNullOrEmptyFields());
        assertThat(copy, is(equalTo(publication)));
        assertThat(copy, is(not(sameInstance(publication))));

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
                .withIdentifier(UUID.randomUUID())
                .withIndexedDate(now)
                .withLink(URI.create("https://this.should.have.been.removed"))
                .withModifiedDate(now)
                .withOwner("me@example.org")
                .withProject(generateProject())
                .withPublishedDate(now)
                .withPublisher(generateOrganization())
                .withStatus(PublicationStatus.PUBLISHED)
                .build();
    }

    private Reference generateReference(String instanceType) throws Exception {
        Reference reference;
        switch (instanceType) {
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
            case "OtherArticle":
                reference = generateOtherArticleInstance();
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

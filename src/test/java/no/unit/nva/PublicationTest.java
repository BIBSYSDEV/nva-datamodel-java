package no.unit.nva;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.Publication;
import no.unit.nva.model.PublicationStatus;
import no.unit.nva.model.Reference;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.net.URI;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PublicationTest extends ModelTest {

    ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("Test that each publication type can be round-tripped to and from JSON")
    @ParameterizedTest(name = "Test that publication with InstanceType {0} can be round-tripped to and from JSON")
    @CsvSource({
            "BookAnthology",
            "BookMonograph",
            "ChapterArticle",
            "DegreeBachelor",
            "DegreeMaster",
            "DegreePhd",
            "JournalArticle",
            "JournalLeader",
            "JournalLetter",
            "JournalReview",
            "JournalShortCommunication",
            "ReportPolicy",
            "ReportResearch",
            "ReportWorkingPaper"
    })
    void publicationReturnsValidPublicationWhenInputIsValid(String instanceType) throws Exception {
        Reference reference;
        switch (instanceType) {
            case "BookAnthology":
                reference = generateBookAnthology();
                break;
            case "BookMonograph":
                reference = generateBookMonograph();
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
            case "JournalArticle":
                reference = generateJournalArticle();
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

        Publication actual = new Publication.Builder()
                .withCreatedDate(Instant.now())
                .withDoi(URI.create("https://example.org/yet/another/fake/doi/1231/12311"))
                .withDoiRequest(generateDoiRequest())
                .withEntityDescription(generateEntityDescription(reference))
                .withFileSet(generateFileSet())
                .withHandle(URI.create("https://example.org/fakeHandle/13213"))
                .withIdentifier(UUID.randomUUID())
                .withIndexedDate(Instant.now())
                .withLink(URI.create("https://this.should.have.been.removed"))
                .withModifiedDate(Instant.now())
                .withOwner("me@example.org")
                .withProject(generateProject())
                .withPublishedDate(Instant.now())
                .withPublisher(generateOrganization())
                .withStatus(PublicationStatus.PUBLISHED)
                .build();

        String publication = objectMapper.writeValueAsString(actual);
        Publication roundTripped = objectMapper.readValue(publication, Publication.class);
        assertNotNull(roundTripped);
    }
}

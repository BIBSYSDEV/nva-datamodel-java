package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.netmikey.logunit.api.LogCapturer;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Range;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportResearchTest extends InstanceTest {

    @RegisterExtension
    LogCapturer logs = LogCapturer.create().captureForType(NonPeerReviewedMonograph.class);

    @DisplayName("Report research can be created from JSON")
    @Test
    void reportResearchReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageRangeException {
        ReportResearch expected = generateReportResearch("42", "1", "3", false);
        String json = objectMapper.writeValueAsString(expected);
        assertTrue(json.contains("peerReviewed"));
        ReportResearch reportResearch = objectMapper.readValue(json, ReportResearch.class);
        assertEquals(expected, reportResearch);
    }

    @DisplayName("Report research cannot be peer reviewed")
    @Test
    void reportResearchSetsPeerReviewedToFalseWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageRangeException {
        String type = "ReportResearch";
        String pages = "42";
        String introductionBegin = "1";
        String introductionEnd = "3";
        boolean illustrated = false;
        boolean peerReviewed = false;
        ReportResearch expected = generateReportResearch(pages, introductionBegin, introductionEnd, illustrated);
        String json = generateMonographJsonString(type, introductionBegin, introductionEnd, pages,
                illustrated, peerReviewed);
        ReportResearch actual = objectMapper.readValue(json, ReportResearch.class);
        assertEquals(expected, actual);
    }

    @Test
    void reportLogsWarningWhenPeerReviewedIsTrue() {
        new ReportResearch(null, true);
        String expected = Report.PEER_REVIEWED_FALSE.replace("{}", ReportResearch.class.getSimpleName());
        logs.assertContains(expected);
    }

    private ReportResearch generateReportResearch(String pages, String introductionBegin, String introductionEnd,
                                                  boolean illustrated) throws InvalidPageRangeException {
        Range introductionRange = new Range.Builder()
                .withBegin(introductionBegin)
                .withEnd(introductionEnd)
                .build();

        MonographPages monographPages = new MonographPages.Builder()
                .withPages(pages)
                .withIntroduction(introductionRange)
                .withIllustrated(illustrated)
                .build();

        return new ReportResearch.Builder()
                .withPages(monographPages)
                .build();
    }
}
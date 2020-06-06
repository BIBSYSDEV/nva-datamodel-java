package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportResearchTest extends ReportTestBase {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;

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
        ObjectMapper objectMapper = new ObjectMapper();
        String type = "ReportResearch";
        String pages = "42";
        String introductionBegin = "1";
        String introductionEnd = "3";
        boolean illustrated = false;
        boolean peerReviewed = true;
        ReportResearch expected = generateReportResearch(pages, introductionBegin, introductionEnd, illustrated);

        String json = generateJsonString(type, pages, introductionBegin, introductionEnd,
                illustrated, peerReviewed);
        ReportResearch reportResearch = objectMapper.readValue(json, ReportResearch.class);
        assertEquals(expected, reportResearch);
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
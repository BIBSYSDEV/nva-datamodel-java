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

class ReportTest extends ReportTestBase {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("Report can be created from JSON")
    @Test
    void reportReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageRangeException {
        Report expected = generateReport("42", "1", "3", false);
        String json = objectMapper.writeValueAsString(expected);
        assertTrue(json.contains("peerReviewed"));
        Report report = objectMapper.readValue(json, Report.class);
        assertEquals(expected, report);
    }

    @DisplayName("Report cannot be peer reviewed")
    @Test
    void reportSetsPeerReviewedToFalseWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageRangeException {
        ObjectMapper objectMapper = new ObjectMapper();
        String type = "Report";
        String pages = "42";
        String introductionBegin = "1";
        String introductionEnd = "3";
        boolean illustrated = false;
        boolean peerReviewed = true;
        Report expected = generateReport(pages, introductionBegin, introductionEnd, illustrated);

        String json = generateJsonString(type, pages, introductionBegin, introductionEnd,
                illustrated, peerReviewed);
        Report report = objectMapper.readValue(json, Report.class);
        assertEquals(expected, report);
    }

    private Report generateReport(String pages, String introductionBegin, String introductionEnd,
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

        return new Report.Builder()
                .withPages(monographPages)
                .build();
    }
}
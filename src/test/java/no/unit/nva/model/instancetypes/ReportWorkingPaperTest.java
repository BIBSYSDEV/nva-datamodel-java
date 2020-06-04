package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Range;
import no.unit.nva.model.util.ReportContentTestUtil;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportWorkingPaperTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("Report working paper can be created from JSON")
    @Test
    void reportWorkingPaperReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageTypeException {
        ReportWorkingPaper expected = generateReportWorkingPaper("42", "1", "3", false);
        String json = objectMapper.writeValueAsString(expected);
        assertTrue(json.contains("peerReviewed"));
        ReportWorkingPaper reportWorkingPaper = objectMapper.readValue(json, ReportWorkingPaper.class);
        assertEquals(expected, reportWorkingPaper);
    }

    @DisplayName("Report working paper cannot be peer reviewed")
    @Test
    void reportWorkingPaperSetsPeerReviewedToFalseWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageTypeException {
        ObjectMapper objectMapper = new ObjectMapper();
        String type = "ReportWorkingPaper";
        String pages = "42";
        String introductionBegin = "1";
        String introductionEnd = "3";
        boolean illustrated = false;
        boolean peerReviewed = true;
        ReportWorkingPaper expected = generateReportWorkingPaper(pages, introductionBegin, introductionEnd,
                illustrated);

        String json = ReportContentTestUtil.generateJsonString(type, pages, introductionBegin, introductionEnd,
                illustrated, peerReviewed);
        ReportWorkingPaper reportWorkingPaper = objectMapper.readValue(json, ReportWorkingPaper.class);
        assertEquals(expected, reportWorkingPaper);
    }

    private ReportWorkingPaper generateReportWorkingPaper(String pages, String introductionBegin,
        String introductionEnd, boolean illustrated) throws InvalidPageTypeException {

        Range introductionRange = new Range.Builder()
                .withBegin(introductionBegin)
                .withEnd(introductionEnd)
                .build();

        MonographPages monographPages = new MonographPages.Builder()
                .withPages(pages)
                .withIntroduction(introductionRange)
                .withIllustrated(illustrated)
                .build();

        return new ReportWorkingPaper.Builder()
                .withPages(monographPages)
                .build();
    }
}
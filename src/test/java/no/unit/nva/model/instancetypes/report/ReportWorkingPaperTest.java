package no.unit.nva.model.instancetypes.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.instancetypes.InstanceTest;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.rmi.UnexpectedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportWorkingPaperTest extends InstanceTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("Report working paper can be created from JSON")
    @Test
    void reportWorkingPaperReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageRangeException {
        ReportWorkingPaper expected = generateReportWorkingPaper("42", "1", "3", false);
        String json = objectMapper.writeValueAsString(expected);
        assertTrue(json.contains("peerReviewed"));
        ReportWorkingPaper reportWorkingPaper = objectMapper.readValue(json, ReportWorkingPaper.class);
        assertEquals(expected, reportWorkingPaper);
    }

    @DisplayName("Report working paper cannot be peer reviewed")
    @Test
    void reportWorkingPaperSetsPeerReviewedToFalseWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageRangeException {
        ObjectMapper objectMapper = new ObjectMapper();
        String type = "ReportWorkingPaper";
        String pages = "42";
        String introductionBegin = "1";
        String introductionEnd = "3";
        boolean illustrated = false;
        boolean peerReviewed = true;
        ReportWorkingPaper expected = generateReportWorkingPaper(pages, introductionBegin, introductionEnd,
                illustrated);

        String json = generateMonographJsonString(type, introductionBegin, introductionEnd, pages,
                illustrated, peerReviewed);
        ReportWorkingPaper reportWorkingPaper = objectMapper.readValue(json, ReportWorkingPaper.class);
        assertEquals(expected, reportWorkingPaper);
    }

    @DisplayName("ReportWorkingPaper: Attempting to set peer reviewed to true results in Unexpected exception")
    @Test
    void reportWorkingPaperThrowsUnexpectedExceptionWhenPeerReviewedIsTrue() {
        Executable executable = () -> {
            ReportWorkingPaper reportWorkingPaper = new ReportWorkingPaper();
            reportWorkingPaper.setPeerReviewed(true);
        };
        UnexpectedException exception = assertThrows(UnexpectedException.class, executable);
        String expected = String.format(ReportWorkingPaper.PEER_REVIEWED_ERROR_TEMPLATE,
                ReportWorkingPaper.class.getSimpleName());
        assertEquals(expected, exception.getMessage());
    }

    private ReportWorkingPaper generateReportWorkingPaper(String pages, String introductionBegin,
        String introductionEnd, boolean illustrated) throws InvalidPageRangeException {

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
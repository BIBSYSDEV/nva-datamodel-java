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

class ReportTest extends InstanceTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String REPORT = "Report";

    @DisplayName("Report can be created from JSON")
    @Test
    void reportReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageRangeException {
        String pages = "42";
        String introductionBegin = "1";
        String introductionEnd = "3";
        Report expected = generateReport(pages, introductionBegin, introductionEnd, false);
        String json = generateMonographJsonString(REPORT, introductionBegin, introductionEnd, pages, false);
        Report actual = objectMapper.readValue(json, Report.class);
        assertEquals(expected, actual);
    }

    @DisplayName("Report cannot be peer reviewed")
    @Test
    void reportSetsPeerReviewedToFalseWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageRangeException {
        ObjectMapper objectMapper = new ObjectMapper();
        String pages = "42";
        String introductionBegin = "1";
        String introductionEnd = "3";
        Report expected = generateReport(pages, introductionBegin, introductionEnd, false);
        String json = generateMonographJsonString(REPORT, introductionBegin, introductionEnd, pages, false, true);
        Report report = objectMapper.readValue(json, Report.class);
        assertEquals(expected, report);
    }

    @DisplayName("Report: Attempting to set peer reviewed to true results in Unexpected exception")
    @Test
    void reportThrowsUnexpectedExceptionWhenPeerReviewedIsTrue() {
        Executable executable = () -> {
            Report report = new Report();
            report.setPeerReviewed(true);
        };
        UnexpectedException exception = assertThrows(UnexpectedException.class, executable);
        String expected = String.format(Report.PEER_REVIEWED_ERROR_TEMPLATE,
                Report.class.getSimpleName());
        assertEquals(expected, exception.getMessage());
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
package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
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

class ReportPolicyTest extends InstanceTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("Report policy can be created from JSON")
    @Test
    void reportPolicyReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageRangeException {
        ReportPolicy expected = generateReportPolicy("42", "1", "3", false);
        String json = objectMapper.writeValueAsString(expected);
        assertTrue(json.contains("peerReviewed"));
        ReportPolicy reportPolicy = objectMapper.readValue(json, ReportPolicy.class);
        assertEquals(expected, reportPolicy);
    }

    @DisplayName("Report policy cannot be peer reviewed")
    @Test
    void reportPolicySetsPeerReviewedToFalseWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageRangeException {
        String type = "ReportPolicy";
        String pages = "42";
        String introductionBegin = "1";
        String introductionEnd = "3";
        boolean illustrated = false;
        boolean peerReviewed = true;
        ReportPolicy expected = generateReportPolicy(pages, introductionBegin, introductionEnd, illustrated);

        String json = generateMonographJsonString(type, introductionBegin, introductionEnd, pages,
                illustrated, peerReviewed);
        ReportPolicy reportPolicy = objectMapper.readValue(json, ReportPolicy.class);
        assertEquals(expected, reportPolicy);
    }

    @DisplayName("ReportPolicy: Attempting to set peer reviewed to true results in Unexpected exception")
    @Test
    void reportThrowsUnexpectedExceptionWhenPeerReviewedIsTrue() {
        Executable executable = () -> {
            ReportPolicy reportPolicy = new ReportPolicy(null);
            reportPolicy.setPeerReviewed(true);
        };
        UnexpectedException exception = assertThrows(UnexpectedException.class, executable);
        String expected = String.format(ReportPolicy.PEER_REVIEWED_ERROR_TEMPLATE,
                ReportPolicy.class.getSimpleName());
        assertEquals(expected, exception.getMessage());
    }

    private ReportPolicy generateReportPolicy(String pages, String introductionBegin, String introductionEnd,
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

        return new ReportPolicy.Builder()
                .withPages(monographPages)
                .build();
    }
}
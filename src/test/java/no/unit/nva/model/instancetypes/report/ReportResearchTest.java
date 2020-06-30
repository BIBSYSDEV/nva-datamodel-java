package no.unit.nva.model.instancetypes.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.instancetypes.InstanceTest;
import no.unit.nva.model.instancetypes.MonographTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static no.unit.nva.model.instancetypes.NonPeerReviewed.PEER_REVIEWED_ERROR_TEMPLATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReportResearchTest extends InstanceTest {

    @DisplayName("ReportResearch can be created from JSON")
    @Test
    void reportResearchReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageRangeException {
        MonographTestData testData = new MonographTestData(false);
        ReportResearch expected = generateReportResearch(testData);
        String json = generateMonographJsonString(ReportResearch.class.getSimpleName(), testData);
        ReportResearch reportResearch = objectMapper.readValue(json, ReportResearch.class);
        assertEquals(expected, reportResearch);
    }

    @DisplayName("ReportResearch cannot be peer reviewed")
    @Test
    void reportResearchSetsPeerReviewedToFalseWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageRangeException {
        MonographTestData testData = new MonographTestData(false);
        ReportResearch expected = generateReportResearch(testData);
        String json = generateMonographJsonString(ReportResearch.class.getSimpleName(), testData);
        ReportResearch actual = objectMapper.readValue(json, ReportResearch.class);
        assertEquals(expected, actual);
    }

    @DisplayName("ReportResearch: Attempting to set peer reviewed to true results in JsonMappingException")
    @Test
    void reportThrowsUnexpectedExceptionWhenPeerReviewedIsTrue() throws InvalidPageRangeException,
            JsonProcessingException {
        String json = generateMonographJsonString(ReportResearch.class.getSimpleName(), new MonographTestData(true));
        Executable executable = () -> objectMapper.readValue(json, ReportResearch.class);
        JsonMappingException exception = assertThrows(JsonMappingException.class, executable);
        String expected = String.format(PEER_REVIEWED_ERROR_TEMPLATE, ReportResearch.class.getSimpleName());
        assertThat(exception.getMessage(), containsString(expected));
    }

    private ReportResearch generateReportResearch(MonographTestData testData) {
        return new ReportResearch.Builder()
                .withPages(testData.getPages())
                .build();
    }
}
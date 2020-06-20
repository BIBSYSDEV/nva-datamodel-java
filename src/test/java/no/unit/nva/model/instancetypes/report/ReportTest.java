package no.unit.nva.model.instancetypes.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.instancetypes.InstanceTest;
import no.unit.nva.model.instancetypes.MonographTestData;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static no.unit.nva.model.instancetypes.NonPeerReviewed.PEER_REVIEWED_ERROR_TEMPLATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReportTest extends InstanceTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String REPORT = "Report";

    @DisplayName("Report can be created from JSON")
    @Test
    void reportReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageRangeException {
        MonographTestData testData = new MonographTestData(false);
        Report expected = generateReport(testData);
        String json = generateMonographJsonString(REPORT, testData);
        Report actual = objectMapper.readValue(json, Report.class);
        assertEquals(expected, actual);
    }

    @DisplayName("Report: Attempting to set peer reviewed to true results in UnexpectedException")
    @Test
    void reportThrowsUnexpectedExceptionWhenPeerReviewedIsTrue() throws JsonProcessingException,
            InvalidPageRangeException {
        MonographTestData testData = new MonographTestData(true);
        String json = generateMonographJsonString(REPORT, testData);
        Executable executable = () -> objectMapper.readValue(json, Report.class);
        JsonMappingException exception = assertThrows(JsonMappingException.class, executable);
        String expected = String.format(PEER_REVIEWED_ERROR_TEMPLATE, Report.class.getSimpleName());
        assertThat(exception.getMessage(), containsString(expected));
    }

    private Report generateReport(MonographTestData testData) {
        return new Report.Builder()
                .withPages(testData.getPages())
                .build();
    }
}
package no.unit.nva.model.instancetypes.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

class ReportPolicyTest extends InstanceTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("Report policy can be created from JSON")
    @Test
    void reportPolicyReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException {
        MonographTestData testData = new MonographTestData(false);
        ReportPolicy expected = generateReportPolicy(testData);
        String json = generateMonographJsonString(ReportPolicy.class.getSimpleName(), testData);
        ReportPolicy reportPolicy = objectMapper.readValue(json, ReportPolicy.class);
        assertEquals(expected, reportPolicy);
    }

    @DisplayName("ReportPolicy: Attempting to set peer reviewed to true results in JsonMappingException")
    @Test
    void reportThrowsJsonMappingExceptionWhenPeerReviewedIsTrue() throws
            JsonProcessingException {
        MonographTestData testData = new MonographTestData(true);
        String json = generateMonographJsonString(ReportPolicy.class.getSimpleName(), testData);
        Executable executable = () -> objectMapper.readValue(json, ReportPolicy.class);
        JsonMappingException exception = assertThrows(JsonMappingException.class, executable);
        String expected = String.format(PEER_REVIEWED_ERROR_TEMPLATE, ReportPolicy.class.getSimpleName());
        assertThat(exception.getMessage(), containsString(expected));
    }

    private ReportPolicy generateReportPolicy(MonographTestData testData) {
        return new ReportPolicy.Builder()
                .withPages(testData.getPages())
                .build();
    }
}
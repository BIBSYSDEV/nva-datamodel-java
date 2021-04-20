package no.unit.nva.model.instancetypes.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.instancetypes.InstanceTest;
import no.unit.nva.model.instancetypes.MonographTestData;
import nva.commons.core.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportBasicTest extends InstanceTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String REPORT = "ReportBasic";

    @DisplayName("ReportBasic can be created from JSON")
    @Test
    void reportReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException {
        MonographTestData testData = new MonographTestData(false, false);
        ReportBasic expected = generateReport(testData);
        String json = generateMonographJsonString(REPORT, testData);
        ReportBasic actual = objectMapper.readValue(json, ReportBasic.class);
        assertEquals(expected, actual);
    }

    private ReportBasic generateReport(MonographTestData testData) {
        return new ReportBasic.Builder()
                .withPages(testData.getPages())
                .build();
    }
}
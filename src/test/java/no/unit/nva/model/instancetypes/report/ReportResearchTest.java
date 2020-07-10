package no.unit.nva.model.instancetypes.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.model.instancetypes.InstanceTest;
import no.unit.nva.model.instancetypes.MonographTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportResearchTest extends InstanceTest {

    @DisplayName("ReportResearch can be created from JSON")
    @Test
    void reportResearchReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException {
        MonographTestData testData = new MonographTestData(false);
        ReportResearch expected = generateReportResearch(testData);
        String json = generateMonographJsonString(ReportResearch.class.getSimpleName(), testData);
        ReportResearch reportResearch = objectMapper.readValue(json, ReportResearch.class);
        assertEquals(expected, reportResearch);
    }

    private ReportResearch generateReportResearch(MonographTestData testData) {
        return new ReportResearch.Builder()
                .withPages(testData.getPages())
                .build();
    }
}
package no.unit.nva.model.instancetypes.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.instancetypes.InstanceTest;
import no.unit.nva.model.instancetypes.MonographTestData;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportWorkingPaperTest extends InstanceTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("ReportBasic working paper can be created from JSON")
    @Test
    void reportWorkingPaperReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException {
        MonographTestData testData = new MonographTestData(false);
        String json = generateMonographJsonString(ReportWorkingPaper.class.getSimpleName(), testData);
        ReportWorkingPaper expected = generateReportWorkingPaper(testData);
        ReportWorkingPaper actual = objectMapper.readValue(json, ReportWorkingPaper.class);
        assertEquals(expected, actual);
    }

    private ReportWorkingPaper generateReportWorkingPaper(MonographTestData testData) {
        return new ReportWorkingPaper.Builder()
                .withPages(testData.getPages())
                .build();
    }
}
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

class ReportWorkingPaperTest extends InstanceTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("Report working paper can be created from JSON")
    @Test
    void reportWorkingPaperReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageRangeException {
        MonographTestData testData = new MonographTestData(false);
        String json = generateMonographJsonString(ReportWorkingPaper.class.getSimpleName(), testData);
        ReportWorkingPaper expected = generateReportWorkingPaper(testData);
        ReportWorkingPaper actual = objectMapper.readValue(json, ReportWorkingPaper.class);
        assertEquals(expected, actual);
    }

    @DisplayName("ReportWorkingPaper: Attempting to set peer reviewed to true results in JsonMappingException")
    @Test
    void reportWorkingPaperThrowsJsonMappingExceptionWhenPeerReviewedIsTrue() throws InvalidPageRangeException,
            JsonProcessingException {
        MonographTestData testData = new MonographTestData(true);
        String json = generateMonographJsonString(ReportWorkingPaper.class.getSimpleName(), testData);
        Executable executable = () -> objectMapper.readValue(json, ReportWorkingPaper.class);
        JsonMappingException exception = assertThrows(JsonMappingException.class, executable);
        String expected = String.format(PEER_REVIEWED_ERROR_TEMPLATE, ReportWorkingPaper.class.getSimpleName());
        assertThat(exception.getMessage(), containsString(expected));
    }

    private ReportWorkingPaper generateReportWorkingPaper(MonographTestData testData) {
        return new ReportWorkingPaper.Builder()
                .withPages(testData.getPages())
                .build();
    }
}
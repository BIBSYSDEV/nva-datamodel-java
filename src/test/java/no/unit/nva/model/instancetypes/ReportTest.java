package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Range;
import no.unit.nva.model.util.ReportContentUtil;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("Report can be created from JSON")
    @Test
    void reportReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageTypeException {
        Report expected = generateReport("2", "3");
        String json = objectMapper.writeValueAsString(expected);
        Report report = objectMapper.readValue(json, Report.class);
        assertEquals(expected, report);
    }

    @DisplayName("Report cannot be peer reviewed")
    @Test
    void reportSetsPeerReviewedToFalseWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageTypeException {
        ObjectMapper objectMapper = new ObjectMapper();
        String type = "Report";
        String begin = "2";
        String end = "3";
        Report expected = generateReport(begin, end);

        String json = ReportContentUtil.generateJsonString(type, begin, end, true);
        assertEquals(expected, objectMapper.readValue(json, Report.class));
    }

    private Report generateReport(String begin, String end) throws InvalidPageTypeException {
        Range pages = new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();

        return new Report.Builder()
                .withPages(pages)
                .build();
    }
}
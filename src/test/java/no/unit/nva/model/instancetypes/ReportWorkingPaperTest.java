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

class ReportWorkingPaperTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("Report working paper can be created from JSON")
    @Test
    void reportWorkingPaperReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageTypeException {
        ReportWorkingPaper expected = generateReportWorkingPaper("2", "3");
        String json = objectMapper.writeValueAsString(expected);
        ReportWorkingPaper reportWorkingPaper = objectMapper.readValue(json, ReportWorkingPaper.class);
        assertEquals(expected, reportWorkingPaper);
    }

    @DisplayName("Report working paper cannot be peer reviewed")
    @Test
    void reportWorkingPaperSetsPeerReviewedToFalseWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageTypeException {
        ObjectMapper objectMapper = new ObjectMapper();
        String type = "ReportWorkingPaper";
        String begin = "2";
        String end = "3";
        ReportWorkingPaper expected = generateReportWorkingPaper(begin, end);

        String json = ReportContentUtil.generateJsonString(type, begin, end, true);
        assertEquals(expected, objectMapper.readValue(json, ReportWorkingPaper.class));
    }

    private ReportWorkingPaper generateReportWorkingPaper(String begin, String end)
            throws InvalidPageTypeException {
        Range pages = new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();

        return new ReportWorkingPaper.Builder()
                .withPages(pages)
                .build();
    }
}
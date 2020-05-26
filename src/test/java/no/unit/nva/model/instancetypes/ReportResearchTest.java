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

class ReportResearchTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("Report research can be created from JSON")
    @Test
    void reportResearchReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageTypeException {
        ReportResearch expected = generateReportResearch("2", "3");
        String json = objectMapper.writeValueAsString(expected);
        ReportResearch reportResearch = objectMapper.readValue(json, ReportResearch.class);
        assertEquals(expected, reportResearch);
    }

    @DisplayName("Report research cannot be peer reviewed")
    @Test
    void reportResearchSetsPeerReviewedToFalseWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageTypeException {
        ObjectMapper objectMapper = new ObjectMapper();
        String type = "ReportResearch";
        String begin = "2";
        String end = "3";
        ReportResearch expected = generateReportResearch("2", "3");

        String json = ReportContentUtil.generateJsonString(type, begin, end, true);
        assertEquals(expected, objectMapper.readValue(json, ReportResearch.class));
    }

    private ReportResearch generateReportResearch(String begin, String end) throws InvalidPageTypeException {
        Range pages = new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();

        return new ReportResearch.Builder()
                .withPages(pages)
                .build();
    }
}
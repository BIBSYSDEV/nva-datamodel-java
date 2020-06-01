package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Range;
import no.unit.nva.model.util.ReportContentTestUtil;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportPolicyTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("Report policy can be created from JSON")
    @Test
    void reportPolicyReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageTypeException {
        ReportPolicy expected = generateReportPolicy("2", "3");
        String json = objectMapper.writeValueAsString(expected);
        ReportPolicy reportPolicy = objectMapper.readValue(json, ReportPolicy.class);
        assertEquals(expected, reportPolicy);
    }

    @DisplayName("Report policy cannot be peer reviewed")
    @Test
    void reportPolicySetsPeerReviewedToFalseWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageTypeException {
        ObjectMapper objectMapper = new ObjectMapper();
        String type = "ReportPolicy";
        String begin = "2";
        String end = "3";
        ReportPolicy expected = generateReportPolicy(begin, end);

        String json = ReportContentTestUtil.generateJsonString(type, begin, end, true);
        assertEquals(expected, objectMapper.readValue(json, ReportPolicy.class));
    }

    private ReportPolicy generateReportPolicy(String begin, String end) throws InvalidPageTypeException {
        Range pages = new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();

        return new ReportPolicy.Builder()
                .withPages(pages)
                .build();
    }
}
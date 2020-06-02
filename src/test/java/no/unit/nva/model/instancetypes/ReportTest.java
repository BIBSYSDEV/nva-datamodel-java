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

class ReportTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("Report can be created from JSON")
    @Test
    void reportReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageTypeException {
        Report expected = generateReport("42", "1", "3", false);
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
        String pages = "42";
        String introductionBegin = "1";
        String introductionEnd = "3";
        boolean illustrated = false;
        Report expected = generateReport(pages, introductionBegin, introductionEnd, illustrated);

        String json = ReportContentTestUtil.generateJsonString(type, pages, introductionBegin, introductionEnd,
                illustrated, false);
        assertEquals(expected, objectMapper.readValue(json, Report.class));
    }

    private Report generateReport(String pages, String introductionBegin, String introductionEnd,
                                                          boolean illustrated) throws InvalidPageTypeException {
        Range introductionRange = new Range.Builder()
                .withBegin(introductionBegin)
                .withEnd(introductionEnd)
                .build();

        MonographPages monographPages = new MonographPages.Builder()
                .withPages(pages)
                .withIntroduction(introductionRange)
                .withIllustrated(illustrated)
                .build();

        return new Report.Builder()
                .withPages(monographPages)
                .build();
    }
}
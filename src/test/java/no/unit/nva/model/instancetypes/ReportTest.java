package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.netmikey.logunit.api.LogCapturer;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportTest extends InstanceTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String REPORT = "Report";

    @RegisterExtension
    LogCapturer logs = LogCapturer.create().captureForType(NonPeerReviewedMonograph.class);

    @DisplayName("Report can be created from JSON")
    @Test
    void reportReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageRangeException {
        String pages = "42";
        String introductionBegin = "1";
        String introductionEnd = "3";
        Report expected = generateReport(pages, introductionBegin, introductionEnd, false);
        String json = generateMonographJsonString(REPORT, introductionBegin, introductionEnd, pages, false);
        Report actual = objectMapper.readValue(json, Report.class);
        assertEquals(expected, actual);
    }

    @DisplayName("Report cannot be peer reviewed")
    @Test
    void reportSetsPeerReviewedToFalseWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageRangeException {
        ObjectMapper objectMapper = new ObjectMapper();
        String pages = "42";
        String introductionBegin = "1";
        String introductionEnd = "3";
        Report expected = generateReport(pages, introductionBegin, introductionEnd, false);
        String json = generateMonographJsonString(REPORT, introductionBegin, introductionEnd, pages, false, true);
        Report report = objectMapper.readValue(json, Report.class);
        assertEquals(expected, report);
    }

    @Test
    void reportLogsWarningWhenPeerReviewedIsTrue() {
        new Report(null, true);
        String expected = Report.PEER_REVIEWED_FALSE.replace("{}", Report.class.getSimpleName());
        logs.assertContains(expected);
    }

    private Report generateReport(String pages, String introductionBegin, String introductionEnd,
                                  boolean illustrated) throws InvalidPageRangeException {
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
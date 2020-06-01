package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.Level;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidIssnException;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static no.unit.nva.model.util.PublicationGenerator.convertIsbnStringToList;
import static no.unit.nva.model.util.PublicationGenerator.generatePublicationJson;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReportTest {
    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String REPORT = "Report";
    public static final String ONLINE_ISSN = "0363-6941";
    public static final String PRINT_ISSN = "1945-662X";

    @DisplayName("Reports can be created")
    @ParameterizedTest
    @CsvSource({
            "Series,123,A publisher,LEVEL_0,false,false,9780201309515,0363-6941,1945-662X",
            "Series,123,A publisher,LEVEL_0,false,false,9780201309515|9788131700075,0363-6941,1945-662X",
            "Series,123,A publisher,LEVEL_0,true,false,9780201309515|9788131700075,0363-6941,1945-662X",
            "Series,123,A publisher,LEVEL_0,true,true,9780201309515|9788131700075,0363-6941,1945-662X",
            "Series,,A publisher,LEVEL_0,false,false,9780201309515|9788131700075,0363-6941,1945-662X",
            ",123,A publisher,LEVEL_0,false,false,9780201309515|9788131700075,0363-6941,1945-662X",
            "Series,123,A publisher,LEVEL_0,false,false,,0363-6941,1945-662X",
            ",,A publisher,LEVEL_0,false,false,9780201309515|9788131700075,0363-6941,1945-662X",
            "Series,123,A publisher,LEVEL_0,false,false,9780201309515|9788131700075,,"
    })
    void objectMapperReturnsReportWhenInputIsValidJson(String seriesTitle,
                                                       String seriesNumber,
                                                       String publisher,
                                                       String level,
                                                       String openAccess,
                                                       String peerReviewed,
                                                       String isbnList,
                                                       String printIssn,
                                                       String onlineIssn) throws JsonProcessingException {
        Level expectedLevel = Level.valueOf(level);
        boolean expectedOpenAccess = Boolean.getBoolean(openAccess);
        boolean expectedPeerReviewed = Boolean.getBoolean(peerReviewed);
        List<String> expectedIsbn = convertIsbnStringToList(isbnList);
        String json = generatePublicationJson(
                REPORT,
                seriesTitle,
                seriesNumber,
                publisher,
                expectedLevel,
                expectedOpenAccess,
                expectedPeerReviewed,
                expectedIsbn,
                onlineIssn,
                printIssn
        );
        Report report = objectMapper.readValue(json, Report.class);
        assertEquals(seriesTitle, report.getSeriesTitle());
        assertEquals(seriesNumber, report.getSeriesNumber());
        assertEquals(expectedLevel, report.getLevel());
        assertEquals(expectedIsbn, report.getIsbnList());
        assertEquals(expectedOpenAccess, report.isOpenAccess());
        assertEquals(expectedPeerReviewed, report.isPeerReviewed());
        assertEquals(onlineIssn, report.getOnlineIssn());
        assertEquals(printIssn, report.getPrintIssn());
    }

    @DisplayName("Report serializes expected json")
    @ParameterizedTest
    @CsvSource({
            "Series,123,A publisher,LEVEL_0,false,false,9780201309515,0363-6941,1945-662X",
            "Series,123,A publisher,LEVEL_0,false,false,9780201309515|9788131700075,0363-6941,1945-662X",
            "Series,123,A publisher,LEVEL_0,true,false,9780201309515|9788131700075,0363-6941,1945-662X",
            "Series,123,A publisher,LEVEL_0,true,true,9780201309515|9788131700075,0363-6941,1945-662X",
            "Series,,A publisher,LEVEL_0,false,false,9780201309515|9788131700075,0363-6941,1945-662X",
            ",123,A publisher,LEVEL_0,false,false,9780201309515|9788131700075,0363-6941,1945-662X",
            "Series,123,A publisher,LEVEL_0,false,false,,0363-6941,1945-662X",
            ",,A publisher,LEVEL_0,false,false,9780201309515|9788131700075,0363-6941,1945-662X",
            "Series,123,A publisher,LEVEL_0,false,false,9780201309515|9788131700075,,"
    })
    void objectMapperProducesProperlyFormattedJsonWhenInputIsReport(String seriesTitle,
                                                                    String seriesNumber,
                                                                    String publisher,
                                                                    String level,
                                                                    String openAccess,
                                                                    String peerReviewed,
                                                                    String isbnList,
                                                                    String onlineIssn,
                                                                    String printIssn) throws JsonProcessingException,
            InvalidIsbnException, InvalidIssnException {
        List<String> expectedIsbnList = convertIsbnStringToList(isbnList);
        boolean expectedOpenAccess = Boolean.getBoolean(openAccess);
        boolean expectedPeerReviewed = Boolean.getBoolean(peerReviewed);
        Level expectedLevel = Level.valueOf(level);
        Report report = new Report.Builder()
                .withSeriesTitle(seriesTitle)
                .withSeriesNumber(seriesNumber)
                .withPublisher(publisher)
                .withLevel(expectedLevel)
                .withOpenAccess(expectedOpenAccess)
                .withPeerReviewed(expectedPeerReviewed)
                .withIsbnList(expectedIsbnList)
                .withOnlineIssn(onlineIssn)
                .withPrintIssn(printIssn)
                .build();
        String expectedJson = generatePublicationJson(
                REPORT,
                seriesTitle,
                seriesNumber,
                publisher,
                expectedLevel,
                expectedOpenAccess,
                expectedPeerReviewed,
                expectedIsbnList,
                onlineIssn,
                printIssn
        );
        String actualJson = objectMapper.writeValueAsString(report);
        assertEquals(expectedJson, actualJson);
    }

    @DisplayName("report complains if ISBNs are invalid")
    @ParameterizedTest
    @CsvSource({
            "Report,123,A publisher,LEVEL_2,true,true,\"obviousNonsense|9788131700075\",0363-6941,1945-662X",
            "Report,123,A publisher,LEVEL_2,true,true,\"9780201309515|obviousNonsense\",0363-6941,1945-662X",
            "Report,123,A publisher,LEVEL_2,true,true,\"9780201309515|9788131700075|obviousNonsense\","
                    + "0363-6941,1945-662X"
    })
    void reportThrowsInvalidIsbnExceptionWhenIsbnIsInvalid(String seriesTitle,
                                                           String seriesNumber,
                                                           String publisher,
                                                           String level,
                                                           String openAccess,
                                                           String peerReviewed,
                                                           String isbnList,
                                                           String onlineIssn,
                                                           String printIssn) {

        ArrayList<String> invalidIsbnList = new ArrayList<>(convertIsbnStringToList(isbnList));

        Executable executable = () -> new Report.Builder()
                .withSeriesTitle(seriesTitle)
                .withSeriesNumber(seriesNumber)
                .withPublisher(publisher)
                .withLevel(Level.valueOf(level))
                .withOpenAccess(Boolean.getBoolean(openAccess))
                .withPeerReviewed(Boolean.getBoolean(peerReviewed))
                .withIsbnList(invalidIsbnList)
                .withPrintIssn(printIssn)
                .withOnlineIssn(onlineIssn)
                .build();

        Exception exception = assertThrows(InvalidIsbnException.class, executable);
        String expectedMessage = String.format(InvalidIsbnException.ERROR_TEMPLATE, "obviousNonsense");
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }


    @DisplayName("Report: Null ISBNs are handled gracefully")
    @Test
    void reportReturnsEmptyListWhenIsbnsAreNull() throws InvalidIsbnException, InvalidIssnException {
        Report report = new Report.Builder()
                .withSeriesTitle(null)
                .withSeriesNumber(null)
                .withLevel(Level.LEVEL_0)
                .withPeerReviewed(false)
                .withOpenAccess(false)
                .withPublisher(null)
                .withIsbnList(null)
                .withOnlineIssn(ONLINE_ISSN)
                .withPrintIssn(PRINT_ISSN)
                .build();

        List<String> resultIsbnList = report.getIsbnList();
        assertThat(resultIsbnList, is(not(nullValue())));
        assertThat(resultIsbnList, is(empty()));
    }

    @DisplayName("Report: Empty ISBNs are handled gracefully")
    @Test
    void reportReturnsEmptyListWhenIsbnListIsEmpty() throws InvalidIsbnException, InvalidIssnException {
        Report report = new Report.Builder()
                .withSeriesTitle(null)
                .withSeriesNumber(null)
                .withLevel(Level.LEVEL_0)
                .withPeerReviewed(false)
                .withOpenAccess(false)
                .withPublisher(null)
                .withIsbnList(Collections.emptyList())
                .withPrintIssn(PRINT_ISSN)
                .withOnlineIssn(ONLINE_ISSN)
                .build();

        List<String> resultIsbnList = report.getIsbnList();
        assertThat(resultIsbnList, is(not(nullValue())));
        assertThat(resultIsbnList, is(empty()));
    }
}

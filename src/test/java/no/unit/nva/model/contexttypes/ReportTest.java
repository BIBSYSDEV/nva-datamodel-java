package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.ModelTest;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidIssnException;
import nva.commons.core.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static no.unit.nva.model.util.PublicationGenerator.convertIsbnStringToList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReportTest extends ModelTest {
    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String REPORT = "Report";
    public static final String ONLINE_ISSN = "0363-6941";
    public static final String PRINT_ISSN = "1945-662X";
    public static final URI SAMPLE_LINKED_CONTEXT = URI.create("https://example.org/linkedContext");


    @DisplayName("Reports can be created")
    @ParameterizedTest
    @CsvSource({
        "Series,123,A publisher,9780201309515,0363-6941,1945-662X",
        "Series,123,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        "Series,123,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        "Series,123,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        "Series,,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        ",123,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        "Series,123,A publisher,,0363-6941,1945-662X",
        ",,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        "Series,123,A publisher,9780201309515|9788131700075,,"
    })
    void objectMapperReturnsReportWhenInputIsValidJson(String seriesTitle,
                                                       String seriesNumber,
                                                       String publisher,
                                                       String isbnList,
                                                       String printIssn,
                                                       String onlineIssn) throws JsonProcessingException {
        List<String> expectedIsbn = convertIsbnStringToList(isbnList);
        String json = generatePublicationJson(
                REPORT,
                seriesTitle,
                seriesNumber,
                publisher,
                expectedIsbn,
                onlineIssn,
                printIssn,
                SAMPLE_LINKED_CONTEXT
        );
        Report report = objectMapper.readValue(json, Report.class);
        assertEquals(seriesTitle, report.getSeriesTitle());
        assertEquals(seriesNumber, report.getSeriesNumber());
        assertEquals(expectedIsbn, report.getIsbnList());
        assertEquals(onlineIssn, report.getOnlineIssn());
        assertEquals(printIssn, report.getPrintIssn());
    }

    @DisplayName("Report serializes expected json")
    @ParameterizedTest
    @CsvSource({
            "Series,123,A publisher,9780201309515,0363-6941,1945-662X",
            "Series,123,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
            "Series,123,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
            "Series,123,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
            "Series,,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
            ",123,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
            "Series,123,A publisher,,0363-6941,1945-662X",
            ",,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
            "Series,123,A publisher,9780201309515|9788131700075,,"
    })
    void objectMapperProducesProperlyFormattedJsonWhenInputIsReport(String seriesTitle,
                                                                    String seriesNumber,
                                                                    String publisher,
                                                                    String isbnList,
                                                                    String onlineIssn,
                                                                    String printIssn) throws JsonProcessingException,
            InvalidIsbnException, InvalidIssnException {
        List<String> expectedIsbnList = convertIsbnStringToList(isbnList);
        Report report = new Report.Builder()
                .withSeriesTitle(seriesTitle)
                .withSeriesNumber(seriesNumber)
                .withPublisher(publisher)
                .withIsbnList(expectedIsbnList)
                .withOnlineIssn(onlineIssn)
                .withPrintIssn(printIssn)
                .withLinkedContext(SAMPLE_LINKED_CONTEXT)
                .build();
        String expectedJson = generatePublicationJson(
                REPORT,
                seriesTitle,
                seriesNumber,
                publisher,
                expectedIsbnList,
                onlineIssn,
                printIssn,
                SAMPLE_LINKED_CONTEXT
        );
        String actualJson = objectMapper.writeValueAsString(report);
        assertEquals(expectedJson, actualJson);
    }

    @DisplayName("report complains if ISBNs are invalid")
    @ParameterizedTest
    @CsvSource({
        "Report,123,A publisher,\"obviousNonsense|9788131700075\",0363-6941,1945-662X",
        "Report,123,A publisher,\"9780201309515|obviousNonsense\",0363-6941,1945-662X",
        "Report,123,A publisher,\"9780201309515|9788131700075|obviousNonsense\",0363-6941,1945-662X"
    })
    void reportThrowsInvalidIsbnExceptionWhenIsbnIsInvalid(String seriesTitle,
                                                           String seriesNumber,
                                                           String publisher,
                                                           String isbnList,
                                                           String onlineIssn,
                                                           String printIssn) {

        ArrayList<String> invalidIsbnList = new ArrayList<>(convertIsbnStringToList(isbnList));

        Executable executable = () -> new Report.Builder()
                .withSeriesTitle(seriesTitle)
                .withSeriesNumber(seriesNumber)
                .withPublisher(publisher)
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
                .withPublisher(null)
                .withIsbnList(Collections.emptyList())
                .withPrintIssn(PRINT_ISSN)
                .withOnlineIssn(ONLINE_ISSN)
                .build();

        List<String> resultIsbnList = report.getIsbnList();
        assertThat(resultIsbnList, is(not(nullValue())));
        assertThat(resultIsbnList, is(empty()));
    }

    @DisplayName("Report: serializes and deserializes with linkedContext")
    @Test
    void reportIsSerializedAndDeserializedWithLinkedContext()
            throws InvalidIsbnException, JsonProcessingException, InvalidIssnException {
        Report actualReport = new Report.Builder()
                .withLinkedContext(SAMPLE_LINKED_CONTEXT)
                .build();

        String expectedJson = generatePublicationJson(
                REPORT,
                null,
                null,
                null,
                null,
                null,
                null,
                SAMPLE_LINKED_CONTEXT
        );

        String actualJson = objectMapper.writeValueAsString(actualReport);
        assertEquals(expectedJson, actualJson);
        Report deserializedReport = objectMapper.readValue(actualJson, Report.class);
        assertEquals(actualReport, deserializedReport);
        assertEquals(SAMPLE_LINKED_CONTEXT, deserializedReport.getLinkedContext());
    }
}

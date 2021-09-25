package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.ModelTest;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.exceptions.InvalidUnconfirmedSeriesException;
import nva.commons.core.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @DisplayName("Reports can be created")
    @ParameterizedTest
    @CsvSource({
        "A Report series,123,A publisher,9780201309515,0363-6941,1945-662X",
        "A Report series,123,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        "A Report series,123,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        "A Report series,123,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        "A Report series,,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        ",123,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        "A Report series,123,A publisher,,0363-6941,1945-662X",
        ",,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        "A Report series,123,A publisher,9780201309515|9788131700075,,"
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
                null
        );
        Report report = objectMapper.readValue(json, Report.class);
        assertEquals(seriesTitle, ((UnconfirmedSeries) report.getSeries()).getTitle());
        assertEquals(seriesNumber, report.getSeriesNumber());
        assertEquals(expectedIsbn, report.getIsbnList());
        assertEquals(onlineIssn, ((UnconfirmedSeries) report.getSeries()).getOnlineIssn());
        assertEquals(printIssn, ((UnconfirmedSeries) report.getSeries()).getIssn());
    }

    @DisplayName("Report serializes expected json")
    @ParameterizedTest
    @CsvSource({
        "A Report series,123,A publisher,9780201309515,0363-6941,1945-662X",
        "A Report series,123,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        "A Report series,123,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        "A Report series,123,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        "A Report series,,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        ",123,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        "A Report series,123,A publisher,,0363-6941,1945-662X",
        ",,A publisher,9780201309515|9788131700075,0363-6941,1945-662X",
        "A Report series,123,A publisher,9780201309515|9788131700075,,"
    })
    void objectMapperProducesProperlyFormattedJsonWhenInputIsReport(String seriesTitle,
                                                                    String seriesNumber,
                                                                    String publisher,
                                                                    String isbnList,
                                                                    String onlineIssn,
                                                                    String printIssn) throws JsonProcessingException,
            InvalidIsbnException, InvalidIssnException, InvalidUnconfirmedSeriesException {
        List<String> expectedIsbnList = convertIsbnStringToList(isbnList);
        Report report = new Report.Builder()
                .withSeries(new UnconfirmedSeries(seriesTitle, printIssn, onlineIssn))
                .withSeriesNumber(seriesNumber)
                .withPublisher(new UnconfirmedPublisher(publisher))
                .withIsbnList(expectedIsbnList)
                .build();
        String expectedJson = generatePublicationJson(
                REPORT,
                seriesTitle,
                seriesNumber,
                publisher,
                expectedIsbnList,
                onlineIssn,
                printIssn,
                null
        );
        String actualJson = objectMapper.writeValueAsString(report);
        assertEquals(expectedJson, actualJson);
    }


    @DisplayName("Report complains if ISBNs are invalid")
    @ParameterizedTest
    @CsvSource({
        "A Report series,123,A publisher,\"obviousNonsense|9788131700075\",0363-6941,1945-662X",
        "A Report series,123,A publisher,\"9780201309515|obviousNonsense\",0363-6941,1945-662X",
        "A Report series,123,A publisher,\"9780201309515|9788131700075|obviousNonsense\",0363-6941,1945-662X"
    })
    void reportThrowsInvalidIsbnExceptionWhenIsbnIsInvalid(String seriesTitle,
                                                           String seriesNumber,
                                                           String publisher,
                                                           String isbnList,
                                                           String onlineIssn,
                                                           String printIssn) {

        ArrayList<String> invalidIsbnList = new ArrayList<>(convertIsbnStringToList(isbnList));

        Executable executable = () -> new Report.Builder()
                .withSeries(new UnconfirmedSeries(seriesTitle, printIssn, onlineIssn))
                .withSeriesNumber(seriesNumber)
                .withPublisher(new UnconfirmedPublisher(publisher))
                .withIsbnList(invalidIsbnList)
                .build();

        Exception exception = assertThrows(InvalidIsbnException.class, executable);
        String expectedMessage = String.format(InvalidIsbnException.ERROR_TEMPLATE, "obviousNonsense");
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("Report: Null ISBNs are handled gracefully")
    @Test
    void reportReturnsEmptyListWhenIsbnsAreNull() throws InvalidIsbnException, InvalidIssnException,
            InvalidUnconfirmedSeriesException {
        Report report = new Report.Builder()
                .withSeries(null)
                .withSeriesNumber(null)
                .withPublisher(null)
                .withIsbnList(null)
                .build();

        List<String> resultIsbnList = report.getIsbnList();
        assertThat(resultIsbnList, is(not(nullValue())));
        assertThat(resultIsbnList, is(empty()));
    }

    @DisplayName("Report: Empty ISBNs are handled gracefully")
    @Test
    void reportReturnsEmptyListWhenIsbnListIsEmpty() throws InvalidIsbnException, InvalidIssnException,
            InvalidUnconfirmedSeriesException {
        Report report = new Report.Builder()
                .withSeries(null)
                .withSeriesNumber(null)
                .withPublisher(null)
                .withIsbnList(Collections.emptyList())
                .build();

        List<String> resultIsbnList = report.getIsbnList();
        assertThat(resultIsbnList, is(not(nullValue())));
        assertThat(resultIsbnList, is(empty()));
    }
}

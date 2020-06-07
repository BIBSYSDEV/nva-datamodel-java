package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.netmikey.logunit.api.LogCapturer;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DegreePhdTest extends InstanceTest {
    private static final String DEGREE_PHD = "DegreePhd";

    @RegisterExtension
    LogCapturer logs = LogCapturer.create().captureForType(NonPeerReviewedMonograph.class);

    @DisplayName("DegreePhd exists")
    @Test
    void degreePhdExists() {
        new DegreePhd(null, false);
    }

    @DisplayName("DegreePhd: ObjectMapper correctly deserializes object")
    @ParameterizedTest
    @CsvSource({
            "i,xxviii,398,true",
            ",,231,false",
            ",,123,true"
    })
    void objectMapperReturnsDegreePhdWhenInputIsValid(String begin,
                                                      String end,
                                                      String pages,
                                                      boolean illustrated) throws JsonProcessingException,
            InvalidPageRangeException {

        String json = generateMonographJsonString(
                DEGREE_PHD,
                begin,
                end,
                pages,
                illustrated
        );
        DegreePhd expected = generateDegreePhd(
                begin,
                end,
                pages,
                illustrated
        );
        DegreePhd actual = objectMapper.readValue(json, DegreePhd.class);
        assertEquals(expected, actual);
    }

    @DisplayName("DegreePhd: ObjectMapper serializes valid input correctly")
    @ParameterizedTest
    @CsvSource({
            "i,xxviii,398,true",
            ",,231,false",
            ",,123,true"
    })
    void objectMapperReturnsExpectedJsonWhenInputIsValid(String begin,
                                                         String end,
                                                         String pages,
                                                         boolean illustrated) throws JsonProcessingException,
            InvalidPageRangeException {
        DegreePhd degreePhd = generateDegreePhd(
                begin,
                end,
                pages,
                illustrated
        );
        String json = objectMapper.writeValueAsString(degreePhd);
        String expected = generateMonographJsonString(
                DEGREE_PHD,
                begin,
                end,
                pages,
                illustrated);
        assertEquals(expected, json);
    }

    @Test
    void reportLogsWarningWhenPeerReviewedIsTrue() {
        new DegreePhd(null, true);
        String expected = Report.PEER_REVIEWED_FALSE.replace("{}", DegreePhd.class.getSimpleName());
        logs.assertContains(expected);
    }

    private DegreePhd generateDegreePhd(String introductionBegin,
                                        String introductionEnd,
                                        String pages,
                                        boolean illustrated) throws InvalidPageRangeException {

        return new DegreePhd.Builder()
                .withPages(generateMonographPages(introductionBegin, introductionEnd, pages, illustrated))
                .build();
    }
}

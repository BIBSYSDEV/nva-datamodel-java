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

public class DegreeMasterTest extends InstanceTest {
    private static final String DEGREE_MASTER = "DegreeMaster";

    @RegisterExtension
    LogCapturer logs = LogCapturer.create().captureForType(NonPeerReviewedMonograph.class);

    @DisplayName("DegreeMaster exists")
    @Test
    void degreeMasterExists() {
        new DegreeMaster(null, false);
    }

    @DisplayName("DegreeMaster: ObjectMapper correctly deserializes object")
    @ParameterizedTest
    @CsvSource({
            "i,xxviii,398,true",
            ",,231,false",
            ",,123,true"
    })
    void objectMapperReturnsDegreeMasterWhenInputIsValid(String begin,
                                                         String end,
                                                         String pages,
                                                         boolean illustrated) throws JsonProcessingException,
            InvalidPageRangeException {
        String json = generateMonographJsonString(
                DEGREE_MASTER,
                begin,
                end,
                pages,
                illustrated);
        DegreeMaster actual = objectMapper.readValue(json, DegreeMaster.class);
        DegreeMaster expected = generateDegreeMaster(
                begin,
                end,
                pages,
                illustrated
        );
        assertEquals(expected, actual);
    }

    @DisplayName("DegreeMaster: ObjectMapper serializes valid input correctly")
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
        DegreeMaster degreeMaster = generateDegreeMaster(
                begin,
                end,
                pages,
                illustrated);
        String json = objectMapper.writeValueAsString(degreeMaster);
        String expected = generateMonographJsonString(
                DEGREE_MASTER,
                begin,
                end,
                pages,
                illustrated);
        assertEquals(expected, json);
    }

    @Test
    void reportLogsWarningWhenPeerReviewedIsTrue() {
        new DegreeMaster(null, true);
        String expected = Report.PEER_REVIEWED_FALSE.replace("{}", DegreeMaster.class.getSimpleName());
        logs.assertContains(expected);
    }

    private DegreeMaster generateDegreeMaster(String introductionBegin,
                                              String introductionEnd,
                                              String pages,
                                              boolean illustrated) throws InvalidPageRangeException {

        return new DegreeMaster.Builder()
                .withPages(generateMonographPages(introductionBegin, introductionEnd, pages, illustrated))
                .build();
    }
}

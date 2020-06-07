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

public class DegreeBachelorTest extends InstanceTest {
    private static final String DEGREE_BACHELOR = "DegreeBachelor";

    @RegisterExtension
    LogCapturer logs = LogCapturer.create().captureForType(NonPeerReviewedMonograph.class);

    @DisplayName("DegreeBachelor exists")
    @Test
    void degreeBachelorExists() {
        new DegreeBachelor(null, false);
    }

    @DisplayName("DegreeBachelor: ObjectMapper correctly deserializes object")
    @ParameterizedTest
    @CsvSource({
            "i,xxviii,398,true",
            ",,231,false",
            ",,123,true"
    })
    void objectMapperReturnsDegreeBachelorWhenInputIsValidString(String begin,
                                                                 String end,
                                                                 String pages,
                                                                 boolean illustrated
    )
            throws JsonProcessingException, InvalidPageRangeException {
        DegreeBachelor expected = generateDegreeBachelor(
                begin,
                end,
                pages,
                illustrated
        );
        String json = generateMonographJsonString(
                DEGREE_BACHELOR,
                begin,
                end,
                pages,
                illustrated
        );
        DegreeBachelor actual = objectMapper.readValue(json, DegreeBachelor.class);
        assertEquals(expected, actual);
    }

    @DisplayName("DegreeBachelor: ObjectMapper serializes valid input correctly")
    @ParameterizedTest
    @CsvSource({
            "i,xxviii,398,true",
            ",,231,false",
            ",,123,true"
    })
    void objectMapperReturnsExpectedJsonWhenInputIsValid(String begin,
                                                         String end,
                                                         String pages,
                                                         boolean illustrated) throws
            JsonProcessingException, InvalidPageRangeException {
        DegreeBachelor degreeBachelor = generateDegreeBachelor(
                begin,
                end,
                pages,
                illustrated
        );
        String json = objectMapper.writeValueAsString(degreeBachelor);
        String expected = generateMonographJsonString(
                DEGREE_BACHELOR,
                begin,
                end,
                pages,
                illustrated
        );
        assertEquals(expected, json);
    }

    @Test
    void degreeBachelorLogsWarningWhenPeerReviewedIsTrue() {
        new DegreeBachelor(null, true);
        String expected = DegreeBachelor.PEER_REVIEWED_FALSE.replace("{}", DegreeBachelor.class.getSimpleName());
        logs.assertContains(expected);
    }

    private DegreeBachelor generateDegreeBachelor(String introductionBegin,
                                                  String introductionEnd,
                                                  String pages,
                                                  boolean illustrated) throws InvalidPageRangeException {

        return new DegreeBachelor.Builder()
                .withPages(generateMonographPages(introductionBegin, introductionEnd, pages, illustrated))
                .build();
    }
}

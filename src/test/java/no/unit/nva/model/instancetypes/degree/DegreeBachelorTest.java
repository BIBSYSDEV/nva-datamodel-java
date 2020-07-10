package no.unit.nva.model.instancetypes.degree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import no.unit.nva.model.instancetypes.InstanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.rmi.UnexpectedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DegreeBachelorTest extends InstanceTest {
    private static final String DEGREE_BACHELOR = "DegreeBachelor";

    @DisplayName("DegreeBachelor exists")
    @Test
    void degreeBachelorExists() {
        new DegreeBachelor();
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
                                                                 boolean illustrated) throws JsonProcessingException {
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
                                                         boolean illustrated) throws JsonProcessingException {
        DegreeBachelor degreeBachelor = generateDegreeBachelor(
                begin,
                end,
                pages,
                illustrated
        );
        JsonNode json = jsonStringToJsonNode(objectMapper.writeValueAsString(degreeBachelor));
        JsonNode expected = generateMonographJson(
                DEGREE_BACHELOR,
                begin,
                end,
                pages,
                illustrated,
                false
        );
        assertEquals(expected, json);
    }

    @SuppressWarnings("ConstantConditions")
    @DisplayName("DegreePhd: Attempting to set peer reviewed to true results in Unexpected exception")
    @Test
    void reportThrowsUnexpectedExceptionWhenPeerReviewedIsTrue() {
        Executable executable = () -> {
            DegreeBachelor degreeBachelor = new DegreeBachelor();
            degreeBachelor.setPeerReviewed(true);
        };
        UnexpectedException exception = assertThrows(UnexpectedException.class, executable);
        String expected = String.format(DegreeBachelor.PEER_REVIEWED_ERROR_TEMPLATE,
                DegreeBachelor.class.getSimpleName());
        assertEquals(expected, exception.getMessage());
    }

    private DegreeBachelor generateDegreeBachelor(String introductionBegin,
                                                  String introductionEnd,
                                                  String pages,
                                                  boolean illustrated) {

        return new DegreeBachelor.Builder()
                .withPages(generateMonographPages(introductionBegin, introductionEnd, pages, illustrated))
                .build();
    }
}

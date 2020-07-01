package no.unit.nva.model.instancetypes.degree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import no.unit.nva.JsonHandlingTest;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.instancetypes.InstanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.rmi.UnexpectedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DegreePhdTest extends InstanceTest implements JsonHandlingTest {
    private static final String DEGREE_PHD = "DegreePhd";

    @DisplayName("DegreePhd exists")
    @Test
    void degreePhdExists() {
        new DegreePhd();
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
        JsonNode json = jsonStringToJsonNode(objectMapper.writeValueAsString(degreePhd));
        JsonNode expected = generateMonographJson(
                DEGREE_PHD,
                begin,
                end,
                pages,
                illustrated,
                false);
        assertEquals(expected, json);
    }

    @SuppressWarnings("ConstantConditions")
    @DisplayName("DegreePhd: Attempting to set peer reviewed to true results in Unexpected exception")
    @Test
    void reportThrowsUnexpectedExceptionWhenPeerReviewedIsTrue() {
        Executable executable = () -> {
            DegreePhd degreePhd = new DegreePhd();
            degreePhd.setPeerReviewed(true);
        };
        UnexpectedException exception = assertThrows(UnexpectedException.class, executable);
        String expected = String.format(DegreePhd.PEER_REVIEWED_ERROR_TEMPLATE, DegreePhd.class.getSimpleName());
        assertEquals(expected, exception.getMessage());
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

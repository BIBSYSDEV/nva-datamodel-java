package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.instancetypes.degree.DegreeMaster;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.rmi.UnexpectedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DegreeMasterTest extends InstanceTest {
    private static final String DEGREE_MASTER = "DegreeMaster";

    @DisplayName("DegreeMaster exists")
    @Test
    void degreeMasterExists() {
        new DegreeMaster(null);
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

    @DisplayName("DegreePhd: Attempting to set peer reviewed to true results in Unexpected exception")
    @Test
    void reportThrowsUnexpectedExceptionWhenPeerReviewedIsTrue() {
        Executable executable = () -> {
            DegreeMaster degreeMaster = new DegreeMaster(null);
            degreeMaster.setPeerReviewed(true);
        };
        UnexpectedException exception = assertThrows(UnexpectedException.class, executable);
        String expected = String.format(DegreeMaster.PEER_REVIEWED_ERROR_TEMPLATE, DegreeMaster.class.getSimpleName());
        assertEquals(expected, exception.getMessage());
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

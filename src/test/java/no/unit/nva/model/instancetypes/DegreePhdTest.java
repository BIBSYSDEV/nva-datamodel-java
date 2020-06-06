package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DegreePhdTest extends BookInstanceTest {
    private static final String DEGREE_PHD = "DegreePhd";

    @DisplayName("DegreePhd exists")
    @Test
    void degreePhdExists() {
        new DegreePhd();
    }

    @DisplayName("DegreePhd: ObjectMapper correctly deserializes object")
    @ParameterizedTest
    @CsvSource({
            "i,xxviii,398,true,true,true,",
            ",,231,false,true,true",
            ",,123,true,false,false"
    })
    void objectMapperReturnsDegreePhdWhenInputIsValid(String begin,
                                                      String end,
                                                      String pages,
                                                      boolean illustrated,
                                                      boolean peerReviewed,
                                                      boolean openAccess) throws JsonProcessingException,
            InvalidPageRangeException {

        String json = generateBookInstanceJson(
                DEGREE_PHD,
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess);
        DegreePhd expected = generateDegreePhd(
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess
        );
        DegreePhd actual = objectMapper.readValue(json, DegreePhd.class);
        assertEquals(expected, actual);
    }

    @DisplayName("DegreePhd: ObjectMapper serializes valid input correctly")
    @ParameterizedTest
    @CsvSource({
            "i,xxviii,398,true,true,true,",
            ",,231,false,true,true",
            ",,123,true,false,false"
    })
    void objectMapperReturnsExpectedJsonWhenInputIsValid(String begin,
                                                         String end,
                                                         String pages,
                                                         boolean illustrated,
                                                         boolean peerReviewed,
                                                         boolean openAccess) throws JsonProcessingException,
            InvalidPageRangeException {
        DegreePhd degreePhd = generateDegreePhd(
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess
        );
        String json = objectMapper.writeValueAsString(degreePhd);
        String expected = generateBookInstanceJson(
                DEGREE_PHD,
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess);
        assertEquals(expected, json);
    }

    private DegreePhd generateDegreePhd(String introductionBegin,
                                        String introductionEnd,
                                        String pages,
                                        boolean illustrated,
                                        boolean peerReviewed,
                                        boolean openAccess) throws InvalidPageRangeException {

        return new DegreePhd.Builder()
                .withPages(generateMonographPages(pages, illustrated, introductionBegin, introductionEnd))
                .withPeerReviewed(peerReviewed)
                .withOpenAccess(openAccess)
                .build();
    }
}

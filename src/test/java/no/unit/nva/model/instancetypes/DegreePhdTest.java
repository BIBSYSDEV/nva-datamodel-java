package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Pages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            InvalidPageRangeException, InvalidPageTypeException {

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
                                                         boolean openAccess) throws InvalidPageTypeException,
            JsonProcessingException,
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

    @DisplayName("DegreePhd throws InvalidPageTypeException if pages is not MonographPages")
    @Test
    void degreePhdThrowsInvalidPageTypeExceptionWhenInputIsNotMonographPages() {
        Executable executable = () -> new DegreePhd.Builder()
                .withOpenAccess(false)
                .withPeerReviewed(false)
                .withPages(generateRange())
                .build();
        InvalidPageTypeException exception = assertThrows(InvalidPageTypeException.class, executable);
        String expectedMessage = generateInvalidPageTypeExceptionMessage(DegreePhd.class);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @DisplayName("DegreePhd does not throw InvalidPageTypeException when input is null")
    @ParameterizedTest
    @NullSource
    void degreePhdThrowsInvalidPageTypeExceptionWhenInputIsNull(Pages pages) {
        assertDoesNotThrow(
            () -> new DegreePhd.Builder()
                    .withOpenAccess(false)
                    .withPeerReviewed(false)
                    .withPages(pages)
                    .build()
        );
    }

    private DegreePhd generateDegreePhd(String introductionBegin,
                                                String introductionEnd,
                                                String pages,
                                                boolean illustrated,
                                                boolean peerReviewed,
                                                boolean openAccess) throws InvalidPageRangeException,
            InvalidPageTypeException {

        return new DegreePhd.Builder()
                .withPages(generateMonographPages(pages, illustrated, introductionBegin, introductionEnd))
                .withPeerReviewed(peerReviewed)
                .withOpenAccess(openAccess)
                .build();
    }
}

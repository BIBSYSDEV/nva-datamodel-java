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

public class DegreeBachelorTest extends BookInstanceTest {
    private static final String DEGREE_BACHELOR = "DegreeBachelor";

    @DisplayName("DegreeBachelor exists")
    @Test
    void degreeBachelorExists() {
        new DegreeBachelor();
    }

    @DisplayName("DegreeBachelor: ObjectMapper correctly deserializes object")
    @ParameterizedTest
    @CsvSource({
            "i,xxviii,398,true,true,true,",
            ",,231,false,true,true",
            ",,123,true,false,false"
    })
    void objectMapperReturnsDegreeBachelorWhenInputIsValidString(String begin,
                                                                 String end,
                                                                 String pages,
                                                                 boolean illustrated,
                                                                 boolean peerReviewed,
                                                                 boolean openAccess
    )
            throws JsonProcessingException, InvalidPageRangeException, InvalidPageTypeException {
        DegreeBachelor expected = generateDegreeBachelor(
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess
        );
        String json = generateBookInstanceJson(
                DEGREE_BACHELOR,
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess);
        DegreeBachelor actual = objectMapper.readValue(json, DegreeBachelor.class);
        assertEquals(expected, actual);
    }

    @DisplayName("DegreeBachelor: ObjectMapper serializes valid input correctly")
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
            JsonProcessingException, InvalidPageRangeException {
        DegreeBachelor degreeBachelor = generateDegreeBachelor(
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess
        );
        String json = objectMapper.writeValueAsString(degreeBachelor);
        String expected = generateBookInstanceJson(
                DEGREE_BACHELOR,
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess);
        assertEquals(expected, json);
    }

    @DisplayName("DegreeBachelor throws InvalidPageTypeException if pages is not MonographPages")
    @Test
    void degreeBachelorThrowsInvalidPageTypeExceptionWhenInputIsNotMonographPages() {
        Executable executable = () -> new DegreeBachelor.Builder()
                .withOpenAccess(false)
                .withPeerReviewed(false)
                .withPages(generateRange())
                .build();
        InvalidPageTypeException exception = assertThrows(InvalidPageTypeException.class, executable);
        String expectedMessage = generateInvalidPageTypeExceptionMessage(DegreeBachelor.class);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @DisplayName("DegreeBachelor does not throw InvalidPageTypeException when input is null")
    @ParameterizedTest
    @NullSource
    void degreeBachelorDoesNotThrowInvalidPageTypeExceptionWhenInputIsNull(Pages pages) {
        assertDoesNotThrow(
            () -> new DegreeBachelor.Builder()
                .withOpenAccess(false)
                .withPeerReviewed(false)
                .withPages(pages)
                .build()
        );
    }

    private DegreeBachelor generateDegreeBachelor(String introductionBegin,
                                                  String introductionEnd,
                                                  String pages,
                                                  boolean illustrated,
                                                  boolean peerReviewed,
                                                  boolean openAccess) throws InvalidPageRangeException,
            InvalidPageTypeException {

        return new DegreeBachelor.Builder()
                .withPages(generateMonographPages(pages, illustrated, introductionBegin, introductionEnd))
                .withPeerReviewed(peerReviewed)
                .withOpenAccess(openAccess)
                .build();
    }
}

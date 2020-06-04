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

class BookMonographTest extends BookInstanceTest {

    public static final String BOOK_MONOGRAPH = "BookMonograph";

    @DisplayName("BookMonograph exists")
    @Test
    void bookMonographExists() {
        new BookMonograph();
    }

    @DisplayName("BookMonograph: ObjectMapper correctly deserializes object")
    @ParameterizedTest
    @CsvSource({
            "i,xxviii,398,true,true,true,",
            ",,231,false,true,true",
            ",,123,true,false,false"
    })
    void objectMapperReturnsBookMonographWhenInputIsValid(String begin,
                                                          String end,
                                                          String pages,
                                                          boolean illustrated,
                                                          boolean peerReviewed,
                                                          boolean openAccess) throws JsonProcessingException,
            InvalidPageRangeException, InvalidPageTypeException {
        BookMonograph expected = generateBookMonograph(
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess
        );

        String json = generateBookInstanceJson(
                BOOK_MONOGRAPH,
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess);
        BookMonograph actual = objectMapper.readValue(json, BookMonograph.class);
        assertEquals(expected, actual);
    }

    @DisplayName("BookMonograph: ObjectMapper serializes valid input correctly")
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
            InvalidPageRangeException, JsonProcessingException {

        BookMonograph bookMonograph = generateBookMonograph(begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess);
        String json = objectMapper.writeValueAsString(bookMonograph);
        String expected = generateBookInstanceJson(BOOK_MONOGRAPH,
                begin, end, pages, illustrated, peerReviewed, openAccess);
        assertEquals(expected, json);
    }

    @DisplayName("BookMonograph throws InvalidPageTypeException if pages is not MonographPages")
    @Test
    void bookMonographThrowsInvalidPageTypeExceptionWhenInputIsNotMonographPages() {
        Executable executable = () -> new BookMonograph.Builder()
                .withOpenAccess(false)
                .withPeerReviewed(false)
                .withPages(generateRange())
                .build();
        InvalidPageTypeException exception = assertThrows(InvalidPageTypeException.class, executable);
        String expectedMessage = generateInvalidPageTypeExceptionMessage(BookMonograph.class);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @DisplayName("BookMonograph does not throw InvalidPageTypeException when input is null")
    @ParameterizedTest
    @NullSource
    void bookMonographThrowsInvalidPageTypeExceptionWhenInputIsNull(Pages pages) {
        assertDoesNotThrow(
            () -> new BookMonograph.Builder()
                    .withOpenAccess(false)
                    .withPeerReviewed(false)
                    .withPages(pages)
                    .build()
        );
    }

    private BookMonograph generateBookMonograph(String introductionBegin,
                                                String introductionEnd,
                                                String pages,
                                                boolean illustrated,
                                                boolean peerReviewed,
                                                boolean openAccess) throws InvalidPageRangeException,
            InvalidPageTypeException {

        return new BookMonograph.Builder()
                .withPages(generateMonographPages(pages, illustrated, introductionBegin, introductionEnd))
                .withPeerReviewed(peerReviewed)
                .withOpenAccess(openAccess)
                .build();
    }
}

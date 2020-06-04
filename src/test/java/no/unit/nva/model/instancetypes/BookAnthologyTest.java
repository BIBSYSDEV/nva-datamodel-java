package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookAnthologyTest extends BookInstanceTest {

    public static final String BOOK_ANTHOLOGY = "BookAnthology";

    @DisplayName("BookAnthology exists")
    @Test
    void bookAnthologyExists() {
        new BookAnthology();
    }

    @DisplayName("BookAnthology: ObjectMapper correctly deserializes object")
    @ParameterizedTest
    @CsvSource({
            "i,xxviii,398,true,true,true,",
            ",,231,false,true,true",
            ",,123,true,false,false"
    })
    void objectMapperReturnsBookAnthologyWhenInputIsValid(String begin,
                                                          String end,
                                                          String pages,
                                                          boolean illustrated,
                                                          boolean peerReviewed,
                                                          boolean openAccess) throws JsonProcessingException,
            InvalidPageRangeException, InvalidPageTypeException {

        String json = generateBookInstanceJson(
                BOOK_ANTHOLOGY,
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess);

        BookAnthology expected = generateBookAnthology(
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess
        );

        BookAnthology actual = objectMapper.readValue(json, BookAnthology.class);
        assertEquals(expected, actual);
    }

    @DisplayName("BookAnthology: ObjectMapper serializes valid input correctly")
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

        BookAnthology bookAnthology = generateBookAnthology(
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess
        );
        String json = objectMapper.writeValueAsString(bookAnthology);
        String expected = generateBookInstanceJson(
                BOOK_ANTHOLOGY,
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess);
        assertEquals(expected, json);
    }

    @DisplayName("BookAnthology throws InvalidPageTypeException if pages is not MonographPages")
    @Test
    void bookAnthologyThrowsInvalidPageTypeExceptionWhenInputIsRange() {
        Executable executable = () -> new BookAnthology.Builder()
                .withOpenAccess(false)
                .withPeerReviewed(false)
                .withPages(generateRange())
                .build();
        InvalidPageTypeException exception = assertThrows(InvalidPageTypeException.class, executable);
        String expectedMessage = generateInvalidPageTypeExceptionMessage(BookAnthology.class);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @DisplayName("BookAnthology does not throw InvalidPageTypeException when input is null")
    @ParameterizedTest
    @NullSource
    void bookAnthologyThrowsInvalidPageTypeExceptionWhenInputIsNull(Pages pages) {
        assertDoesNotThrow(
            () -> new BookAnthology.Builder()
                .withOpenAccess(false)
                .withPeerReviewed(false)
                .withPages(pages)
                .build()
        );
    }

    private BookAnthology generateBookAnthology(String introductionBegin,
                                                String introductionEnd,
                                                String pages,
                                                boolean illustrated,
                                                boolean peerReviewed,
                                                boolean openAccess) throws InvalidPageRangeException,
            InvalidPageTypeException {

        return new BookAnthology.Builder()
                .withPages(generateMonographPages(pages, illustrated, introductionBegin, introductionEnd))
                .withPeerReviewed(peerReviewed)
                .withOpenAccess(openAccess)
                .build();
    }
}

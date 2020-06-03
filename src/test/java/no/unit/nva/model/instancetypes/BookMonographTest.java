package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookMonographTest extends BookInstanceTest {

    public static final String BOOK_MONOGRAPH = "BookMonograph";

    @DisplayName("BookMonograph exists")
    @Test
    void bookMonographExists() {
        new BookMonograph();
    }

    @DisplayName("BookMonograph: ObjectMapper correctly deserializes object")
    @Test
    void objectMapperReturnsBookMonographWhenInputIsValid() throws JsonProcessingException {

        String expectedPages = "398";
        String expectedIntroductionBegin = "i";
        String expectedIntroductionEnd = "xxviii";

        Pages expectedPagesObject = generateMonographPages(expectedPages,
                true, expectedIntroductionBegin, expectedIntroductionEnd);

        String json = generateBookInstanceJson(
                BOOK_MONOGRAPH,
                expectedIntroductionBegin,
                expectedIntroductionEnd,
                expectedPages,
                true,
                false,
                false);
        BookMonograph bookMonograph = objectMapper.readValue(json, BookMonograph.class);
        assertEquals(expectedPagesObject, bookMonograph.getPages());
        assertFalse(bookMonograph.isOpenAccess());
        assertFalse(bookMonograph.isPeerReviewed());
    }

    @DisplayName("BookMonograph: ObjectMapper serializes valid input correctly")
    @Test
    void objectMapperReturnsExpectedJsonWhenInputIsValid() throws InvalidPageTypeException, JsonProcessingException {
        String expectedIntroductionBegin = "i";
        String expectedIntroductionEnd = "xxviii";
        String expectedPages = "398";
        Pages expectedPagesObject = generateMonographPages(expectedPages, false,
                expectedIntroductionBegin, expectedIntroductionEnd);
        BookMonograph bookMonograph = new BookMonograph.Builder()
                .withOpenAccess(true)
                .withPeerReviewed(true)
                .withPages(expectedPagesObject)
                .build();
        String json = objectMapper.writeValueAsString(bookMonograph);
        String expected = generateBookInstanceJson(
                BOOK_MONOGRAPH,
                expectedIntroductionBegin,
                expectedIntroductionEnd,
                expectedPages,
                false,
                true,
                true);
        assertEquals(expected, json);
    }

    @DisplayName("BookMonograph throws InvalidPageTypeException if pages is not MonographPages")
    @Test
    void bookMonographThrowsInvalidPageTypeExceptionWhenInputIsRange() {
        Range range = new Range.Builder()
                .withBegin("1")
                .withEnd("22")
                .build();
        InvalidPageTypeException exception = assertThrows(
            InvalidPageTypeException.class, () -> new BookMonograph.Builder()
                .withOpenAccess(false)
                .withPeerReviewed(false)
                .withPages(range)
                .build()
        );

        String expectedMessage = String.format(InvalidPageTypeException.INVALID_CLASS_MESSAGE,
                BookMonograph.class.getTypeName(),
                MonographPages.class.getTypeName(),
                Range.class.getTypeName());
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
}

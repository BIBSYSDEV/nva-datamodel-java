package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookMonographTest extends BookInstanceTest {

    public static final String BOOK_MONOGRAPH = "BookMonograph";
    public static final String LATIN_NUMERAL_ONE = "i";
    public static final String LATIN_NUMERAL_TWENTY_EIGHT = "xxviii";
    public static final String THREE_HUNDRED_AND_NINETY_EIGHT = "398";
    public static final String ONE = "1";
    public static final String TWENTY_TWO = "22";

    @DisplayName("BookMonograph exists")
    @Test
    void bookMonographExists() {
        new BookMonograph();
    }

    @DisplayName("BookMonograph: ObjectMapper correctly deserializes object")
    @Test
    void objectMapperReturnsBookMonographWhenInputIsValid() throws JsonProcessingException, InvalidPageRangeException {
        String expectedIntroductionBegin = LATIN_NUMERAL_ONE;
        String expectedIntroductionEnd = LATIN_NUMERAL_TWENTY_EIGHT;
        String expectedPages = THREE_HUNDRED_AND_NINETY_EIGHT;

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
    void bookMonographThrowsInvalidPageTypeExceptionWhenInputIsRange() throws InvalidPageRangeException {
        Range range = new Range.Builder()
                .withBegin(ONE)
                .withEnd(TWENTY_TWO)
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

    private BookMonograph generateBookMonograph(String introductionBegin,
                                                String introductionEnd,
                                                String pages,
                                                boolean illustrated,
                                                boolean peerReviewed,
                                                boolean openAccess) throws InvalidPageRangeException,
            InvalidPageTypeException {
        Range introduction = new Range.Builder()
                .withBegin(introductionBegin)
                .withEnd(introductionEnd)
                .build();
        Pages monographPages = new MonographPages.Builder()
                .withIntroduction(introduction)
                .withPages(pages)
                .withIllustrated(illustrated)
                .build();
        return new BookMonograph.Builder()
                .withPages(monographPages)
                .withPeerReviewed(peerReviewed)
                .withOpenAccess(openAccess)
                .build();
    }
}

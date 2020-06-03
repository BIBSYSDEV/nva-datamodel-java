package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookMonographTest {

    public static final String LATIN_NUMERAL_ONE = "i";
    public static final String LATIN_NUMERAL_TWENTY_EIGHT = "xxviii";
    public static final String THREE_HUNDRED_AND_NINETY_EIGHT = "398";
    public static final String ONE = "1";
    public static final String TWENTY_TWO = "22";
    public static final String EMPTY_STRING = "";
    private final ObjectMapper objectMapper = JsonUtils.objectMapper;

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
        Range expectedIntroductionObject = getIntroduction(expectedIntroductionBegin, expectedIntroductionEnd);
        Pages expectedPagesObject = new MonographPages.Builder()
                .withPages(expectedPages)
                .withIllustrated(false)
                .withIntroduction(expectedIntroductionObject)
                .build();

        String json = generateBookMonograph(expectedIntroductionBegin,
                expectedIntroductionEnd,
                expectedPages,
                false,
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
            JsonProcessingException, InvalidPageRangeException {
        BookMonograph bookMonograph = new BookMonograph.Builder()
                .withOpenAccess(openAccess)
                .withPeerReviewed(peerReviewed)
                .withPages(getPages(begin, end, pages, illustrated))
                .build();
        String json = objectMapper.writeValueAsString(bookMonograph);
        String expected = generateBookMonograph(begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess);
        assertEquals(expected, json);
    }

    private Pages getPages(String begin, String end, String pages, boolean illustrated) throws
            InvalidPageRangeException {
        return new MonographPages.Builder()
                    .withPages(pages)
                    .withIllustrated(illustrated)
                    .withIntroduction(getIntroduction(begin, end))
                    .build();
    }

    private Range getIntroduction(String begin, String end) throws InvalidPageRangeException {
        return new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();
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

    private String generateBookMonograph(String introductionBegin,
                                         String introductionEnd,
                                         String pages,
                                         boolean illustrated,
                                         boolean peerReviewed,
                                         boolean openAccess) {
        String introduction = EMPTY_STRING;
        if (nonNull(introductionBegin) && nonNull(introductionEnd)) {
            introduction = "    \"introduction\" : {\n"
                    + "      \"type\" : \"Range\",\n"
                    + "      \"begin\" : \"" + introductionBegin + "\",\n"
                    + "      \"end\" : \"" + introductionEnd + "\"\n"
                    + "    },\n";
        }
        return "{\n"
                + "  \"type\" : \"BookMonograph\",\n"
                + "  \"pages\" : {\n"
                + "    \"type\" : \"MonographPages\",\n"
                + introduction
                + "    \"pages\" : \"" + pages + "\",\n"
                + "    \"illustrated\" : " + illustrated + "\n"
                + "  },\n"
                + "  \"peerReviewed\" : " + peerReviewed + ",\n"
                + "  \"openAccess\" : " + openAccess + "\n"
                + "}";
    }
}

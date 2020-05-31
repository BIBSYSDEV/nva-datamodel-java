package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookMonographTest {

    private final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("BookMonograph exists")
    @Test
    void bookMonographExists() {
        new BookMonograph();
    }

    @DisplayName("BookMonograph: ObjectMapper correctly deserializes object")
    @Test
    void objectMapperReturnsBookMonographWhenInputIsValid() throws JsonProcessingException {
        String expectedIntroductionBegin = "i";
        String expectedIntroductionEnd = "xxviii";
        String expectedPages = "398";
        Range expectedIntroductionObject = new Range.Builder()
                .withBegin(expectedIntroductionBegin)
                .withEnd(expectedIntroductionEnd)
                .build();
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
    @Test
    void objectMapperReturnsExpectedJsonWhenInputIsValid() throws InvalidPageTypeException, JsonProcessingException {
        String expectedIntroductionBegin = "i";
        String expectedIntroductionEnd = "xxviii";
        String expectedPages = "398";
        Range expectedIntroductionObject = new Range.Builder()
                .withBegin(expectedIntroductionBegin)
                .withEnd(expectedIntroductionEnd)
                .build();
        Pages expectedPagesObject = new MonographPages.Builder()
                .withPages(expectedPages)
                .withIllustrated(true)
                .withIntroduction(expectedIntroductionObject)
                .build();
        BookMonograph bookMonograph = new BookMonograph.Builder()
                .withOpenAccess(true)
                .withPeerReviewed(true)
                .withPages(expectedPagesObject)
                .build();
        String json = objectMapper.writeValueAsString(bookMonograph);
        String expected = generateBookMonograph(expectedIntroductionBegin,
                expectedIntroductionEnd,
                expectedPages,
                true,
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

    private String generateBookMonograph(String introductionBegin,
                                         String introductionEnd,
                                         String pages,
                                         boolean illustrated,
                                         boolean peerReviewed,
                                         boolean openAccess) {
        return "{\n"
                + "  \"type\" : \"BookMonograph\",\n"
                + "  \"pages\" : {\n"
                + "    \"type\" : \"MonographPages\",\n"
                + "    \"introduction\" : {\n"
                + "      \"type\" : \"Range\",\n"
                + "      \"begin\" : \"" + introductionBegin + "\",\n"
                + "      \"end\" : \"" + introductionEnd + "\"\n"
                + "    },\n"
                + "    \"pages\" : \"" + pages + "\",\n"
                + "    \"illustrated\" : " + illustrated + "\n"
                + "  },\n"
                + "  \"peerReviewed\" : " + peerReviewed + ",\n"
                + "  \"openAccess\" : " + openAccess + "\n"
                + "}";
    }
}

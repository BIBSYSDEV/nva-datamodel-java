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

class BookAnthologyTest {
    
    ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("BookAnthology exists")
    @Test
    void bookAnthologyExists() {
        new BookAnthology();
    }


    @DisplayName("BookAnthology: ObjectMapper correctly deserializes object")
    @Test
    void objectMapperReturnsBookAnthologyWhenInputIsValid() throws JsonProcessingException {
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

        String json = generateBookAnthology(expectedIntroductionBegin,
                expectedIntroductionEnd,
                expectedPages,
                false,
                false,
                false);
        BookAnthology bookAnthology = objectMapper.readValue(json, BookAnthology.class);
        assertEquals(expectedPagesObject, bookAnthology.getPages());
        assertFalse(bookAnthology.isOpenAccess());
        assertFalse(bookAnthology.isPeerReviewed());
    }

    @DisplayName("BookAnthology: ObjectMapper serializes valid input correctly")
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
        BookAnthology bookAnthology = new BookAnthology.Builder()
                .withOpenAccess(true)
                .withPeerReviewed(true)
                .withPages(expectedPagesObject)
                .build();
        String json = objectMapper.writeValueAsString(bookAnthology);
        String expected = generateBookAnthology(expectedIntroductionBegin,
                expectedIntroductionEnd,
                expectedPages,
                true,
                true,
                true);
        assertEquals(expected, json);
    }

    @DisplayName("BookAnthology throws InvalidPageTypeException if pages is not MonographPages")
    @Test
    void bookAnthologyThrowsInvalidPageTypeExceptionWhenInputIsRange() {
        Range range = new Range.Builder()
                .withBegin("1")
                .withEnd("22")
                .build();
        InvalidPageTypeException exception = assertThrows(
                InvalidPageTypeException.class, () -> new BookAnthology.Builder()
                    .withOpenAccess(false)
                    .withPeerReviewed(false)
                    .withPages(range)
                    .build()
        );

        String expectedMessage = String.format(InvalidPageTypeException.INVALID_CLASS_MESSAGE,
                BookAnthology.class.getTypeName(),
                MonographPages.class.getTypeName(),
                Range.class.getTypeName());
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

    private String generateBookAnthology(String introductionBegin,
                                         String introductionEnd,
                                         String pages,
                                         boolean illustrated,
                                         boolean peerReviewed,
                                         boolean openAccess) {
        return "{\n"
                + "  \"type\" : \"BookAnthology\",\n"
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

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

public class DegreeBachelorTest extends BookInstanceTest {
    private static final String DEGREE_BACHELOR = "DegreeBachelor";

    @DisplayName("DegreeBachelor exists")
    @Test
    void degreeBachelorExists() {
        new DegreeBachelor();
    }

    @DisplayName("DegreeBachelor: ObjectMapper correctly deserializes object")
    @Test
    void objectMapperReturnsDegreeBachelorWhenInputIsValid() throws JsonProcessingException {

        String expectedPages = "398";
        String expectedIntroductionBegin = "i";
        String expectedIntroductionEnd = "xxviii";

        Pages expectedPagesObject = generateMonographPages(expectedPages,
                true, expectedIntroductionBegin, expectedIntroductionEnd);

        String json = generateBookInstanceJson(
                DEGREE_BACHELOR,
                expectedIntroductionBegin,
                expectedIntroductionEnd,
                expectedPages,
                true,
                false,
                false);
        DegreeBachelor degreeBachelor = objectMapper.readValue(json, DegreeBachelor.class);
        assertEquals(expectedPagesObject, degreeBachelor.getPages());
        assertFalse(degreeBachelor.isOpenAccess());
        assertFalse(degreeBachelor.isPeerReviewed());
    }

    @DisplayName("DegreeBachelor: ObjectMapper serializes valid input correctly")
    @Test
    void objectMapperReturnsExpectedJsonWhenInputIsValid() throws InvalidPageTypeException, JsonProcessingException {
        String expectedIntroductionBegin = "i";
        String expectedIntroductionEnd = "xxviii";
        String expectedPages = "398";
        Pages expectedPagesObject = generateMonographPages(expectedPages, false,
                expectedIntroductionBegin, expectedIntroductionEnd);
        DegreeBachelor degreeBachelor = new DegreeBachelor.Builder()
                .withOpenAccess(true)
                .withPeerReviewed(true)
                .withPages(expectedPagesObject)
                .build();
        String json = objectMapper.writeValueAsString(degreeBachelor);
        String expected = generateBookInstanceJson(
                DEGREE_BACHELOR,
                expectedIntroductionBegin,
                expectedIntroductionEnd,
                expectedPages,
                false,
                true,
                true);
        assertEquals(expected, json);
    }

    @DisplayName("DegreeBachelor throws InvalidPageTypeException if pages is not MonographPages")
    @Test
    void degreeBachelorThrowsInvalidPageTypeExceptionWhenInputIsRange() {
        Range range = new Range.Builder()
                .withBegin("1")
                .withEnd("22")
                .build();
        InvalidPageTypeException exception = assertThrows(
                InvalidPageTypeException.class, () -> new DegreeBachelor.Builder()
                        .withOpenAccess(false)
                        .withPeerReviewed(false)
                        .withPages(range)
                        .build()
        );

        String expectedMessage = String.format(InvalidPageTypeException.INVALID_CLASS_MESSAGE,
                DegreeBachelor.class.getTypeName(),
                MonographPages.class.getTypeName(),
                Range.class.getTypeName());
        assertEquals(expectedMessage, exception.getMessage());
    }

    @DisplayName("DegreeBachelor does not throw InvalidPageTypeException when input is null")
    @ParameterizedTest
    @NullSource
    void degreeBachelorThrowsInvalidPageTypeExceptionWhenInputIsNull(Pages pages) {
        assertDoesNotThrow(
                () -> new DegreeBachelor.Builder()
                        .withOpenAccess(false)
                        .withPeerReviewed(false)
                        .withPages(pages)
                        .build()
        );
    }
}

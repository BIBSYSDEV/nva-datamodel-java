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
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DegreeMasterTest extends BookInstanceTest {
    private static final String DEGREE_MASTER = "DegreeMaster";

    @DisplayName("DegreeMaster exists")
    @Test
    void degreeMasterExists() {
        new DegreeMaster();
    }

    @DisplayName("DegreeMaster: ObjectMapper correctly deserializes object")
    @Test
    void objectMapperReturnsDegreeMasterWhenInputIsValid() throws JsonProcessingException, InvalidPageRangeException {

        String expectedPages = "398";
        String expectedIntroductionBegin = "i";
        String expectedIntroductionEnd = "xxviii";

        Pages expectedPagesObject = generateMonographPages(expectedPages,
                true, expectedIntroductionBegin, expectedIntroductionEnd);

        String json = generateBookInstanceJson(
                DEGREE_MASTER,
                expectedIntroductionBegin,
                expectedIntroductionEnd,
                expectedPages,
                true,
                false,
                false);
        DegreeMaster degreeMaster = objectMapper.readValue(json, DegreeMaster.class);
        assertEquals(expectedPagesObject, degreeMaster.getPages());
        assertFalse(degreeMaster.isOpenAccess());
        assertFalse(degreeMaster.isPeerReviewed());
    }

    @DisplayName("DegreeMaster: ObjectMapper serializes valid input correctly")
    @Test
    void objectMapperReturnsExpectedJsonWhenInputIsValid() throws InvalidPageTypeException, JsonProcessingException,
            InvalidPageRangeException {
        String expectedIntroductionBegin = "i";
        String expectedIntroductionEnd = "xxviii";
        String expectedPages = "398";
        Pages expectedPagesObject = generateMonographPages(expectedPages, false,
                expectedIntroductionBegin, expectedIntroductionEnd);
        DegreeMaster degreeMaster = new DegreeMaster.Builder()
                .withOpenAccess(true)
                .withPeerReviewed(true)
                .withPages(expectedPagesObject)
                .build();
        String json = objectMapper.writeValueAsString(degreeMaster);
        String expected = generateBookInstanceJson(
                DEGREE_MASTER,
                expectedIntroductionBegin,
                expectedIntroductionEnd,
                expectedPages,
                false,
                true,
                true);
        assertEquals(expected, json);
    }

    @DisplayName("DegreeMaster throws InvalidPageTypeException if pages is not MonographPages")
    @Test
    void degreeMasterThrowsInvalidPageTypeExceptionWhenInputIsRange() throws InvalidPageRangeException {
        Range range = new Range.Builder()
                .withBegin("1")
                .withEnd("22")
                .build();
        InvalidPageTypeException exception = assertThrows(
                InvalidPageTypeException.class, () -> new DegreeMaster.Builder()
                        .withOpenAccess(false)
                        .withPeerReviewed(false)
                        .withPages(range)
                        .build()
        );

        String expectedMessage = String.format(InvalidPageTypeException.INVALID_CLASS_MESSAGE,
                DegreeMaster.class.getTypeName(),
                MonographPages.class.getTypeName(),
                Range.class.getTypeName());
        assertEquals(expectedMessage, exception.getMessage());
    }

    @DisplayName("DegreeMaster does not throw InvalidPageTypeException when input is null")
    @ParameterizedTest
    @NullSource
    void degreeMasterThrowsInvalidPageTypeExceptionWhenInputIsNull(Pages pages) {
        assertDoesNotThrow(
            () -> new DegreeMaster.Builder()
                    .withOpenAccess(false)
                    .withPeerReviewed(false)
                    .withPages(pages)
                    .build()
        );
    }
}

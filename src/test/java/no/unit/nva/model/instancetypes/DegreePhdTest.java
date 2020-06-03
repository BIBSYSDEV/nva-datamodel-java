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

public class DegreePhdTest extends BookInstanceTest {
    private static final String DEGREE_PHD = "DegreePhd";

    @DisplayName("DegreePhd exists")
    @Test
    void degreePhdExists() {
        new DegreePhd();
    }

    @DisplayName("DegreePhd: ObjectMapper correctly deserializes object")
    @Test
    void objectMapperReturnsDegreePhdWhenInputIsValid() throws JsonProcessingException, InvalidPageRangeException {

        String expectedPages = "398";
        String expectedIntroductionBegin = "i";
        String expectedIntroductionEnd = "xxviii";

        Pages expectedPagesObject = generateMonographPages(expectedPages,
                true, expectedIntroductionBegin, expectedIntroductionEnd);

        String json = generateBookInstanceJson(
                DEGREE_PHD,
                expectedIntroductionBegin,
                expectedIntroductionEnd,
                expectedPages,
                true,
                false,
                false);
        DegreePhd degreePhd = objectMapper.readValue(json, DegreePhd.class);
        assertEquals(expectedPagesObject, degreePhd.getPages());
        assertFalse(degreePhd.isOpenAccess());
        assertFalse(degreePhd.isPeerReviewed());
    }

    @DisplayName("DegreePhd: ObjectMapper serializes valid input correctly")
    @Test
    void objectMapperReturnsExpectedJsonWhenInputIsValid() throws InvalidPageTypeException, JsonProcessingException,
            InvalidPageRangeException {
        String expectedIntroductionBegin = "i";
        String expectedIntroductionEnd = "xxviii";
        String expectedPages = "398";
        Pages expectedPagesObject = generateMonographPages(expectedPages, false,
                expectedIntroductionBegin, expectedIntroductionEnd);
        DegreePhd degreePhd = new DegreePhd.Builder()
                .withOpenAccess(true)
                .withPeerReviewed(true)
                .withPages(expectedPagesObject)
                .build();
        String json = objectMapper.writeValueAsString(degreePhd);
        String expected = generateBookInstanceJson(
                DEGREE_PHD,
                expectedIntroductionBegin,
                expectedIntroductionEnd,
                expectedPages,
                false,
                true,
                true);
        assertEquals(expected, json);
    }

    @DisplayName("DegreePhd throws InvalidPageTypeException if pages is not MonographPages")
    @Test
    void degreePhdThrowsInvalidPageTypeExceptionWhenInputIsRange() throws InvalidPageRangeException {
        Range range = new Range.Builder()
                .withBegin("1")
                .withEnd("22")
                .build();
        InvalidPageTypeException exception = assertThrows(
                InvalidPageTypeException.class, () -> new DegreePhd.Builder()
                        .withOpenAccess(false)
                        .withPeerReviewed(false)
                        .withPages(range)
                        .build()
        );

        String expectedMessage = String.format(InvalidPageTypeException.INVALID_CLASS_MESSAGE,
                DegreePhd.class.getTypeName(),
                MonographPages.class.getTypeName(),
                Range.class.getTypeName());
        assertEquals(expectedMessage, exception.getMessage());
    }

    @DisplayName("DegreePhd does not throw InvalidPageTypeException when input is null")
    @ParameterizedTest
    @NullSource
    void degreePhdThrowsInvalidPageTypeExceptionWhenInputIsNull(Pages pages) {
        assertDoesNotThrow(
            () -> new DegreePhd.Builder()
                    .withOpenAccess(false)
                    .withPeerReviewed(false)
                    .withPages(pages)
                    .build()
        );
    }
}

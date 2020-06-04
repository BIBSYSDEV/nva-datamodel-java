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
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DegreeMasterTest extends BookInstanceTest {
    private static final String DEGREE_MASTER = "DegreeMaster";
    public static final String ONE = "1";
    public static final String TWENTY_TWO = "22";

    @DisplayName("DegreeMaster exists")
    @Test
    void degreeMasterExists() {
        new DegreeMaster();
    }

    @DisplayName("DegreeMaster: ObjectMapper correctly deserializes object")
    @ParameterizedTest
    @CsvSource({
            "i,xxviii,398,true,true,true,",
            ",,231,false,true,true",
            ",,123,true,false,false"
    })
    void objectMapperReturnsDegreeMasterWhenInputIsValid(String begin,
                                                         String end,
                                                         String pages,
                                                         boolean illustrated,
                                                         boolean peerReviewed,
                                                         boolean openAccess) throws JsonProcessingException,
            InvalidPageRangeException, InvalidPageTypeException {
        String json = generateBookInstanceJson(
                DEGREE_MASTER,
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess);
        DegreeMaster actual = objectMapper.readValue(json, DegreeMaster.class);
        DegreeMaster expected = generateDegreeMaster(
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess
        );
        assertEquals(expected, actual);
    }

    @DisplayName("DegreeMaster: ObjectMapper serializes valid input correctly")
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
        DegreeMaster degreeMaster = generateDegreeMaster(
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess
        );
        String json = objectMapper.writeValueAsString(degreeMaster);
        String expected = generateBookInstanceJson(
                DEGREE_MASTER,
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                openAccess);
        assertEquals(expected, json);
    }

    @DisplayName("DegreeMaster throws InvalidPageTypeException if pages is not MonographPages")
    @Test
    void degreeMasterThrowsInvalidPageTypeExceptionWhenInputIsRange() {

        InvalidPageTypeException exception = assertThrows(
                InvalidPageTypeException.class, () -> new DegreeMaster.Builder()
                        .withOpenAccess(false)
                        .withPeerReviewed(false)
                        .withPages(generateRange(ONE, TWENTY_TWO))
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

    private DegreeMaster generateDegreeMaster(String introductionBegin,
                                              String introductionEnd,
                                              String pages,
                                              boolean illustrated,
                                              boolean peerReviewed,
                                              boolean openAccess) throws InvalidPageRangeException,
            InvalidPageTypeException {

        return new DegreeMaster.Builder()
                .withPages(generateMonographPages(pages, illustrated, introductionBegin, introductionEnd))
                .withPeerReviewed(peerReviewed)
                .withOpenAccess(openAccess)
                .build();
    }
}

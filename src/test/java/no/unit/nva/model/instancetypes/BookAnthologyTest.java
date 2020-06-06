package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
            InvalidPageRangeException {

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
                                                         boolean openAccess) throws JsonProcessingException,
            InvalidPageRangeException {

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

    private BookAnthology generateBookAnthology(String introductionBegin,
                                                String introductionEnd,
                                                String pages,
                                                boolean illustrated,
                                                boolean peerReviewed,
                                                boolean openAccess) throws InvalidPageRangeException {

        return new BookAnthology.Builder()
                .withPages(generateMonographPages(pages, illustrated, introductionBegin, introductionEnd))
                .withPeerReviewed(peerReviewed)
                .withOpenAccess(openAccess)
                .build();
    }
}

package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookAnthologyTest extends InstanceTest {

    public static final String BOOK_ANTHOLOGY = "BookAnthology";

    @DisplayName("BookAnthology exists")
    @Test
    void bookAnthologyExists() {
        new BookAnthology(null, false);
    }

    @DisplayName("BookAnthology: ObjectMapper correctly deserializes object")
    @ParameterizedTest(name = "BookAnthology deserialized with begin {0}, end {1}, pages {2}, illustrated {3}, "
            + "peerReviewed {4}")
    @CsvSource({
            "i,xxviii,398,true,true",
            ",,231,false,true",
            ",,123,true,false"
    })
    void objectMapperReturnsBookAnthologyWhenInputIsValid(String begin,
                                                          String end,
                                                          String pages,
                                                          boolean illustrated,
                                                          boolean peerReviewed) throws JsonProcessingException,
            InvalidPageRangeException {

        String json = generateMonographJsonString(
                BOOK_ANTHOLOGY,
                begin,
                end,
                pages,
                illustrated,
                peerReviewed);

        BookAnthology expected = generateBookAnthology(
                begin,
                end,
                pages,
                illustrated,
                peerReviewed
        );

        BookAnthology actual = objectMapper.readValue(json, BookAnthology.class);
        assertEquals(expected, actual);
    }

    @DisplayName("BookAnthology: ObjectMapper serializes valid input correctly")
    @ParameterizedTest(name = "BookAnthology serialized with begin {0}, end {1}, pages {2}, illustrated {3}, "
            + "peerReviewed {4}")
    @CsvSource({
            "i,xxviii,398,true,true",
            ",,231,false,true",
            ",,123,true,false"
    })
    void objectMapperReturnsExpectedJsonWhenInputIsValid(String begin,
                                                         String end,
                                                         String pages,
                                                         boolean illustrated,
                                                         boolean peerReviewed) throws JsonProcessingException,
            InvalidPageRangeException {

        BookAnthology bookAnthology = generateBookAnthology(
                begin,
                end,
                pages,
                illustrated,
                peerReviewed
        );
        String json = objectMapper.writeValueAsString(bookAnthology);
        String expected = generateMonographJsonString(
                BOOK_ANTHOLOGY,
                begin,
                end,
                pages,
                illustrated,
                peerReviewed);
        assertEquals(expected, json);
    }

    private BookAnthology generateBookAnthology(String introductionBegin,
                                                String introductionEnd,
                                                String pages,
                                                boolean illustrated,
                                                boolean peerReviewed) throws InvalidPageRangeException {

        return new BookAnthology.Builder()
                .withPages(generateMonographPages(introductionBegin, introductionEnd, pages, illustrated))
                .withPeerReviewed(peerReviewed)
                .build();
    }
}
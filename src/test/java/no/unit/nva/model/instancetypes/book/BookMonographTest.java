package no.unit.nva.model.instancetypes.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import no.unit.nva.model.instancetypes.InstanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookMonographTest extends InstanceTest {

    public static final String BOOK_MONOGRAPH = "BookMonograph";

    @DisplayName("BookMonograph exists")
    @Test
    void bookMonographExists() {
        new BookMonograph();
    }

    @DisplayName("BookMonograph: ObjectMapper correctly deserializes object")
    @ParameterizedTest(name = "BookMonograph deserializes begin {0}, end {1}, pages {2}, illustrated {3}, "
            + "peerReviewed {4}")
    @CsvSource({
            "i,xxviii,398,true,true",
            ",,231,false,true",
            ",,123,true,false"
    })
    void objectMapperReturnsBookMonographWhenInputIsValid(String begin,
                                                          String end,
                                                          String pages,
                                                          boolean illustrated,
                                                          boolean peerReviewed) throws JsonProcessingException {
        BookMonograph expected = generateBookMonograph(
                begin,
                end,
                pages,
                illustrated,
                peerReviewed
        );

        String json = generateMonographJsonString(
                BOOK_MONOGRAPH,
                begin,
                end,
                pages,
                illustrated,
                peerReviewed
        );
        BookMonograph actual = objectMapper.readValue(json, BookMonograph.class);
        assertEquals(expected, actual);
    }

    @DisplayName("BookMonograph: ObjectMapper serializes valid input correctly")
    @ParameterizedTest(name = "BookMonograph serializes begin {0}, end {1}, pages {2}, illustrated {3}, "
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
                                                         boolean peerReviewed) throws JsonProcessingException {

        BookMonograph bookMonograph = generateBookMonograph(begin,
                end,
                pages,
                illustrated,
                peerReviewed
        );
        JsonNode json = jsonStringToJsonNode(objectMapper.writeValueAsString(bookMonograph));
        JsonNode expected = generateMonographJson(BOOK_MONOGRAPH,
                begin, end, pages, illustrated, peerReviewed);
        assertEquals(expected, json);
    }

    private BookMonograph generateBookMonograph(String introductionBegin,
                                                String introductionEnd,
                                                String pages,
                                                boolean illustrated,
                                                boolean peerReviewed) {

        return new BookMonograph.Builder()
                .withPages(generateMonographPages(introductionBegin, introductionEnd, pages, illustrated))
                .withPeerReviewed(peerReviewed)
                .build();
    }
}
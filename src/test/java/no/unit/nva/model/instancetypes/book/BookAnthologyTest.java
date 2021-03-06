package no.unit.nva.model.instancetypes.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import no.unit.nva.JsonHandlingTest;
import no.unit.nva.model.instancetypes.InstanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookAnthologyTest extends InstanceTest implements JsonHandlingTest {

    public static final String BOOK_ANTHOLOGY = "BookAnthology";

    @DisplayName("BookAnthology exists")
    @Test
    void bookAnthologyExists() {
        new BookAnthology();
    }

    @DisplayName("BookAnthology: ObjectMapper correctly deserializes object")
    @ParameterizedTest(name = "BookAnthology deserialized with begin {0}, end {1}, pages {2}, illustrated {3}, "
            + "peerReviewed {4}, textbook {5}")
    @CsvSource({
            "i,xxviii,398,true,true,true",
            ",,231,false,true,true",
            ",,123,true,false,true",
            ",,123,true,false,false"
    })
    void objectMapperReturnsBookAnthologyWhenInputIsValid(String begin,
                                                          String end,
                                                          String pages,
                                                          boolean illustrated,
                                                          boolean peerReviewed,
                                                          boolean textbookContent) throws JsonProcessingException {

        String json = generateMonographJsonString(
                BOOK_ANTHOLOGY,
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                textbookContent);

        BookAnthology expected = generateBookAnthology(
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                textbookContent
        );

        BookAnthology actual = objectMapper.readValue(json, BookAnthology.class);
        assertThat(expected, samePropertyValuesAs(actual));
    }

    @DisplayName("BookAnthology: ObjectMapper serializes valid input correctly")
    @ParameterizedTest(name = "BookAnthology serialized with begin {0}, end {1}, pages {2}, illustrated {3}, "
            + "peerReviewed {4}, textbook {5}")
    @CsvSource({
            "i,xxviii,398,true,true,true",
            ",,231,false,true,true",
            ",,123,true,false,true",
            ",,123,true,false,false"
    })
    void objectMapperReturnsExpectedJsonWhenInputIsValid(String begin,
                                                         String end,
                                                         String pages,
                                                         boolean illustrated,
                                                         boolean peerReviewed,
                                                         boolean textbookContent) {

        BookAnthology bookAnthology = generateBookAnthology(
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                textbookContent
        );
        JsonNode actual = objectMapper.convertValue(bookAnthology, JsonNode.class);
        JsonNode expected = generateMonographJson(BOOK_ANTHOLOGY,
                begin, end, pages, illustrated, peerReviewed, textbookContent);
        assertEquals(expected, actual);
    }

    private BookAnthology generateBookAnthology(String introductionBegin,
                                                String introductionEnd,
                                                String pages,
                                                boolean illustrated,
                                                boolean peerReviewed,
                                                boolean textbookContent) {

        return new BookAnthology.Builder()
                .withPages(generateMonographPages(introductionBegin, introductionEnd, pages, illustrated))
                .withPeerReviewed(peerReviewed)
                .withTextbookContent(textbookContent)
                .build();
    }
}

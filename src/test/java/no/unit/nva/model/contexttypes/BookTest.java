package no.unit.nva.model.contexttypes;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.Level;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final int FIRST_ELEMENT = 0;

    @DisplayName("Book can deserialize a book")
    @Test
    void objectMapperReturnsBookWhenInputIsValidJson() throws JsonProcessingException {
        String expectedType = "Book";
        String expectedTitle = "The history of JSON in this project";
        Level expectedLevel = Level.LEVEL_2;
        String expectedIsbn = "123 123123 1112";
        String json = generateJsonFromTemplate(null, expectedLevel,
                true, true, expectedIsbn);
        Book book = objectMapper.readValue(json, Book.class);
        assertNull(book.getSeriesTitle());
        assertEquals(expectedIsbn, book.getIsbnList().get(FIRST_ELEMENT));
        assertEquals(expectedLevel, book.getLevel());
        assertTrue(book.isOpenAccess());
        assertTrue(book.isPeerReviewed());
    }

    @DisplayName("Book serializes expected json")
    @Test
    void objectMapperProducesProperlyFormattedJsonWhenInputIsBook() throws JsonProcessingException {
        String expectedSeriesTitle = "A series title";
        Level expectedLevel = Level.NO_LEVEL;
        String expectedIsbn = "123 123123 1112";
        Book book = new Book.Builder()
                .withSeriesTitle(expectedSeriesTitle)
                .withLevel(expectedLevel)
                .withOpenAccess(false)
                .withPeerReviewed(false)
                .withIsbnList(List.of(expectedIsbn))
                .build();
        String expectedJson = generateJsonFromTemplate(expectedSeriesTitle, expectedLevel,
                false, false, expectedIsbn);
        assertEquals(expectedJson, objectMapper.writeValueAsString(book));
    }


    private String generateJsonFromTemplate(String seriesTitle,
                                            Level level,
                                            boolean openAccess,
                                            boolean peerReviewed,
                                            String isbn) throws JsonProcessingException {
        Book book = new Book.Builder()
                .withSeriesTitle(seriesTitle)
                .withLevel(level)
                .withOpenAccess(openAccess)
                .withPeerReviewed(peerReviewed)
                .withIsbnList(List.of(isbn))
                .build();

        return objectMapper.writeValueAsString(book);
    }
}
package no.unit.nva.model.contexttypes;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.Level;
import nva.commons.utils.IoUtils;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String BOOK_JSON = "book.json.template";
    public static final int FIRST_ELEMENT = 0;

    @DisplayName("Book can deserialize a book")
    @Test
    void objectMapperReturnsBookWhenInputIsValidJson() throws JsonProcessingException {
        String expectedType = "Book";
        String expectedTitle = "The history of JSON in this project";
        Level expectedLevel = Level.LEVEL_2;
        String expectedIsbn = "123 123123 1112";
        String json = generateJsonFromTemplate(expectedType, expectedTitle, expectedLevel,
                true, true, expectedIsbn);
        Book book = objectMapper.readValue(json, Book.class);
        assertEquals(expectedTitle, book.getTitle());
        assertEquals(expectedIsbn, book.getIsbns().get(FIRST_ELEMENT));
        assertEquals(expectedLevel, book.getLevel());
        assertTrue(book.isOpenAccess());
        assertTrue(book.isPeerReviewed());
    }

    @DisplayName("Book serializes expected json")
    @Test
    void objectMapperProducesProperlyFormattedJsonWhenInputIsBook() throws JsonProcessingException {
        String expectedType = "Book";
        String expectedTitle = "The history of JSON in this project";
        Level expectedLevel = Level.NO_LEVEL;
        String expectedIsbn = "123 123123 1112";
        Book book = new Book(expectedTitle, expectedLevel, false, false, List.of(expectedIsbn));
        String expectedJson = generateJsonFromTemplate(expectedType, expectedTitle, expectedLevel,
                false, false, expectedIsbn);
        assertEquals(expectedJson, objectMapper.writeValueAsString(book));
    }


    private String generateJsonFromTemplate(String type,
                                            String title,
                                            Level level,
                                            boolean openAccess,
                                            boolean peerReviewed,
                                            String isbn) {
        String template = IoUtils.stringFromResources(Path.of(BOOK_JSON));
        return String.format(template, type, title, level, openAccess, peerReviewed, isbn);
    }
}
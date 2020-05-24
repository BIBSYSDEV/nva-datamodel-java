package no.unit.nva.model.contexttypes;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.Level;
import no.unit.nva.model.NullSeries;
import no.unit.nva.model.Series;
import no.unit.nva.model.SeriesImpl;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        String json = generateJsonFromTemplate(expectedType, expectedTitle, null, expectedLevel,
                true, true, expectedIsbn);
        Book book = objectMapper.readValue(json, Book.class);
        assertEquals(expectedTitle, book.getTitle());
        assertEquals(NullSeries.class, book.getSeries().getClass());
        assertEquals(expectedIsbn, book.getIsbns().get(FIRST_ELEMENT));
        assertEquals(expectedLevel, book.getLevel());
        assertTrue(book.isOpenAccess());
        assertTrue(book.isPeerReviewed());
    }

    @DisplayName("Book serializes expected json")
    @Test
    void objectMapperProducesProperlyFormattedJsonWhenInputIsBook() throws JsonProcessingException {
        String expectedType = "Book";
        String expectedSeriesTitle = "A series title";
        String expectedTitle = "The history of JSON in this project";
        Level expectedLevel = Level.NO_LEVEL;
        String expectedIsbn = "123 123123 1112";
        Book book = new Book.Builder()
                .withTitle(expectedTitle)
                .withLevel(expectedLevel)
                .withOpenAccess(false)
                .withPeerReviewed(false)
                .withSeries(new SeriesImpl(expectedSeriesTitle, null))
                .withIsbns(List.of(expectedIsbn))
                .build();
        String expectedJson = generateJsonFromTemplate(expectedType, expectedTitle, expectedSeriesTitle, expectedLevel,
                false, false, expectedIsbn);
        assertEquals(expectedJson, objectMapper.writeValueAsString(book));
    }


    private String generateJsonFromTemplate(String type,
                                            String title,
                                            String seriesTitle,
                                            Level level,
                                            boolean openAccess,
                                            boolean peerReviewed,
                                            String isbn) throws JsonProcessingException {
        Series series = Objects.nonNull(seriesTitle) ? new SeriesImpl(seriesTitle, null) : null;
        Book book = new Book.Builder()
                .withTitle(title)
                .withSeries(series)
                .withLevel(level)
                .withOpenAccess(openAccess)
                .withPeerReviewed(peerReviewed)
                .withIsbns(List.of(isbn))
                .build();

        return objectMapper.writeValueAsString(book);
    }
}
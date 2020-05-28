package no.unit.nva.model.contexttypes;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.Level;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String SEPARATOR = "\\|";
    public static final String QUOTE = "\"";
    public static final String EMPTY_STRING = "";
    public static final String COMMA_SPACE = ", ";
    public static final String KEY_VALUE_STRING_PAIR_TEMPLATE = "  \"%s\" : \"%s\",\n";
    private static final String KEY_VALUE_BOOLEAN_PAIR_TEMPLATE = "  \"%s\" : %s,\n";
    private static final String KEY_VALUE_LIST_PAIR_TEMPLATE = "  \"%s\" : [ %s ]\n";
    public static final String PROLOGUE = "{\n"
            + "  \"type\" : \"Book\",\n";
    public static final String EPILOGUE = "}";
    public static final String SERIES_TITLE = "seriesTitle";
    public static final String SERIES_NUMBER = "seriesNumber";
    public static final String PUBLISHER = "publisher";
    public static final String LEVEL = "level";
    public static final String OPEN_ACCESS = "openAccess";
    public static final String PEER_REVIEWED = "peerReviewed";
    public static final String ISBN_LIST = "isbnList";

    @DisplayName("Book can deserialize a book")
    @ParameterizedTest
    @CsvSource({
            "A series title,123,Full publisher details,LEVEL_2,true,true,\"9780201309515|9788131700075\"",
            ",, Full publisher details, LEVEL_2, true, true, \"9780201309515|9788131700075\"",
            "A series title,,Full publisher details,LEVEL_2,true,true,\"9780201309515|9788131700075\"",
            "Fulong of Oolong,12,T Publishing,LEVEL_0,false,false,\"9780201309515|9788131700075\""
    })
    void objectMapperReturnsBookWhenInputIsValidJson(String seriesTitle,
                                                     String seriesNumber,
                                                     String publisher,
                                                     String level,
                                                     String openAccess,
                                                     String peerReviewed,
                                                     String isbnList) throws JsonProcessingException {
        Level expectedLevel = Level.valueOf(level);
        boolean expectedOpenAccess = Boolean.getBoolean(openAccess);
        boolean expectedPeerReviewed = Boolean.getBoolean(peerReviewed);
        List<String> expectedIsbn = convertIsbnStringToList(isbnList);
        String json = generateBookJson(
                seriesTitle,
                seriesNumber,
                publisher,
                expectedLevel,
                expectedOpenAccess,
                expectedPeerReviewed,
                expectedIsbn
        );
        Book book = objectMapper.readValue(json, Book.class);
        assertEquals(seriesTitle, book.getSeriesTitle());
        assertEquals(seriesNumber, book.getSeriesNumber());
        assertEquals(expectedLevel, book.getLevel());
        assertEquals(expectedIsbn, book.getIsbnList());
        assertEquals(expectedOpenAccess, book.isOpenAccess());
        assertEquals(expectedPeerReviewed, book.isPeerReviewed());
    }

    private ArrayList<String> convertIsbnStringToList(String isbnList) {
        String unquoted = isbnList.replaceAll(QUOTE, EMPTY_STRING);
        String[] split = unquoted.split(SEPARATOR);
        return new ArrayList<>(Arrays.asList(split));
    }

    @DisplayName("Book serializes expected json")
    @ParameterizedTest
    @CsvSource({
            "A series title,123,Full publisher details,LEVEL_2,true,true,\"9780201309515|9788131700075\"",
            ",, Full publisher details, LEVEL_2, true, true, \"9780201309515|9788131700075\"",
            "A series title,,Full publisher details,LEVEL_2,true,true,\"9780201309515|9788131700075\"",
            "Fulong of Oolong,12,T Publishing,LEVEL_0,false,false,\"9780201309515|9788131700075\""
    })
    void objectMapperProducesProperlyFormattedJsonWhenInputIsBook(String seriesTitle,
                                                                  String seriesNumber,
                                                                  String publisher,
                                                                  String level,
                                                                  String openAccess,
                                                                  String peerReviewed,
                                                                  String isbnList) throws JsonProcessingException,
            InvalidIsbnException {
        List<String> expectedIsbnList = convertIsbnStringToList(isbnList);
        boolean expectedOpenAccess = Boolean.getBoolean(openAccess);
        boolean expectedPeerReviewed = Boolean.getBoolean(peerReviewed);
        Level expectedLevel = Level.valueOf(level);
        Book book = new Book.Builder()
                .withSeriesTitle(seriesTitle)
                .withSeriesNumber(seriesNumber)
                .withPublisher(publisher)
                .withLevel(expectedLevel)
                .withOpenAccess(expectedOpenAccess)
                .withPeerReviewed(expectedPeerReviewed)
                .withIsbnList(new ArrayList<>(expectedIsbnList))
                .build();
        String expectedJson = generateBookJson(
                seriesTitle,
                seriesNumber,
                publisher,
                expectedLevel,
                expectedOpenAccess,
                expectedPeerReviewed,
                expectedIsbnList
        );
        assertEquals(expectedJson, objectMapper.writeValueAsString(book));
    }

    String generateBookJson(String seriesTitle,
                            String seriesNumber,
                            String publisher,
                            Level level,
                            boolean openAccess,
                            boolean peerReviewed,
                            List<String> isbnList) {

        return PROLOGUE + generateKeyValuePair(SERIES_TITLE, seriesTitle)
                + generateKeyValuePair(SERIES_NUMBER, seriesNumber)
                + generateKeyValuePair(PUBLISHER, publisher)
                + generateKeyValuePair(LEVEL, level.toString())
                + generateKeyValuePair(OPEN_ACCESS, openAccess)
                + generateKeyValuePair(PEER_REVIEWED, peerReviewed)
                + generateKeyValueListPair(isbnList)
                + EPILOGUE;
    }

    private String generateKeyValuePair(String key, Object value) {
        if (nonNull(value) && value instanceof String) {
            return String.format(KEY_VALUE_STRING_PAIR_TEMPLATE, key, value);
        }
        if (nonNull(value) && value instanceof Boolean) {
            return String.format(KEY_VALUE_BOOLEAN_PAIR_TEMPLATE, key, value);
        }
        return EMPTY_STRING;
    }

    private String generateKeyValueListPair(List<String> value) {
        if (nonNull(value)) {
            String isbnListString = value.stream()
                    .map(isbn -> QUOTE + isbn + QUOTE)
                    .collect(Collectors.joining(COMMA_SPACE));
            return String.format(KEY_VALUE_LIST_PAIR_TEMPLATE, BookTest.ISBN_LIST, isbnListString);
        }
        return EMPTY_STRING;
    }

}
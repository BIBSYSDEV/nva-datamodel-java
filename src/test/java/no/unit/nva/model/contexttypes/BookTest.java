package no.unit.nva.model.contexttypes;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.Level;
import no.unit.nva.model.ModelTest;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import nva.commons.core.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static no.unit.nva.model.util.PublicationGenerator.convertIsbnStringToList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookTest extends ModelTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String BOOK = "Book";
    public static final String SAMPLE_LINKED_CONTEXT = "http://example.com/context";

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
        String json = generatePublicationJson(
                BOOK,
                seriesTitle,
                seriesNumber,
                publisher,
                level,
                expectedOpenAccess,
                expectedPeerReviewed,
                expectedIsbn,
                null,
                null,
                SAMPLE_LINKED_CONTEXT
        );
        Book book = objectMapper.readValue(json, Book.class);
        assertEquals(seriesTitle, book.getSeriesTitle());
        assertEquals(seriesNumber, book.getSeriesNumber());
        assertEquals(expectedLevel, book.getLevel());
        assertEquals(expectedIsbn, book.getIsbnList());
        assertEquals(expectedOpenAccess, book.isOpenAccess());
        assertEquals(expectedPeerReviewed, book.isPeerReviewed());
    }

    @DisplayName("Book serializes expected json")
    @ParameterizedTest
    @CsvSource({
            "A series title,123,Full publisher details,LEVEL_2,true,true,\"9780201309515|9788131700075\"",
            ",, Full publisher details, LEVEL_2, true, true, \"9780201309515|9788131700075\"",
            "A series title,,Full publisher details,LEVEL_2,true,true,\"9780201309515|9788131700075\"",
            "Fulong of Oolong,12,T Publishing,LEVEL_0,false,false,\"9780201309515|9788131700075\"",
            "A Marxist analysis of marking systems,6903,ACO,LEVEL_1,true,false,"
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
                .withIsbnList(expectedIsbnList)
                .withLinkedContext(SAMPLE_LINKED_CONTEXT)
                .build();
        String expectedJson = generatePublicationJson(
                BOOK,
                seriesTitle,
                seriesNumber,
                publisher,
                level,
                expectedOpenAccess,
                expectedPeerReviewed,
                expectedIsbnList,
                null,
                null,
                SAMPLE_LINKED_CONTEXT
        );
        String actualJson = objectMapper.writeValueAsString(book);
        assertEquals(expectedJson, actualJson);
    }

    @DisplayName("Book complains if ISBNs are invalid")
    @ParameterizedTest
    @CsvSource({
            "Series title,123,Full publisher details,LEVEL_2,true,true,\"obviousNonsense|9788131700075\"",
            "Series title,123,Full publisher details,LEVEL_2,true,true,\"9780201309515|obviousNonsense\"",
            "Series title,123,Full publisher details,LEVEL_2,true,true,\"9780201309515|9788131700075|obviousNonsense\""
    })
    void bookThrowsInvalidIsbnExceptionWhenIsbnIsInvalid(String seriesTitle,
                                                         String seriesNumber,
                                                         String publisher,
                                                         String level,
                                                         String openAccess,
                                                         String peerReviewed,
                                                         String isbnList) {

        ArrayList<String> invalidIsbnList = new ArrayList<>(convertIsbnStringToList(isbnList));

        Executable executable = () -> new Book.Builder()
                .withSeriesTitle(seriesTitle)
                .withSeriesNumber(seriesNumber)
                .withPublisher(publisher)
                .withLevel(Level.valueOf(level))
                .withOpenAccess(Boolean.getBoolean(openAccess))
                .withPeerReviewed(Boolean.getBoolean(peerReviewed))
                .withIsbnList(invalidIsbnList)
                .build();

        Exception exception = assertThrows(InvalidIsbnException.class, executable);
        String expectedMessage = String.format(InvalidIsbnException.ERROR_TEMPLATE, "obviousNonsense");
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("Book: Null ISBNs are handled gracefully")
    @Test
    void bookReturnsEmptyListWhenIsbnsAreNull() throws InvalidIsbnException {
        Book book = new Book.Builder()
                .withSeriesTitle(null)
                .withSeriesNumber(null)
                .withLevel(Level.LEVEL_0)
                .withPeerReviewed(false)
                .withOpenAccess(false)
                .withPublisher(null)
                .withIsbnList(null)
                .build();

        List<String> resultIsbnList = book.getIsbnList();
        assertThat(resultIsbnList, is(not(nullValue())));
        assertThat(resultIsbnList, is(empty()));
    }

    @DisplayName("Book: Empty ISBNs are handled gracefully")
    @Test
    void bookReturnsEmptyListWhenIsbnListIsEmpty() throws InvalidIsbnException {
        Book book = new Book.Builder()
                .withSeriesTitle(null)
                .withSeriesNumber(null)
                .withLevel(Level.LEVEL_0)
                .withPeerReviewed(false)
                .withOpenAccess(false)
                .withPublisher(null)
                .withIsbnList(Collections.emptyList())
                .build();

        List<String> resultIsbnList = book.getIsbnList();
        assertThat(resultIsbnList, is(not(nullValue())));
        assertThat(resultIsbnList, is(empty()));
    }

    @DisplayName("Book: serializes and deserializes with linkedContext")
    @Test
    void bookIsSerializedAndDeserializedWithLinkedContext() throws InvalidIsbnException, JsonProcessingException {
        Book actualBook = new Book.Builder()
                .withLinkedContext(SAMPLE_LINKED_CONTEXT)
                .build();

        String expectedJson = generatePublicationJson(
                BOOK,
                null,
                null,
                null,
                null,
                false,
                false,
                null,
                null,
                null,
                SAMPLE_LINKED_CONTEXT
        );


        String actualJson = objectMapper.writeValueAsString(actualBook);
        assertEquals(expectedJson, actualJson);
        Book deserializedBook = objectMapper.readValue(actualJson, Book.class);
        assertEquals(actualBook, deserializedBook);
        assertEquals(SAMPLE_LINKED_CONTEXT, deserializedBook.getLinkedContext().toString());
    }
}

package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;
import no.unit.nva.model.ModelTest;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidSeriesException;
import nva.commons.core.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValues;
import static no.unit.nva.model.util.PublicationGenerator.convertIsbnStringToList;
import static no.unit.nva.utils.RandomPublicationContexts.randomBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookTest extends ModelTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String BOOK = "Book";
    public static final String SAMPLE_OF_SYMBOLS = "*/.-+^!#&%(){}[]";

    @Test
    public void bookHasNonEmptySeriesUriWhenBookIsPartOfSeries() throws InvalidIsbnException {
        Book book = randomBook();
        assertThat(book.getSeriesUri(), is(not(nullValue())));
        assertThat(book, doesNotHaveEmptyValues());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "https://api.dev.nva.aws.unit.no/publication-channels/journal/449575/2020",
        "https://api.test.nva.aws.unit.no/publication-channels/journal/449575/2020",
        "https://api.nva.aws.unit.no/publication-channels/journal/449575/2020",
    })
    public void bookAcceptsValidSeriesUri(String validSeriesUri) {
        Book book =
            assertDoesNotThrow((() -> randomBook().copy().withSeriesUri(URI.create(validSeriesUri)).build()));
        assertThat(book.getSeriesUri().toString(), is(equalTo(validSeriesUri)));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "https://api.dev.nva.unit.no/publication-channels/journal/449575/2020",
        "http://www.example.com/",
        "uri:not:http:uri"
    })
    public void bookThrowsExceptionWhenSeriesUriIsInvalid(String invalidSeriesUri) {
        Executable action = () -> randomBook().copy().withSeriesUri(URI.create(invalidSeriesUri)).build();
        InvalidSeriesException exception = assertThrows(InvalidSeriesException.class, action);
        assertThat(exception.getMessage(), containsString(invalidSeriesUri));
    }

    @Test
    public void copyReturnsBookEqualToOriginalButDifferentObject() throws InvalidIsbnException {
        Book original = randomBook();
        assertThat(original, doesNotHaveEmptyValues());
        Book copy = original.copy().build();
        assertThat(copy, is(equalTo(original)));
    }

    @DisplayName("Book can deserialize a book")
    @ParameterizedTest
    @CsvSource({
        "A series title,123,Full publisher details,\"9780201309515|9788131700075\"",
        ",, Full publisher details,\"9780201309515|9788131700075\"",
        "A series title,,Full publisher details,\"9780201309515|9788131700075\"",
        "Fulong of Oolong,12,T Publishing,\"9780201309515|9788131700075\""
    })
    void objectMapperReturnsBookWhenInputIsValidJson(String seriesTitle,
                                                     String seriesNumber,
                                                     String publisher,
                                                     String isbnList) throws JsonProcessingException {
        List<String> expectedIsbn = convertIsbnStringToList(isbnList);
        String json = generatePublicationJson(
            BOOK,
            seriesTitle,
            seriesNumber,
            publisher,
            expectedIsbn,
            null,
            null,
            null
        );
        Book book = objectMapper.readValue(json, Book.class);
        assertEquals(seriesTitle, book.getSeriesTitle());
        assertEquals(seriesNumber, book.getSeriesNumber());
        assertEquals(expectedIsbn, book.getIsbnList());
    }

    @DisplayName("Book serializes expected json")
    @ParameterizedTest
    @CsvSource({
        "A series title,123,Full publisher details,\"9780201309515|9788131700075\"",
        ",, Full publisher details,\"9780201309515|9788131700075\"",
        "A series title,,Full publisher details,\"9780201309515|9788131700075\"",
        "Fulong of Oolong,12,T Publishing,\"9780201309515|9788131700075\"",
        "A Marxist analysis of marking systems,6903,ACO,"
    })
    void objectMapperProducesProperlyFormattedJsonWhenInputIsBook(String seriesTitle,
                                                                  String seriesNumber,
                                                                  String publisher,
                                                                  String isbnList) throws JsonProcessingException,
                                                                                          InvalidIsbnException {
        List<String> expectedIsbnList = convertIsbnStringToList(isbnList);
        Book book = new Book.Builder()
            .withSeriesTitle(seriesTitle)
            .withSeriesNumber(seriesNumber)
            .withPublisher(publisher)
            .withIsbnList(expectedIsbnList)
            .build();
        String expectedJson = generatePublicationJson(
            BOOK,
            seriesTitle,
            seriesNumber,
            publisher,
            expectedIsbnList,
            null,
            null,
            null
        );
        String actualJson = objectMapper.writeValueAsString(book);
        assertEquals(expectedJson, actualJson);
    }

    @DisplayName("Book complains if ISBNs are invalid")
    @ParameterizedTest
    @CsvSource({
        "\"obviousNonsense|9788131700075\"",
        "\"9780201309515|obviousNonsense\"",
        "\"9780201309515|9788131700075|obviousNonsense\""
    })
    void bookThrowsInvalidIsbnExceptionWhenIsbnIsInvalid(String isbnList) {

        ArrayList<String> invalidIsbnList = new ArrayList<>(convertIsbnStringToList(isbnList));

        Executable executable = () -> randomBook().copy().withIsbnList(invalidIsbnList).build();

        Exception exception = assertThrows(InvalidIsbnException.class, executable);
        String expectedMessage = String.format(InvalidIsbnException.ERROR_TEMPLATE, "obviousNonsense");
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("Book: Null ISBNs are handled gracefully")
    @Test
    void bookReturnsEmptyListWhenIsbnsAreNull() throws InvalidIsbnException {
        Book book = randomBook().copy().withIsbnList(null).build();

        List<String> resultIsbnList = book.getIsbnList();
        assertThat(resultIsbnList, is(not(nullValue())));
        assertThat(resultIsbnList, is(empty()));
    }

    @DisplayName("Book: Empty ISBNs are handled gracefully")
    @Test
    void bookReturnsEmptyListWhenIsbnListIsEmpty() throws InvalidIsbnException {
        Book book = randomBook().copy().withIsbnList(Collections.emptyList()).build();
        List<String> resultIsbnList = book.getIsbnList();
        assertThat(resultIsbnList, is(not(nullValue())));
        assertThat(resultIsbnList, is(empty()));
    }

    @DisplayName("No-digits are removed from isbn upon creation of a Book with deserialization.")
    @ParameterizedTest
    @CsvSource({
            "9788131700075",
            "9780201309515"
    })
    void setIsbnListRemovesNonedigitsFromIsbnWhenCreatingABookWithDeserialization(String isbn) throws InvalidIsbnException, IOException {
        Book actuallBook = randomBook();
        actuallBook.setIsbnList(convertIsbnStringToList(isbn));
        String actuallBookString = objectMapper.writeValueAsString(actuallBook);
        String wrongIsbn = objectMapper.writeValueAsString(actuallBook.getIsbnList()
                .stream()
                .map(isbnValue -> addNonedigitsToString(isbn))
                .collect(Collectors.toList()));
        JsonNode wrongIsbnJsonNode = JsonUtils.objectMapperNoEmpty.readTree(wrongIsbn);
        ObjectNode bookObjectNode = (ObjectNode) JsonUtils.objectMapperNoEmpty.readTree(actuallBookString);
        bookObjectNode.set("isbnList", wrongIsbnJsonNode);
        String tempString = objectMapper.writeValueAsString(bookObjectNode);
        Book deserializedBook = objectMapper.readValue(tempString, Book.class);
        assertThat(deserializedBook.getIsbnList(), is(equalTo(convertIsbnStringToList(isbn))));
    }

    @DisplayName("No-digits are removed from isbn upon creation of a Book using the builder")
    @ParameterizedTest
    @CsvSource({
            "9788131700075",
            "9780201309515"
    })
    void setIsbnListRemovesNonedigitsFromIsbnWhenCreatingABookUsingTheBuilder(String isbn) throws InvalidIsbnException {
        Book actuallBook = new Book.Builder()
                .withIsbnList(convertIsbnStringToList(addNonedigitsToString(isbn)))
                .build();
        assertThat(actuallBook.getIsbnList(), is(equalTo(convertIsbnStringToList(isbn))));
    }

    private String addNonedigitsToString(String isbn) {
        return SAMPLE_OF_SYMBOLS + isbn;
    }
}

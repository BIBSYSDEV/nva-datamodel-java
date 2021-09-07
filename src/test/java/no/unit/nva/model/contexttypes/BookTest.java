package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import no.unit.nva.model.ModelTest;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidSeriesException;
import no.unit.nva.model.exceptions.InvalidUnconfirmedSeriesException;
import nva.commons.core.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValues;
import static no.unit.nva.model.util.PublicationGenerator.convertIsbnStringToList;
import static no.unit.nva.utils.RandomPublicationContexts.randomBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest extends ModelTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String BOOK = "Book";
    public static final String SOME_SERIES_TITLE = "Some series title";
    public static final String SOME_OTHER_SERIES_TITLE = "Unmatched series title";

    @Test
    public void bookHasNonEmptySeriesUriWhenBookIsPartOfSeries() throws InvalidIsbnException {
        Book book = randomBook();
        assertThat(book.getSeries(), is(not(nullValue())));
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
            assertDoesNotThrow((() -> randomBook().copy().withSeries(new Series(URI.create(validSeriesUri))).build()));
        assertThat(((Series) book.getSeries()).getId().toString(), is(equalTo(validSeriesUri)));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "https://api.dev.nva.unit.no/publication-channels/journal/449575/2020",
        "http://www.example.com/",
        "uri:not:http:uri"
    })
    public void bookThrowsExceptionWhenSeriesUriIsInvalid(String invalidSeriesUri) {
        Executable action = () -> randomBook().copy().withSeries(new Series(URI.create(invalidSeriesUri))).build();
        InvalidSeriesException exception = assertThrows(InvalidSeriesException.class, action);
        assertThat(exception.getMessage(), containsString(invalidSeriesUri));
    }

    @Test
    public void copyReturnsBookEqualToOriginalButDifferentObject() throws InvalidIsbnException {
        Book original = randomBook();
        assertThat(original, doesNotHaveEmptyValues());
        Book copy = original.copy().build();
        assertThat(original, is(not(sameInstance(copy))));
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
        assertEquals(seriesTitle, ((UnconfirmedSeries) book.getSeries()).getTitle());
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
        Book book = new Book.BookBuilder()
            .withSeries(new UnconfirmedSeries(seriesTitle))
            .withSeriesNumber(seriesNumber)
            .withPublisher(new UnconfirmedPublisher(publisher))
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
        Book book = randomBook().copy().withIsbnList(emptyList()).build();
        List<String> resultIsbnList = book.getIsbnList();
        assertThat(resultIsbnList, is(not(nullValue())));
        assertThat(resultIsbnList, is(empty()));
    }

    @DisplayName("No-digits are removed from isbn upon creation of a Book with deserialization.")
    @ParameterizedTest
    @CsvSource({
        "9788131700075, ?9;7:8-8.1+3-1!7#0¤0%0&7/5*",
        "9780201309515, 9^7'8¨0`2\\0(){}[]1=3  0_9~5´1±5"
    })
    void setIsbnListRemovesNonedigitsFromIsbnWhenCreatingABookWithDeserialization(String expectedIsbn, String inputIsbn)
            throws InvalidIsbnException, IOException {
        Book actuallBook = randomBook();
        String actuallBookString = objectMapper.writeValueAsString(actuallBook);
        String wrongIsbn = objectMapper.writeValueAsString(convertIsbnStringToList(inputIsbn));
        JsonNode wrongIsbnJsonNode = JsonUtils.objectMapperNoEmpty.readTree(wrongIsbn);
        ObjectNode bookObjectNode = (ObjectNode) JsonUtils.objectMapperNoEmpty.readTree(actuallBookString);
        bookObjectNode.set("isbnList", wrongIsbnJsonNode);
        String tempString = objectMapper.writeValueAsString(bookObjectNode);
        Book deserializedBook = objectMapper.readValue(tempString, Book.class);
        assertThat(deserializedBook.getIsbnList(), is(equalTo(convertIsbnStringToList(expectedIsbn))));
    }

    @DisplayName("No-digits are removed from isbn upon creation of a Book using the builder")
    @ParameterizedTest
    @CsvSource({
        "9788131700075, ?9;7:8-8.1+3-1!7#0¤0%0&7/5*",
        "9780201309515, 9^7'8¨0`2\\0(){}[]1=3  0_9~5´1±5"
    })
    void setIsbnListRemovesNonedigitsFromIsbnWhenCreatingABookUsingTheBuilder(String expectedIsbn, String inputIsbn)
            throws InvalidIsbnException {
        Book actuallBook = new Book.BookBuilder()
                .withIsbnList(convertIsbnStringToList(inputIsbn))
                .build();
        assertThat(actuallBook.getIsbnList(), is(equalTo(convertIsbnStringToList(expectedIsbn))));
    }

    @Test
    void bookDoesNotThrowExceptionWhenInputUnconfirmedSeriesStringsMatch() {
        assertDoesNotThrow(() -> new Book(
                new UnconfirmedSeries(SOME_SERIES_TITLE),
                SOME_SERIES_TITLE,
                "1",
                new UnconfirmedPublisher("Publisher"),
                emptyList())
        );
    }

    @Test
    void bookThrowsExceptionWhenInputUnconfirmedSeriesStringsAreUnmatched() {
        Executable executable = () -> new Book(
                new UnconfirmedSeries(SOME_SERIES_TITLE),
                SOME_OTHER_SERIES_TITLE,
                "1",
                new UnconfirmedPublisher("Publisher"),
                emptyList());
        Exception exception = assertThrows(InvalidUnconfirmedSeriesException.class, executable);
        assertThat(exception.getMessage(), equalTo(InvalidUnconfirmedSeriesException.ERROR_MESSAGE));
    }

    @Test
    void bookIgnoresSeriesTitleStringWhenInputIsSeriesTitleAndConfirmedSeries() throws InvalidIsbnException,
            InvalidUnconfirmedSeriesException {
        var seriesUri = URI.create("https://nva.aws.unit.no/publication-channels/series/123123");
        var book = new Book(
                new Series(seriesUri),
                SOME_SERIES_TITLE,
                "1",
                new UnconfirmedPublisher("Publisher"),
                emptyList());
        assertTrue(book.getSeries().isConfirmed());
        assertThat(((Series) book.getSeries()).getId(), equalTo(seriesUri));
    }
}

package no.unit.nva.model.contexttypes;

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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import no.unit.nva.model.Level;
import no.unit.nva.model.ModelTest;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidSeriesException;
import nva.commons.core.JsonUtils;
import org.apache.commons.validator.routines.ISBNValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BookTest extends ModelTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String BOOK = "Book";
    public static final URI SAMPLE_LINKED_CONTEXT = URI.create("http://example.com/context");
    public static final ISBNValidator ISBN_VALIDATOR = ISBNValidator.getInstance();

    @Test
    public void bookHasNonEmptySeriesUriWhenBookIsPartOfSeries() throws MalformedURLException, InvalidIsbnException {
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
        Executable action = ()-> randomBook().copy().withSeriesUri(URI.create(invalidSeriesUri)).build();
        InvalidSeriesException exception= assertThrows(InvalidSeriesException.class,action);
        assertThat(exception.getMessage(), containsString(invalidSeriesUri));
    }

    @Test
    public void copyReturnsBookEqualToOriginalButDifferentObject() throws MalformedURLException, InvalidIsbnException {
        Book original = randomBook();
        assertThat(original, doesNotHaveEmptyValues());
        Book copy = original.copy().build();
        assertThat(copy, is(equalTo(original)));
    }

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
    void bookReturnsEmptyListWhenIsbnsAreNull() throws InvalidIsbnException, MalformedURLException {
        Book book = randomBook().copy().withIsbnList(null).build();

        List<String> resultIsbnList = book.getIsbnList();
        assertThat(resultIsbnList, is(not(nullValue())));
        assertThat(resultIsbnList, is(empty()));
    }

    @DisplayName("Book: Empty ISBNs are handled gracefully")
    @Test
    void bookReturnsEmptyListWhenIsbnListIsEmpty() throws InvalidIsbnException, MalformedURLException {
        Book book = randomBook().copy().withIsbnList(Collections.emptyList()).build();
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
        assertEquals(SAMPLE_LINKED_CONTEXT, deserializedBook.getLinkedContext());
    }
}

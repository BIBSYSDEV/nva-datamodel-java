package no.unit.nva.model.instancetypes.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import no.unit.nva.model.instancetypes.InstanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValues;
import static no.unit.nva.model.instancetypes.book.BookMonographContentType.ENCYCLOPEDIA;
import static no.unit.nva.model.instancetypes.book.BookMonographContentType.lookup;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookMonographTest extends InstanceTest {

    public static final String BOOK_MONOGRAPH = "BookMonograph";
    public static final boolean NOT_ORIGINAL_RESEARCH = false;
    public static final boolean IS_ORIGINAL_RESEARCH = true;

    @DisplayName("BookMonograph exists")
    @Test
    void bookMonographExists() {
        new BookMonograph();
    }

    @DisplayName("BookMonograph: ObjectMapper correctly deserializes object")
    @ParameterizedTest(name = "BookMonograph deserializes begin {0}, end {1}, pages {2}, illustrated {3}, "
            + "peerReviewed {4}, textbook {5}")
    @CsvSource({
            "i,xxviii,398,true,true,true",
            ",,231,false,true,true",
            ",,123,true,false,true",
            ",,123,true,false,false"
    })
    void objectMapperReturnsBookMonographWhenInputIsValid(String begin,
                                                          String end,
                                                          String pages,
                                                          boolean illustrated,
                                                          boolean peerReviewed,
                                                          boolean textbookContent) throws JsonProcessingException {
        BookMonograph expected = generateBookMonograph(
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                textbookContent
        );

        String json = generateMonographJsonString(
                BOOK_MONOGRAPH,
                begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                textbookContent);
        BookMonograph actual = objectMapper.readValue(json, BookMonograph.class);
        assertThat(expected, samePropertyValuesAs(actual));
    }

    @DisplayName("BookMonograph: ObjectMapper serializes valid input correctly")
    @ParameterizedTest(name = "BookMonograph serializes begin {0}, end {1}, pages {2}, illustrated {3}, "
            + "peerReviewed {4}, textbook {5}")
    @CsvSource({
            "i,xxviii,398,true,true,true",
            ",,231,false,true,true",
            ",,123,true,false,true",
            ",,123,true,false,false"
    })
    void objectMapperSerializesAndDeserializesJsonWhenInputIsValid(String begin,
                                                         String end,
                                                         String pages,
                                                         boolean illustrated,
                                                         boolean peerReviewed,
                                                         boolean textbookContent) throws JsonProcessingException {

        BookMonograph bookMonograph = generateBookMonograph(begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                textbookContent
        );
        JsonNode json = jsonStringToJsonNode(objectMapper.writeValueAsString(bookMonograph));
        JsonNode expected = generateMonographJson(BOOK_MONOGRAPH,
                begin, end, pages, illustrated, peerReviewed, textbookContent, null, NOT_ORIGINAL_RESEARCH);
        assertEquals(expected, json);
    }


    @DisplayName("Test BookMonograph with contentType can be serialized/deserialized ")
    @ParameterizedTest(name = "Test BookMonograph with Content type {0} can be (de-)serialized")
    @ValueSource(strings = {
            "Academic Monograph",
            "Non-fiction Monograph",
            "Popular Science Monograph",
            "Textbook",
            "Encyclopedia"
    })
    void publicationReturnsJsonWhenInputIsValid(String contentTypeString) throws IOException {

        final String begin = "i";
        final String end = "xxviii";
        final String pages = "398";
        final boolean illustrated = true;
        final boolean peerReviewed = true;
        final boolean textbookContent = true;

        BookMonographContentType contentType = lookup(contentTypeString);

        BookMonograph expectedBookMonograph = generateBookMonographWithContentType(begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                textbookContent,
                contentType);

        JsonNode actualBookMonographJson = jsonStringToJsonNode(objectMapper.writeValueAsString(expectedBookMonograph));
        JsonNode expectedBookMonographJson = generateMonographJson(BOOK_MONOGRAPH,
                begin, end, pages, illustrated, peerReviewed, textbookContent, contentType, NOT_ORIGINAL_RESEARCH);

        BookMonograph actualBookMonograph =
                objectMapper.readValue(objectMapper.writeValueAsString(expectedBookMonograph), BookMonograph.class);

        assertEquals(expectedBookMonographJson, actualBookMonographJson);
        assertThat(expectedBookMonograph, is(equalTo(actualBookMonograph)));

    }

    @DisplayName("Test OriginalResearch for BookMonograph contentTypes")
    @ParameterizedTest(name = "Test BookMonograph with Content type {0} can be annotated as OriginalResearch ")
    @CsvSource({
            "Academic Monograph,true",
            "Non-fiction Monograph,false",
            "Popular Science Monograph,false",
            "Textbook,false",
            "Encyclopedia,false"
    })
    void originalResearchIsAssignedToBookMonograpWithContentTypeAcademicResearchOnly(String contentTypeString,
                                                                                     boolean expectedOriginalResearch) {

        final String begin = "i";
        final String end = "xxviii";
        final String pages = "398";
        final boolean illustrated = true;
        final boolean peerReviewed = true;
        final boolean textbookContent = true;

        BookMonographContentType contentType = lookup(contentTypeString);

        BookMonograph bookMonograph = generateBookMonographWithContentType(begin,
                end,
                pages,
                illustrated,
                peerReviewed,
                textbookContent,
                contentType);
        bookMonograph.setOriginalResearch(IS_ORIGINAL_RESEARCH);
        assertEquals(bookMonograph.isOriginalResearch(), expectedOriginalResearch);
    }

    @Test
    void bookMonographBuilderCreatesBookMonographWithoutEmptyValues() {
        BookMonograph expectedBookMonograph
                = generateBookMonographWithContentType("i", "xxviii", "398", true, true, true, ENCYCLOPEDIA);
        assertThat(expectedBookMonograph, doesNotHaveEmptyValues());
    }

    @Test
    void bookMonographSerializationContainsBookMonographContentType() throws JsonProcessingException {

        final BookMonographContentType bookMonographContentType = ENCYCLOPEDIA;
        BookMonograph expectedBookMonograph = generateBookMonographWithContentType("i",
                "xxviii",
                "398",
                true,
                true,
                true,
                bookMonographContentType);
        assertThat(expectedBookMonograph, doesNotHaveEmptyValues());

        String json = objectMapper.writeValueAsString(expectedBookMonograph);
        String expectedContentPhrase = "contentType\" : \"" + bookMonographContentType.getValue() + "\"";
        assertTrue(json.contains(expectedContentPhrase));
    }


    private BookMonograph generateBookMonograph(String introductionBegin,
                                                String introductionEnd,
                                                String pages,
                                                boolean illustrated,
                                                boolean peerReviewed,
                                                boolean textbookContent) {

        return new BookMonograph.Builder()
                .withPages(generateMonographPages(introductionBegin, introductionEnd, pages, illustrated))
                .withPeerReviewed(peerReviewed)
                .withTextbookContent(textbookContent)
                .build();
    }

    private BookMonograph generateBookMonographWithContentType(String introductionBegin,
                                                               String introductionEnd,
                                                               String pages,
                                                               boolean illustrated,
                                                               boolean peerReviewed,
                                                               boolean textbookContent,
                                                               BookMonographContentType contentType) {

        return new BookMonograph.Builder()
                .withPages(generateMonographPages(introductionBegin, introductionEnd, pages, illustrated))
                .withPeerReviewed(peerReviewed)
                .withTextbookContent(textbookContent)
                .withContentType(contentType)
                .build();
    }


}

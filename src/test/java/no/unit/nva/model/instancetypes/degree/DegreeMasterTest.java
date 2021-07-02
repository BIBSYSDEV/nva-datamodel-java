package no.unit.nva.model.instancetypes.degree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import no.unit.nva.model.instancetypes.InstanceTest;
import no.unit.nva.model.instancetypes.book.BookMonographContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DegreeMasterTest extends InstanceTest {
    private static final String DEGREE_MASTER = "DegreeMaster";
    public static final boolean NOT_ORIGINAL_RESEARCH = false;
    public static final BookMonographContentType EMPTY_CONTENT_TYPE = null;
    public static final boolean NOT_TEXTBOOK_CONTENT = false;
    public static final boolean NOT_PEER_REVIEWED = false;

    @DisplayName("DegreeMaster exists")
    @Test
    void degreeMasterExists() {
        new DegreeMaster();
    }

    @DisplayName("DegreeMaster: ObjectMapper correctly deserializes object")
    @ParameterizedTest
    @CsvSource({
            "i,xxviii,398,true",
            ",,231,false",
            ",,123,true"
    })
    void objectMapperReturnsDegreeMasterWhenInputIsValid(String begin,
                                                         String end,
                                                         String pages,
                                                         boolean illustrated) throws JsonProcessingException {
        String json = generateMonographJsonString(
                DEGREE_MASTER,
                begin,
                end,
                pages,
                illustrated,
                false);
        DegreeMaster actual = objectMapper.readValue(json, DegreeMaster.class);
        DegreeMaster expected = generateDegreeMaster(
                begin,
                end,
                pages,
                illustrated
        );
        assertEquals(expected, actual);
    }

    @DisplayName("DegreeMaster: ObjectMapper serializes valid input correctly")
    @ParameterizedTest
    @CsvSource({
            "i,xxviii,398,true",
            ",,231,false",
            ",,123,true"
    })
    void objectMapperReturnsExpectedJsonWhenInputIsValid(String begin,
                                                         String end,
                                                         String pages,
                                                         boolean illustrated) throws JsonProcessingException {
        DegreeMaster degreeMaster = generateDegreeMaster(
                begin,
                end,
                pages,
                illustrated);
        String json = objectMapper.writeValueAsString(degreeMaster);
        JsonNode expected = generateMonographJson(
                DEGREE_MASTER,
                begin,
                end,
                pages,
                illustrated,
                NOT_PEER_REVIEWED,
                NOT_TEXTBOOK_CONTENT,
                EMPTY_CONTENT_TYPE,
                NOT_ORIGINAL_RESEARCH);
        JsonNode actual = jsonStringToJsonNode(json);
        assertEquals(expected, actual);
    }

    private DegreeMaster generateDegreeMaster(String introductionBegin,
                                              String introductionEnd,
                                              String pages,
                                              boolean illustrated) {

        return new DegreeMaster.Builder()
                .withPages(generateMonographPages(introductionBegin, introductionEnd, pages, illustrated))
                .build();
    }
}

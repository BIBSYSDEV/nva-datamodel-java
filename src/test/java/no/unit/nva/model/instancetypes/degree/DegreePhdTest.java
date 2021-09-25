package no.unit.nva.model.instancetypes.degree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import no.unit.nva.JsonHandlingTest;
import no.unit.nva.model.instancetypes.InstanceTest;
import no.unit.nva.model.instancetypes.book.BookMonographContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DegreePhdTest extends InstanceTest implements JsonHandlingTest {
    private static final String DEGREE_PHD = "DegreePhd";
    public static final boolean NOT_PEER_REVIEWED = false;
    public static final BookMonographContentType EMPTY_CONTENT_TYPE = null;
    public static final boolean NOT_ORIGINAL_RESEARCH = false;

    @DisplayName("DegreePhd exists")
    @Test
    void degreePhdExists() {
        new DegreePhd();
    }

    @DisplayName("DegreePhd: ObjectMapper correctly deserializes object")
    @ParameterizedTest
    @CsvSource({
            "i,xxviii,398,true",
            ",,231,false",
            ",,123,true"
    })
    void objectMapperReturnsDegreePhdWhenInputIsValid(String begin,
                                                      String end,
                                                      String pages,
                                                      boolean illustrated) throws JsonProcessingException {

        String json = generateMonographJsonString(
                DEGREE_PHD,
                begin,
                end,
                pages,
                illustrated,
                EMPTY_CONTENT_TYPE);
        DegreePhd expected = generateDegreePhd(
                begin,
                end,
                pages,
                illustrated
        );
        DegreePhd actual = objectMapper.readValue(json, DegreePhd.class);
        assertEquals(expected, actual);
    }

    @DisplayName("DegreePhd: ObjectMapper serializes valid input correctly")
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
        DegreePhd degreePhd = generateDegreePhd(
                begin,
                end,
                pages,
                illustrated
        );
        JsonNode json = jsonStringToJsonNode(objectMapper.writeValueAsString(degreePhd));
        JsonNode expected = generateMonographJson(
                DEGREE_PHD,
                begin,
                end,
                pages,
                illustrated,
                NOT_PEER_REVIEWED,
                EMPTY_CONTENT_TYPE,
                NOT_ORIGINAL_RESEARCH);
        assertEquals(expected, json);
    }

    private DegreePhd generateDegreePhd(String introductionBegin,
                                        String introductionEnd,
                                        String pages,
                                        boolean illustrated) {

        return new DegreePhd.Builder()
                .withPages(generateMonographPages(introductionBegin, introductionEnd, pages, illustrated))
                .build();
    }
}

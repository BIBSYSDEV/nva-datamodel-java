package no.unit.nva.model.contexttypes;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.Level;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static no.unit.nva.model.util.PublicationGenerator.convertIsbnStringToList;
import static no.unit.nva.model.util.PublicationGenerator.generatePublicationJson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DegreeTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String DEGREE = "Degree";

    @DisplayName("Degree can deserialize a degree")
    @ParameterizedTest
    @CsvSource({
            "A series title,123,Full publisher details,LEVEL_2,true,true,\"9780201309515|9788131700075\"",
            ",, Full publisher details, LEVEL_2, true, true, \"9780201309515|9788131700075\"",
            "A series title,,Full publisher details,LEVEL_2,true,true,\"9780201309515|9788131700075\"",
            "Fulong of Oolong,12,T Publishing,LEVEL_0,false,false,\"9780201309515|9788131700075\""
    })
    void objectMapperReturnsDegreeWhenInputIsValidJson(String seriesTitle,
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
                DEGREE,
                seriesTitle,
                seriesNumber,
                publisher,
                expectedLevel,
                expectedOpenAccess,
                expectedPeerReviewed,
                expectedIsbn,
                null,
                null
        );
        Degree degree = objectMapper.readValue(json, Degree.class);
        assertEquals(seriesTitle, degree.getSeriesTitle());
        assertEquals(seriesNumber, degree.getSeriesNumber());
        assertEquals(expectedLevel, degree.getLevel());
        assertEquals(expectedIsbn, degree.getIsbnList());
        assertEquals(expectedOpenAccess, degree.isOpenAccess());
        assertEquals(expectedPeerReviewed, degree.isPeerReviewed());
    }

    @DisplayName("Degree serializes expected json")
    @ParameterizedTest
    @CsvSource({
            "A series title,123,Full publisher details,LEVEL_2,true,true,\"9780201309515|9788131700075\"",
            ",, Full publisher details, LEVEL_2, true, true, \"9780201309515|9788131700075\"",
            "A series title,,Full publisher details,LEVEL_2,true,true,\"9780201309515|9788131700075\"",
            "Fulong of Oolong,12,T Publishing,LEVEL_0,false,false,\"9780201309515|9788131700075\"",
            "A Marxist analysis of marking systems,6903,ACO,LEVEL_1,true,false,"
    })
    void objectMapperProducesProperlyFormattedJsonWhenInputIsDegree(String seriesTitle,
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
        Degree degree = new Degree.Builder()
                .withSeriesTitle(seriesTitle)
                .withSeriesNumber(seriesNumber)
                .withPublisher(publisher)
                .withLevel(expectedLevel)
                .withOpenAccess(expectedOpenAccess)
                .withPeerReviewed(expectedPeerReviewed)
                .withIsbnList(expectedIsbnList)
                .build();
        String expectedJson = generatePublicationJson(
                DEGREE,
                seriesTitle,
                seriesNumber,
                publisher,
                expectedLevel,
                expectedOpenAccess,
                expectedPeerReviewed,
                expectedIsbnList,
                null,
                null
        );
        String actualJson = objectMapper.writeValueAsString(degree);
        assertEquals(expectedJson, actualJson);
    }

    @DisplayName("Degree complains if ISBNs are invalid")
    @ParameterizedTest
    @CsvSource({
            "Series title,123,Full publisher details,LEVEL_2,true,true,\"obviousNonsense|9788131700075\"",
            "Series title,123,Full publisher details,LEVEL_2,true,true,\"9780201309515|obviousNonsense\"",
            "Series title,123,Full publisher details,LEVEL_2,true,true,\"9780201309515|9788131700075|obviousNonsense\""
    })
    void degreeThrowsInvalidIsbnExceptionWhenIsbnIsInvalid(String seriesTitle,
                                                         String seriesNumber,
                                                         String publisher,
                                                         String level,
                                                         String openAccess,
                                                         String peerReviewed,
                                                         String isbnList) {

        ArrayList<String> invalidIsbnList = new ArrayList<>(convertIsbnStringToList(isbnList));

        Executable executable = () -> new Degree.Builder()
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

    @DisplayName("Degree: Null ISBNs are handled gracefully")
    @Test
    void degreeReturnsEmptyListWhenIsbnsAreNull() throws InvalidIsbnException {
        Degree degree = new Degree.Builder()
                .withSeriesTitle(null)
                .withSeriesNumber(null)
                .withLevel(Level.LEVEL_0)
                .withPeerReviewed(false)
                .withOpenAccess(false)
                .withPublisher(null)
                .withIsbnList(null)
                .build();

        List<String> resultIsbnList = degree.getIsbnList();
        assertThat(resultIsbnList, is(not(nullValue())));
        assertThat(resultIsbnList, is(empty()));
    }

    @DisplayName("Degree: Empty ISBNs are handled gracefully")
    @Test
    void degreeReturnsEmptyListWhenIsbnListIsEmpty() throws InvalidIsbnException {
        Degree degree = new Degree.Builder()
                .withSeriesTitle(null)
                .withSeriesNumber(null)
                .withLevel(Level.LEVEL_0)
                .withPeerReviewed(false)
                .withOpenAccess(false)
                .withPublisher(null)
                .withIsbnList(Collections.emptyList())
                .build();

        List<String> resultIsbnList = degree.getIsbnList();
        assertThat(resultIsbnList, is(not(nullValue())));
        assertThat(resultIsbnList, is(empty()));
    }
}

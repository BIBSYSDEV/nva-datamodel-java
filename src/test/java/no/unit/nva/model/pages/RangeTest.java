package no.unit.nva.model.pages;

import no.unit.nva.model.exceptions.InvalidPageRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static java.util.Objects.nonNull;
import static no.unit.nva.model.pages.Range.EMPTY_STRING_PLACEHOLDER;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RangeTest {

    @DisplayName("Range accepts strings as definitions of extent")
    @ParameterizedTest
    @CsvSource({
            "begin,end", "1,7", "a,b", "A,Z", "i,ix"
    })
    void rangeReturnsRangeObjectWhenInputIsValid(String begin, String end) throws InvalidPageRangeException {
        Range actual = new Range(begin, end);
        Range expected = new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();
        assertEquals(expected, actual);
    }

    @DisplayName("Range allows two null arguments")
    @Test
    void rangeDoesNotThrowInvalidPageRangeExceptionWhenBothArgumentsAreNull() {
        assertDoesNotThrow(() -> new Range(null, null));
    }

    @DisplayName("Range throws InvalidPageRangeException if begin value is empty or just whitespace")
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"", " ", "   ", "\t", "\n", "\r\n"})
    void rangeThrowsInvalidPageRangeExceptionWhenBeginIsEmptyString(String begin) {
        String end = "ix";
        Executable executable = () -> new Range(begin, end);
        InvalidPageRangeException exception = assertThrows(InvalidPageRangeException.class, executable);
        String expectedMessage = String.format(InvalidPageRangeException.ERROR_TEMPLATE, EMPTY_STRING_PLACEHOLDER, end);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @DisplayName("Range throws InvalidPageRangeException if end value is empty or just whitespace")
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"", " ", "   ", "\t", "\n", "\r\n"})
    void rangeThrowsInvalidPageRangeExceptionWhenEndtIsEmptyString(String end) {
        String begin = "i";
        Executable executable = () -> new Range(begin, end);
        InvalidPageRangeException exception = assertThrows(InvalidPageRangeException.class, executable);
        String expectedMessage = String.format(InvalidPageRangeException.ERROR_TEMPLATE,
                begin, EMPTY_STRING_PLACEHOLDER);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @DisplayName("Range throws InvalidPageRangeException when both begin and end values are empty")
    @Test
    void rangeThrowsInvalidPageRangeExceptionWhenBeginAndEndAreEmptyStrings() {
        String begin = "";
        String end = "";
        Executable executable = () -> new Range(begin, end);
        InvalidPageRangeException exception = assertThrows(InvalidPageRangeException.class, executable);
        String expectedMessage = String.format(InvalidPageRangeException.ERROR_TEMPLATE,
                EMPTY_STRING_PLACEHOLDER, EMPTY_STRING_PLACEHOLDER);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @DisplayName("Range throws InvalidPageRangeException if only one of the inputs is null")
    @ParameterizedTest
    @CsvSource(value = {
            "null,x",
            "i,null",
            "null,'\t'",
            "null,'\n'",
            "null,'\r\n'",
            "null,''",
            "null, ' '",
            "'\t',null",
            "'\n',null",
            "'\r\n',null",
            "'',null",
            "' ',null"
        }, nullValues = "null")
    void rangeThrowsInvalidPageRangeExceptionWhenOnlyOneValueIsNull(String begin, String end) {
        Executable executable = () -> new Range(begin, end);
        InvalidPageRangeException exception = assertThrows(InvalidPageRangeException.class, executable);
        String expectedMessage = String.format(InvalidPageRangeException.ERROR_TEMPLATE,
                getDisplayValue(begin), getDisplayValue(end));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @DisplayName("Range preserves medial whitespace, stripping leading and trailing whitespace")
    @ParameterizedTest
    @CsvSource({"First page,Tenth page", " Page one,  Page ten", "Page one ,Page ten ", "  Page one  ,  Page ten  "})
    void rangeReturnsBeginAndEndWithMediaWhitespaceWithoutLeadingAndTrailingWhitespace(String begin, String end)
            throws InvalidPageRangeException {
        Range range = new Range(begin, end);
        String expectedBegin = begin.strip();
        String expectedEnd = end.strip();
        String actualBegin = range.getBegin();
        String actualEnd = range.getEnd();
        assertEquals(expectedBegin, actualBegin);
        assertEquals(expectedEnd, actualEnd);
    }

    private String getDisplayValue(String input) {
        if (nonNull(input) && (input.isEmpty() || input.matches("^[\\s\\t\\n\\r]+$"))) {
            return EMPTY_STRING_PLACEHOLDER;
        }
        return input;
    }
}
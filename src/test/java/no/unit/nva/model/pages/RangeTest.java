package no.unit.nva.model.pages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class RangeTest {

    @DisplayName("Range accepts strings as definitions of extent")
    @ParameterizedTest
    @CsvSource({
            "begin,end", "1,7", "a,b", "A,Z", "i,ix"
    })
    void rangeReturnsRangeObjectWhenInputIsValid(String begin, String end) {
        Range actual = new Range(begin, end);
        Range expected = new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();
        assertEquals(expected, actual);
    }

    @DisplayName("Range creates a null range if input is effectively empty")
    @ParameterizedTest(name = "Range creates null page range from {0} and {1}")
    @CsvSource(value = {
            "null,'\t'",
            "null,'\n'",
            "null,'\r\n'",
            "null,''",
            "null, ' '",
            "'\t',null",
            "'\n',null",
            "'\r\n',null",
            "'',null",
            "' ',null",
            "null,null"
        }, nullValues = "null")
    void rangeReturnsNullPageRangeExceptionWhenOnlyOneValueIsNull(String begin, String end) {
        Range actual = new Range(begin, end);
        assertNull(actual.getBegin());
        assertNull(actual.getEnd());
    }

    @DisplayName("Range creates a single-page range (n-n) if only one of the inputs is empty")
    @ParameterizedTest(name = "Range creates single page range from {0} and {1}")
    @CsvSource(value = {"null,1", "1,null"}, nullValues = "null")
    void rangeCreatesSinglePageRangeWhenOneInputIsNull(String begin, String end) {
        Range actual = new Range(begin, end);
        assertNotNull(actual.getBegin());
        assertNotNull(actual.getEnd());
        assertEquals(actual.getBegin(), actual.getEnd());
    }

    @DisplayName("Range preserves medial whitespace, stripping leading and trailing whitespace")
    @ParameterizedTest
    @CsvSource({"First page,Tenth page", " Page one,  Page ten", "Page one ,Page ten ", "  Page one  ,  Page ten  "})
    void rangeReturnsBeginAndEndWithMediaWhitespaceWithoutLeadingAndTrailingWhitespace(String begin, String end) {
        Range range = new Range(begin, end);
        String expectedBegin = begin.strip();
        String expectedEnd = end.strip();
        String actualBegin = range.getBegin();
        String actualEnd = range.getEnd();
        assertEquals(expectedBegin, actualBegin);
        assertEquals(expectedEnd, actualEnd);
    }
}
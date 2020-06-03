package no.unit.nva.model.pages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import nva.commons.utils.JacocoGenerated;

import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Range implements Pages {
    public static final String EMPTY_STRING_PLACEHOLDER = "<empty string>";
    public static final String NON_SPACE_WHITESPACE = "[\\n\\r\\t]+";
    public static final String EMPTY_STRING = "";

    private final String begin;
    private final String end;

    /**
     * Default constructor for Range ensures that a range has a specified beginning and an end, and contains
     * no unnecessary whitespace, nor members that are only whitespace.
     *
     * @param begin The beginning of the range.
     * @param end   The end of the range
     * @throws InvalidPageRangeException thrown if begin is null and end is not, or vice versa,
     *                                   or if either or both values are empty.
     */
    @JsonCreator
    public Range(@JsonProperty("begin") String begin, @JsonProperty("end") String end) throws
            InvalidPageRangeException {
        String sanitizedBegin = sanitize(begin);
        String sanitizedEnd = sanitize(end);
        if (oneValueIsNull(sanitizedBegin, sanitizedEnd) || oneOrBothValuesAreEmpty(sanitizedBegin, sanitizedEnd)) {
            throw new InvalidPageRangeException(getErrorDisplayValue(sanitizedBegin),
                    getErrorDisplayValue(sanitizedEnd));
        }
        this.begin = sanitizedBegin;
        this.end = sanitizedEnd;
    }

    private String getErrorDisplayValue(String input) {
        return nonNull(input) && input.isEmpty() ? EMPTY_STRING_PLACEHOLDER : input;
    }

    private boolean oneOrBothValuesAreEmpty(String begin, String end) {
        return nonNull(begin) && begin.isEmpty() || nonNull(end) && end.isEmpty();
    }

    private boolean oneValueIsNull(String begin, String end) {
        return isNull(begin) && nonNull(end) || nonNull(begin) && isNull(end);
    }

    private String sanitize(String input) {
        return nonNull(input) ? removeWhiteSpace(input) : null;
    }

    private String removeWhiteSpace(String input) {
        return input.replaceAll(NON_SPACE_WHITESPACE, EMPTY_STRING).trim();
    }

    private Range(Builder builder) throws InvalidPageRangeException {
        this(builder.begin, builder.end);
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Range)) {
            return false;
        }
        Range range = (Range) o;
        return Objects.equals(begin, range.begin)
                && Objects.equals(end, range.end);
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(begin, end);
    }

    public static final class Builder {
        private String begin;
        private String end;

        public Builder() {
        }

        public Builder withBegin(String begin) {
            this.begin = begin;
            return this;
        }

        public Builder withEnd(String end) {
            this.end = end;
            return this;
        }

        public Range build() throws InvalidPageRangeException {
            return new Range(this);
        }
    }
}

package no.unit.nva.model.instancetypes.chapter;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ChapterArticleContentType {
    ACADEMIC_CHAPTER("Academic Chapter"),
    NON_FICTION_CHAPTER("Non-fiction Chapter"),
    POPULAR_SCIENCE_CHAPTER("Popular Science Chapter"),
    TEXTBOOK_CHAPTER("Textbook Chapter"),
    ENCYCLOPEDIA_CHAPTER("Encyclopedia Chapter");

    public static final String ERROR_MESSAGE_TEMPLATE = "%s not a valid ChapterContentType, expected one of: %s";
    public static final String DELIMITER = ", ";

    private final String value;

    @JsonCreator
    ChapterArticleContentType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Lookup enum by value.
     *
     * @param value value
     * @return enum
     */
    public static ChapterArticleContentType lookup(String value) {
        return stream(values())
                .filter(nameType -> nameType.getValue().equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        format(ERROR_MESSAGE_TEMPLATE, value, stream(ChapterArticleContentType.values())
                                .map(ChapterArticleContentType::toString).collect(joining(DELIMITER)))));
    }
}

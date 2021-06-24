package no.unit.nva.model.instancetypes.journal;

import com.fasterxml.jackson.annotation.JsonValue;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

public enum JournalArticleContentType {
    RESEARCH_ARTICLE("Research article"),
    REVIEW_ARTICLE("Review article"),
    CASE_REPORT("Case report"),
    STUDY_PROTOCOL("Study protocol"),
    PROFESSIONAL_ARTICLE("Professional article"),
    POPULAR_SCIENCE_ARTICLE("Popular science article");

    public static final String ERROR_MESSAGE_TEMPLATE = "%s not a valid JournalArticleContentType, expected one of: %s";
    public static final String DELIMITER = ", ";

    private final String value;

    JournalArticleContentType(String value) {
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
    public static JournalArticleContentType lookup(String value) {
        return stream(values())
                .filter(nameType -> nameType.getValue().equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        format(ERROR_MESSAGE_TEMPLATE, value, stream(JournalArticleContentType.values())
                                .map(JournalArticleContentType::toString).collect(joining(DELIMITER)))));
    }


}

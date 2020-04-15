package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonValue;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

public enum PublicationType {

    JOURNAL_ARTICLE("JournalArticle");

    public static final String ERROR_MESSAGE_TEMPLATE = "%s not a valid PublicationType, expected one of: %s";
    public static final String DELIMITER = ", ";
    private String value;

    PublicationType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Lookup enum by value.
     *
     * @param value value
     * @return enum
     */
    public static PublicationType lookup(String value) {
        return stream(values())
                .filter(publicationType -> publicationType.getValue().equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        format(ERROR_MESSAGE_TEMPLATE, value, stream(PublicationType.values())
                                .map(PublicationType::toString).collect(joining(DELIMITER)))));
    }
}

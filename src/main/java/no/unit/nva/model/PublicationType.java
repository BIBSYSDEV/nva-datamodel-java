package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum PublicationType {

    JOURNAL_ARTICLE("JournalArticle");

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
     * @return  enum
     */
    public static PublicationType lookup(String value) {
        return Arrays
                .stream(values())
                .filter(publicationType -> publicationType.getValue().equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}

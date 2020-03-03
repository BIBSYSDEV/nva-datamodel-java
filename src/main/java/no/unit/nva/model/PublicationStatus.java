package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public enum PublicationStatus {

    NEW("New"),
    DRAFT("Draft"),
    REJECTED("Rejected"),
    PUBLISHED("Published");

    private String value;

    PublicationStatus(String value) {
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
    public static PublicationStatus lookup(String value) {
        return Arrays
                .stream(values())
                .filter(publicationStatus -> publicationStatus.getValue().equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("%s not a valid PublicationStatus, expected one of: %s",
                                value,
                                Arrays.stream(PublicationStatus.values())
                                        .map(PublicationStatus::toString)
                                        .collect(Collectors.joining(", ")))
                        )
                );
    }

}

package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum NameType {

    PERSONAL("Personal");

    private String value;

    NameType(String value) {
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
    public static NameType lookup(String value) {
        return Arrays
                .stream(values())
                .filter(nameType -> nameType.getValue().equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                                String.format("%s not a valid NameType, expected one of: %s",
                                        value,
                                        Arrays.stream(NameType.values())
                                                .map(NameType::toString)
                                                .collect(Collectors.joining(", ")))
                        )
                );
    }
}

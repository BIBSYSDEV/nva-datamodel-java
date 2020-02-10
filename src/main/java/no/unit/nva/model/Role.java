package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Role {
    CREATOR("Creator");

    private String value;

    Role(String value) {
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
     * @return  enum
     */
    public static Role lookup(String value) {
        return Arrays
                .stream(values())
                .filter(nameType -> nameType.getValue().equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}

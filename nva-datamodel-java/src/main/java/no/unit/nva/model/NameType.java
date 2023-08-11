package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonValue;
import nva.commons.core.JacocoGenerated;

public enum NameType {

    ORGANIZATIONAL("Organizational"),
    PERSONAL("Personal");

    public static final String ERROR_MESSAGE_TEMPLATE = "%s not a valid NameType, expected one of: %s";
    private String value;

    NameType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
    @JacocoGenerated
    public void setValue(String value) {
        this.value = value;
    }
}

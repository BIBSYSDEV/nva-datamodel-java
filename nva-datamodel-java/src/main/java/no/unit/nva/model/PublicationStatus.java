package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonValue;
import nva.commons.core.JacocoGenerated;

public enum PublicationStatus {

    NEW("NEW"),
    DRAFT("DRAFT"),
    PUBLISHED_METADATA("PUBLISHED_METADATA"),
    PUBLISHED("PUBLISHED"),
    DELETED("DELETED"),
    DRAFT_FOR_DELETION("DRAFT_FOR_DELETION");

    public static final String ERROR_MESSAGE_TEMPLATE = "%s not a valid PublicationStatus, expected one of: %s";
    private String value;

    PublicationStatus(String value) {
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

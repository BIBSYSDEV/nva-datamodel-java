package no.unit.nva.model.contexttypes.media;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MediaSubType {

    JOURNAL("Journal"),
    RADIO("Radio"),
    TV("TV"),
    INTERNET("Internet"),
    OTHER("Other");

    private String value;

    MediaSubType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

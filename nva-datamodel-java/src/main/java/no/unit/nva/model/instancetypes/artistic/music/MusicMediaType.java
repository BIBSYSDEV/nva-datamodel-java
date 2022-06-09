package no.unit.nva.model.instancetypes.artistic.music;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MusicMediaType {
    COMPACT_DISC("CompactDisc"),
    DVD("DVD"),
    STREAMING("Streaming"),
    DIGITAL_FILE("DigitalFile"),
    VINYL("Vinyl"),
    OTHER("Other");

    private final String value;

    MusicMediaType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

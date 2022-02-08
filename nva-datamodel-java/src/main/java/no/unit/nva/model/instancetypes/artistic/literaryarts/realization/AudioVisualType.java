package no.unit.nva.model.instancetypes.artistic.literaryarts.realization;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AudioVisualType {
    AUDIOBOOK("Audiobook"),
    RADIO_PLAY("RadioPlay"),
    SHORT_FILM("ShortFilm"),
    PODCAST("Podcast"),
    OTHER("Other");

    private final String type;

    AudioVisualType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }
}

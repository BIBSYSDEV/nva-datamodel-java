package no.unit.nva.model.instancetypes.artistic.literaryarts.realization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;

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

    @JsonCreator
    public static AudioVisualType fromString(String input) {
        return Arrays.stream(AudioVisualType.values()).filter(value -> value.getType().equalsIgnoreCase(input))
            .findFirst()
            .orElseThrow();
    }

    @JsonValue
    public String getType() {
        return type;
    }
}

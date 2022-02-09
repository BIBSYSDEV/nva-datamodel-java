package no.unit.nva.model.instancetypes.artistic.literaryarts.realization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import nva.commons.core.JacocoGenerated;

import java.util.Arrays;

public enum AudioVisualType {
    AUDIOBOOK("Audiobook"),
    RADIO_PLAY("RadioPlay"),
    SHORT_FILM("ShortFilm"),
    PODCAST("Podcast"),
    OTHER_LITERARY_AUDIO_VISUAL("OtherLiteraryAudioVisual");

    private final String type;

    AudioVisualType(String type) {
        this.type = type;
    }

    @JacocoGenerated
    @JsonCreator
    public static AudioVisualType fromString(String input) {
        return Arrays.stream(AudioVisualType.values())
            .filter(value -> value.getType().equalsIgnoreCase(input))
            .findFirst()
            .orElseThrow();
    }

    @JsonValue
    public String getType() {
        return type;
    }
}

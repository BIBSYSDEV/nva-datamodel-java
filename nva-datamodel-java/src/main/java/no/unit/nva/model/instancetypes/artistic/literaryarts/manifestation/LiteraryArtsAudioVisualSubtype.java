package no.unit.nva.model.instancetypes.artistic.literaryarts.manifestation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import nva.commons.core.SingletonCollector;

import java.util.Arrays;

public enum LiteraryArtsAudioVisualSubtype {
    AUDIOBOOK("Audiobook"),
    RADIO_PLAY("RadioPlay"),
    SHORT_FILM("ShortFilm"),
    PODCAST("Podcast"),
    OTHER("Other");

    private final String name;

    LiteraryArtsAudioVisualSubtype(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public LiteraryArtsAudioVisualSubtype lookup(String candidate) {
        return Arrays.stream(LiteraryArtsAudioVisualSubtype.values())
                .filter(value -> value.getName().equals(candidate))
                .collect(SingletonCollector.collect());
    }
}

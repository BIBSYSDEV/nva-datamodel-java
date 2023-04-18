package no.unit.nva.model.instancetypes.artistic.literaryarts.manifestation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import no.unit.nva.model.instancetypes.artistic.film.MovingPictureSubtypeEnum;
import nva.commons.core.SingletonCollector;

import java.util.Arrays;

public enum LiteraryArtsAudioVisualSubtype {
    AUDIOBOOK("Audiobook"),
    RADIO_PLAY("RadioPlay"),
    SHORT_FILM("ShortFilm"),
    PODCAST("Podcast"),
    OTHER("LiteraryArtsAudioVisualOther");

    private final String name;

    LiteraryArtsAudioVisualSubtype(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    // TODO: Remove following migration
    @Deprecated
    @JsonCreator
    public static MovingPictureSubtypeEnum parseWithDeprecated(String candidate) {
        return "Other".equalsIgnoreCase(candidate)
                ? MovingPictureSubtypeEnum.OTHER
                : parse(candidate);
    }

    //  @JsonCreator
    public static MovingPictureSubtypeEnum parse(String candidate) {
        return Arrays.stream(MovingPictureSubtypeEnum.values())
                .filter(value -> value.getType().equalsIgnoreCase(candidate))
                .collect(SingletonCollector.collect());
    }
}

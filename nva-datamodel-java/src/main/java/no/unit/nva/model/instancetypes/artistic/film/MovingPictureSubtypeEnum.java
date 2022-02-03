package no.unit.nva.model.instancetypes.artistic.film;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MovingPictureSubtypeEnum {
    FILM("Film"),
    SHORT("ShortFilm"),
    SERIAL("SerialFilmProduction"),
    INTERACTIVE("InteractiveFilm"),
    AUGMENTED_VIRTUAL_REALITY("AugmentedVirtualRealityFilm"),
    OTHER("Other");

    @JsonValue
    private final String type;

    MovingPictureSubtypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

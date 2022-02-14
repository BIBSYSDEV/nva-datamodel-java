package no.unit.nva.model.instancetypes.artistic.film;

import com.fasterxml.jackson.annotation.JsonValue;
import no.unit.nva.model.instancetypes.artistic.IsType;

public enum MovingPictureSubtypeEnum implements IsType {
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

    @Override
    public String getType() {
        return type;
    }
}

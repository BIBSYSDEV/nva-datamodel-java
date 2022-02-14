package no.unit.nva.model.instancetypes.artistic.literaryarts;

import com.fasterxml.jackson.annotation.JsonValue;
import no.unit.nva.model.instancetypes.artistic.IsType;

public enum LiteraryArtsSubtypeEnum implements IsType {
    NOVEL("Novel"),
    POETRY("Poetry"),
    NOVELLA("Novella"),
    SHORT_STORY("ShortStory"),
    ESSAY("Essay"),
    TRANSLATION("Translation"),
    RETELLING("Retelling"),
    PLAY("Play"),
    OTHER("Other");

    private final String type;

    LiteraryArtsSubtypeEnum(String type) {
        this.type = type;
    }

    @JsonValue
    @Override
    public String getType() {
        return type;
    }
}

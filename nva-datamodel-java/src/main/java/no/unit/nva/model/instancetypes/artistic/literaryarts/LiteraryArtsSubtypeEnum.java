package no.unit.nva.model.instancetypes.artistic.literaryarts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import nva.commons.core.SingletonCollector;

import java.util.Arrays;

public enum LiteraryArtsSubtypeEnum {
    NOVEL("Novel"),
    POETRY("Poetry"),
    NOVELLA("Novella"),
    SHORT_FICTION("ShortFiction"),
    ESSAY("Essay"),
    TRANSLATION("Translation"),
    RETELLING("Retelling"),
    PLAY("Play"),
    OTHER("Other");

    private final String name;

    LiteraryArtsSubtypeEnum(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public LiteraryArtsSubtypeEnum lookup(String candidate) {
        return Arrays.stream(LiteraryArtsSubtypeEnum.values())
                .filter(value -> value.getName().equalsIgnoreCase(candidate))
                .collect(SingletonCollector.collect());
    }
}

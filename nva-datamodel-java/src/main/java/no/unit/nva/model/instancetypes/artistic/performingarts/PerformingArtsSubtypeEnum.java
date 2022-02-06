package no.unit.nva.model.instancetypes.artistic.performingarts;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PerformingArtsSubtypeEnum {
    THEATRICAL_PRODUCTION("TheatricalProduction"),
    BROADCAST("Broadcast"),
    OTHER("Other");

    @JsonValue
    private final String type;

    PerformingArtsSubtypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

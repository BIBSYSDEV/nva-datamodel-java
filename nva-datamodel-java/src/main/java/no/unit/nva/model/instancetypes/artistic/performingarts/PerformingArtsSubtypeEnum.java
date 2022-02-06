package no.unit.nva.model.instancetypes.artistic.performingarts;

import com.fasterxml.jackson.annotation.JsonValue;
import no.unit.nva.model.instancetypes.artistic.WithType;

public enum PerformingArtsSubtypeEnum implements WithType {
    THEATRICAL_PRODUCTION("TheatricalProduction"),
    BROADCAST("Broadcast"),
    OTHER("Other");

    @JsonValue
    private final String type;

    PerformingArtsSubtypeEnum(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }
}

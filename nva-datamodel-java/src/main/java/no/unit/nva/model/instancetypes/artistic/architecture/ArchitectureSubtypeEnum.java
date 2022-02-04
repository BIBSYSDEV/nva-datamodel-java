package no.unit.nva.model.instancetypes.artistic.architecture;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ArchitectureSubtypeEnum {
    BUILDING("Building"),
    PLANNING_PROPOSAL("PlanningProposal"),
    LANDSCAPE_ARCHITECTURE("LandscapeArchitecture"),
    INTERIOR("Interior"),
    OTHER("Other");

    @JsonValue
    private final String type;

    ArchitectureSubtypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

package no.unit.nva.model.instancetypes.artistic.literaryarts.realization;

import com.fasterxml.jackson.annotation.JsonGetter;

public enum LivePerformanceType {
    READING("Reading"),
    PLAY("Play"),
    OTHER("Other");

    private final String type;

    LivePerformanceType(String type) {
        this.type = type;
    }

    @JsonGetter
    public String getType() {
        return type;
    }
}

package no.unit.nva.model.instancetypes.artistic.literaryarts.realization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import nva.commons.core.JacocoGenerated;

import java.util.Arrays;

public enum LivePerformanceType {
    READING("Reading"),
    PLAY("Play"),
    OTHER_LITERARY_LIVE_PERFORMANCE("OtherLiteraryLivePerformance");

    private final String type;

    LivePerformanceType(String type) {
        this.type = type;
    }

    @JacocoGenerated
    @JsonCreator
    public static LivePerformanceType fromString(String input) {
        return Arrays.stream(LivePerformanceType.values())
                .filter(value -> value.getType().equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow();
    }

    @JsonValue
    public String getType() {
        return type;
    }
}

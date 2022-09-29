package no.unit.nva.model.instancetypes.artistic.literaryarts.manifestation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import nva.commons.core.SingletonCollector;

import java.util.Arrays;

public enum LiteraryArtsPerformanceSubtype {
    READING("Reading"),
    PLAY("Play"),
    OTHER("Other");

    private final String name;

    LiteraryArtsPerformanceSubtype(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public LiteraryArtsPerformanceSubtype lookup(String candidate) {
        return Arrays.stream(LiteraryArtsPerformanceSubtype.values())
                .filter(value -> value.getName().equals(candidate))
                .collect(SingletonCollector.collect());
    }
}

package no.unit.nva.model.instancetypes.artistic.literaryarts.manifestation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import nva.commons.core.SingletonCollector;

import java.util.Arrays;

public enum LiteraryArtsPerformanceSubtype {
    READING("Reading"),
    PLAY("Play"),
    OTHER("LiteraryArtsPerformanceOther");

    private final String name;

    LiteraryArtsPerformanceSubtype(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    // TODO: Remove following migration
    @Deprecated
    @JsonCreator
    public static LiteraryArtsPerformanceSubtype parseWithDeprecated(String candidate) {
        return "Other".equalsIgnoreCase(candidate)
                ? LiteraryArtsPerformanceSubtype.OTHER
                : parse(candidate);
    }

    // @JsonCreator
    public static LiteraryArtsPerformanceSubtype parse(String candidate) {
        return Arrays.stream(LiteraryArtsPerformanceSubtype.values())
                .filter(value -> value.getName().equals(candidate))
                .collect(SingletonCollector.collect());
    }
}

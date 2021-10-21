package no.unit.nva.model.instancetypes.media;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ContributionType {
    INTERNET("Internet"),
    JOURNAL("Journal"),
    NEWSPAPER("Newspaper"),
    OTHER("Other"),
    RADIO("Radio"),
    TELEVISION("Television");

    private final String name;

    ContributionType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }


    @JsonCreator
    public static ContributionType fromString(String name) {
        return Arrays.stream(values())
            .filter(value -> value.getName().equals(name))
            .findAny()
            .orElseThrow();
    }
}

package no.unit.nva.model.time;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nva.commons.core.JacocoGenerated;

import java.time.LocalDateTime;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Instant implements Time {
    public static final String VALUE = "value";

    @JsonProperty(VALUE)
    private final LocalDateTime value;

    @JsonCreator
    public Instant(@JsonProperty(VALUE) LocalDateTime value) {
        this.value = value;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Instant)) {
            return false;
        }
        Instant instant = (Instant) o;
        return Objects.equals(value, instant.value);
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

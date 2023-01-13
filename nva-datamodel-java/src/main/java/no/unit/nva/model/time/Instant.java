package no.unit.nva.model.time;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Instant implements Time {
    public static final String VALUE = "value";

    @JsonProperty(VALUE)
    private final java.time.Instant value;

    @JsonCreator
    public Instant(@JsonProperty(VALUE) java.time.Instant value) {
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

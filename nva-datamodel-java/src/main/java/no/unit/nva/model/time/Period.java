package no.unit.nva.model.time;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Period implements Time {
    public static final String FROM = "from";
    public static final String TO = "to";
    @JsonProperty(FROM)
    private final java.time.Instant from;
    @JsonProperty(TO)
    private final java.time.Instant to;

    @JsonCreator
    public Period(@JsonProperty(FROM) java.time.Instant from, @JsonProperty(TO) java.time.Instant to) {
        this.from = from;
        this.to = to;
    }

    public java.time.Instant getFrom() {
        return from;
    }

    public java.time.Instant getTo() {
        return to;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Period)) {
            return false;
        }
        Period that = (Period) o;
        return Objects.equals(getFrom(), that.getFrom())
                && Objects.equals(getTo(), that.getTo());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo());
    }
}

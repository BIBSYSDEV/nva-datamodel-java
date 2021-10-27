package no.unit.nva.model.time;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.LocalDateTime;
import java.util.Objects;

import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Period implements Time {
    public static final String FROM = "from";
    public static final String TO = "to";
    @JsonProperty(FROM)
    private final LocalDateTime from;
    @JsonProperty(TO)
    private final LocalDateTime to;

    @JsonCreator
    public Period(@JsonProperty(FROM) LocalDateTime from, @JsonProperty(TO) LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
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

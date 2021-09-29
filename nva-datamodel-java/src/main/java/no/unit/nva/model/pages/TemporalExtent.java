package no.unit.nva.model.pages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.contexttypes.event.ReifiedExtent;
import nva.commons.core.JacocoGenerated;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class TemporalExtent implements Pages {
    public static final String FROM = "from";
    public static final String TO = "to";
    @JsonProperty(FROM)
    private final LocalDateTime from;
    @JsonProperty(TO)
    private final LocalDateTime to;

    public TemporalExtent(@JsonProperty(FROM) LocalDateTime from, @JsonProperty(TO) LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public ReifiedExtent getExtent() {
        return new ReifiedExtent(String.valueOf(ChronoUnit.HOURS.between(from, to)), ChronoUnit.HOURS.toString());
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TemporalExtent)) {
            return false;
        }
        TemporalExtent that = (TemporalExtent) o;
        return Objects.equals(getFrom(), that.getFrom())
                && Objects.equals(getTo(), that.getTo());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo());
    }
}

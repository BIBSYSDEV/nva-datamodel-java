package no.unit.nva.model.contexttypes.venue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.contexttypes.place.Place;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Venue {
    public static final String PLACE = "place";
    public static final String SEQUENCE = "sequence";

    @JsonProperty(PLACE)
    private final Place place;
    @JsonProperty(SEQUENCE)
    private final int sequence;

    public Venue(@JsonProperty(PLACE) Place place, @JsonProperty(SEQUENCE) int sequence) {
        this.place = place;
        this.sequence = sequence;
    }

    public Place getPlace() {
        return place;
    }

    public int getSequence() {
        return sequence;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Venue)) {
            return false;
        }
        Venue venue = (Venue) o;
        return getSequence() == venue.getSequence() && Objects.equals(getPlace(), venue.getPlace());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPlace(), getSequence());
    }
}

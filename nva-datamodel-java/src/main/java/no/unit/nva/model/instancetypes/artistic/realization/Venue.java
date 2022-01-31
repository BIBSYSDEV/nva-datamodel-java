package no.unit.nva.model.instancetypes.artistic.realization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;
import no.unit.nva.model.contexttypes.place.Place;
import no.unit.nva.model.time.Time;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Venue {
    public static final String PLACE = "place";
    public static final String SEQUENCE = "sequence";
    public static final String TIME = "time";

    @JsonProperty(PLACE)
    private final Place place;
    @JsonProperty(SEQUENCE)
    private final int sequence;
    @JsonProperty(TIME)
    private final Time time;

    public Venue(@JsonProperty(PLACE) Place place,
                 @JsonProperty(TIME) Time time,
                 @JsonProperty(SEQUENCE) int sequence) {
        this.place = place;
        this.time = time;
        this.sequence = sequence;
    }

    public Place getPlace() {
        return place;
    }


    public Time getTime() {
        return time;
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
        return getSequence() == venue.getSequence()
                && Objects.equals(getPlace(), venue.getPlace())
                && Objects.equals(getTime(), venue.getTime());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPlace(), getSequence(), getTime());
    }
}

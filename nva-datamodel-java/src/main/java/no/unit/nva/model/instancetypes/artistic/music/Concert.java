package no.unit.nva.model.instancetypes.artistic.music;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import no.unit.nva.model.contexttypes.place.UnconfirmedPlace;
import no.unit.nva.model.time.Time;
import nva.commons.core.JacocoGenerated;

public class Concert implements MusicPerformanceManifestation {

    public static final String PLACE = "place";
    public static final String TIME = "time";
    public static final String EXTENT = "extent";
    public static final String DESCRIPTION = "description";
    public static final String CONCERT_PROGRAMME = "concertProgramme";

    @JsonProperty(PLACE)
    private final UnconfirmedPlace place;
    @JsonProperty(TIME)
    private final Time time;
    @JsonProperty(EXTENT)
    private final String extent;
    @JsonProperty(DESCRIPTION)
    private final String description;
    @JsonProperty(CONCERT_PROGRAMME)
    private final ConcertProgramme concertProgramme;

    @JsonCreator
    public Concert(@JsonProperty(PLACE) UnconfirmedPlace place,
                   @JsonProperty(TIME) Time time,
                   @JsonProperty(EXTENT) String extent,
                   @JsonProperty(DESCRIPTION) String description,
                   @JsonProperty(CONCERT_PROGRAMME) ConcertProgramme concertProgramme) {
        this.place = place;
        this.time = time;
        this.extent = extent;
        this.description = description;
        this.concertProgramme = concertProgramme;
    }

    public UnconfirmedPlace getPlace() {
        return place;
    }

    public Time getTime() {
        return time;
    }

    public String getExtent() {
        return extent;
    }

    public String getDescription() {
        return description;
    }

    public ConcertProgramme getConcertProgramme() {
        return concertProgramme;
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getPlace(), getTime(), getExtent(), getDescription(), getConcertProgramme());
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Concert)) {
            return false;
        }
        Concert concert = (Concert) o;
        return Objects.equals(getPlace(), concert.getPlace())
               && Objects.equals(getTime(), concert.getTime())
               && Objects.equals(getExtent(), concert.getExtent())
               && Objects.equals(getDescription(), concert.getDescription())
               && Objects.equals(getConcertProgramme(), concert.getConcertProgramme());
    }
}

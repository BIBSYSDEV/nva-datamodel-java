package no.unit.nva.model.instancetypes.artistic.music;

import static no.unit.nva.model.util.SerializationUtils.nullListAsEmpty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;
import no.unit.nva.model.contexttypes.place.UnconfirmedPlace;
import no.unit.nva.model.time.Time;
import nva.commons.core.JacocoGenerated;

public class Concert implements MusicPerformanceManifestation {

    public static final String PLACE_FIELD = "place";
    public static final String TIME_FIELD = "time";
    public static final String EXTENT_FIELD = "extent";
    public static final String DESCRIPTION_FIELD = "description";
    public static final String CONCERT_PROGRAMME_FIELD = "concertProgramme";
    public static final String SERIES_FIELD = "series";

    @JsonProperty(PLACE_FIELD)
    private final UnconfirmedPlace place;
    @JsonProperty(TIME_FIELD)
    private final Time time;
    @JsonProperty(EXTENT_FIELD)
    private final String extent;
    @JsonProperty(DESCRIPTION_FIELD)
    private final String description;
    @JsonProperty(CONCERT_PROGRAMME_FIELD)
    private final List<MusicalWorkPerformance> concertProgramme;
    @JsonProperty(SERIES_FIELD)
    private final String series;

    @JsonCreator
    public Concert(@JsonProperty(PLACE_FIELD) UnconfirmedPlace place,
                   @JsonProperty(TIME_FIELD) Time time,
                   @JsonProperty(EXTENT_FIELD) String extent,
                   @JsonProperty(DESCRIPTION_FIELD) String description,
                   @JsonProperty(CONCERT_PROGRAMME_FIELD) List<MusicalWorkPerformance> concertProgramme,
                   @JsonProperty(SERIES_FIELD) String series) {
        this.place = place;
        this.time = time;
        this.extent = extent;
        this.description = description;
        this.concertProgramme = nullListAsEmpty(concertProgramme);
        this.series = series;
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

    public List<MusicalWorkPerformance> getConcertProgramme() {
        return concertProgramme;
    }

    public String getSeries() {
        return series;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Concert concert = (Concert) o;
        return Objects.equals(getPlace(), concert.getPlace())
               && Objects.equals(getTime(), concert.getTime())
               && Objects.equals(getExtent(), concert.getExtent())
               && Objects.equals(getDescription(), concert.getDescription())
               && Objects.equals(getConcertProgramme(), concert.getConcertProgramme())
               && Objects.equals(getSeries(), concert.getSeries());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPlace(), getTime(), getExtent(), getDescription(), getConcertProgramme(), getSeries());
    }
}

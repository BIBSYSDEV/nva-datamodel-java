package no.unit.nva.model.instancetypes.artistic.literaryarts.realization;

import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.model.contexttypes.place.Place;
import no.unit.nva.model.contexttypes.place.UnconfirmedPlace;
import no.unit.nva.model.time.Instant;
import no.unit.nva.model.time.Time;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

public class LivePerformance implements LiteraryArtsOutput {
    public static final String TYPE = "type";
    public static final String PLACE = "place";
    public static final String DATE = "date";
    @JsonProperty(TYPE)
    private final LivePerformanceType type;
    @JsonProperty(PLACE)
    private final Place place;
    @JsonProperty(DATE)
    private final Time date;

    public LivePerformance(@JsonProperty(TYPE) LivePerformanceType type,
                           @JsonProperty(PLACE) UnconfirmedPlace place,
                           @JsonProperty(DATE) Instant date) {
        this.type = type;
        this.place = place;
        this.date = date;
    }

    public LivePerformanceType getType() {
        return type;
    }

    public Place getPlace() {
        return place;
    }

    public Time getDate() {
        return date;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LivePerformance)) {
            return false;
        }
        LivePerformance that = (LivePerformance) o;
        return Objects.equals(getType(), that.getType())
                && Objects.equals(getPlace(), that.getPlace())
                && Objects.equals(getDate(), that.getDate());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getType(), getPlace(), getDate());
    }
}

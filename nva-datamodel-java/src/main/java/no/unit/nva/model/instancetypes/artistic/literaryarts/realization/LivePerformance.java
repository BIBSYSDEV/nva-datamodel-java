package no.unit.nva.model.instancetypes.artistic.literaryarts.realization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import java.util.Objects;
import no.unit.nva.model.contexttypes.place.Place;
import no.unit.nva.model.contexttypes.place.UnconfirmedPlace;
import no.unit.nva.model.time.Instant;
import no.unit.nva.model.time.Time;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.EXISTING_PROPERTY, property = "type", visible = true)
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
    public int hashCode() {
        return Objects.hash(getType(), getPlace(), getDate());
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
}

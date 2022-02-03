package no.unit.nva.model.instancetypes.artistic.film.realization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.contexttypes.UnconfirmedPublisher;
import no.unit.nva.model.contexttypes.place.UnconfirmedPlace;
import no.unit.nva.model.time.Instant;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class OtherRelease implements MovingPictureOutput {
    public static final String DESCRIPTION = "description";
    public static final String PLACE = "place";
    public static final String PUBLISHER = "publisher";
    public static final String DATE = "date";

    @JsonProperty(DESCRIPTION)
    private final String description;
    @JsonProperty(PLACE)
    private final UnconfirmedPlace place;
    @JsonProperty(PUBLISHER)
    private final UnconfirmedPublisher publisher;
    @JsonProperty(DATE)
    private final Instant date;
    @JsonProperty(SEQUENCE)
    private final int sequence;

    public OtherRelease(@JsonProperty(DESCRIPTION) String description,
                        @JsonProperty(PLACE) UnconfirmedPlace place,
                        @JsonProperty(PUBLISHER) UnconfirmedPublisher publisher,
                        @JsonProperty(DATE) Instant date,
                        @JsonProperty(SEQUENCE) int sequence) {
        this.description = description;
        this.place = place;
        this.publisher = publisher;
        this.date = date;
        this.sequence = sequence;
    }

    public String getDescription() {
        return description;
    }

    public UnconfirmedPlace getPlace() {
        return place;
    }

    public UnconfirmedPublisher getPublisher() {
        return publisher;
    }

    public Instant getDate() {
        return date;
    }

    @Override
    public int getSequence() {
        return sequence;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof OtherRelease)) {
            return false;
        }
        OtherRelease that = (OtherRelease) o;
        return getSequence() == that.getSequence()
                && Objects.equals(getDescription(), that.getDescription())
                && Objects.equals(getPlace(), that.getPlace())
                && Objects.equals(getPublisher(), that.getPublisher())
                && Objects.equals(getDate(), that.getDate());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getPlace(), getPublisher(), getDate(), getSequence());
    }
}

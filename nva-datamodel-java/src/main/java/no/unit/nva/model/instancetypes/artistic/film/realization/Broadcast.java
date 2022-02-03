package no.unit.nva.model.instancetypes.artistic.film.realization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.contexttypes.UnconfirmedPublisher;
import no.unit.nva.model.time.Instant;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Broadcast implements MovingPictureOutput {

    public static final String PUBLISHER = "publisher";
    public static final String DATE = "date";

    @JsonProperty(PUBLISHER)
    private final UnconfirmedPublisher publisher;
    @JsonProperty(DATE)
    private final Instant date;
    @JsonProperty(SEQUENCE)
    private final int sequence;

    public Broadcast(@JsonProperty(PUBLISHER) UnconfirmedPublisher publisher,
                     @JsonProperty(DATE) Instant date,
                     @JsonProperty(SEQUENCE) int sequence) {
        this.publisher = publisher;
        this.date = date;
        this.sequence = sequence;
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
        if (!(o instanceof Broadcast)) {
            return false;
        }
        Broadcast broadcast = (Broadcast) o;
        return getSequence() == broadcast.getSequence()
                && Objects.equals(getPublisher(), broadcast.getPublisher())
                && Objects.equals(getDate(), broadcast.getDate());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPublisher(), getDate(), getSequence());
    }
}

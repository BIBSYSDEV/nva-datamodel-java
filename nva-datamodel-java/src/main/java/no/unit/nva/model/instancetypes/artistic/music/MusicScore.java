package no.unit.nva.model.instancetypes.artistic.music;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import java.util.Objects;
import no.unit.nva.model.contexttypes.UnconfirmedPublisher;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = Id.NAME, property = "type")
public class MusicScore implements MusicPerformanceManifestation {

    public static final String ENSEMBLE = "ensemble";
    public static final String MOVEMENTS = "movements";
    public static final String EXTENT = "extent";
    public static final String PUBLISHER = "publisher";
    public static final String ISMN = "ismn";
    public static final String ISRC = "isrc";

    @JsonProperty(ENSEMBLE)
    private final String ensemble;
    @JsonProperty(MOVEMENTS)
    private final String movements;
    @JsonProperty(EXTENT)
    private final String extent;
    @JsonProperty(PUBLISHER)
    private final UnconfirmedPublisher publisher;
    @JsonProperty(ISMN)
    private final Ismn ismn;
    @JsonProperty(ISRC)
    private final Isrc isrc;

    @JsonCreator
    public MusicScore(@JsonProperty(ENSEMBLE) String ensemble,
                      @JsonProperty(MOVEMENTS) String movements,
                      @JsonProperty(EXTENT) String extent,
                      @JsonProperty(PUBLISHER) UnconfirmedPublisher publisher,
                      @JsonProperty(ISMN) Ismn ismn,
                      @JsonProperty(ISRC) Isrc isrc) {

        this.ensemble = ensemble;
        this.movements = movements;
        this.extent = extent;
        this.publisher = publisher;
        this.ismn = ismn;
        this.isrc = isrc;
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getEnsemble(), getMovements(), getExtent(), getPublisher(), getIsmn(), getIsrc());
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MusicScore)) {
            return false;
        }
        MusicScore that = (MusicScore) o;
        return Objects.equals(getEnsemble(), that.getEnsemble())
               && Objects.equals(getMovements(), that.getMovements())
               && Objects.equals(getExtent(), that.getExtent())
               && Objects.equals(getPublisher(), that.getPublisher())
               && Objects.equals(getIsmn(), that.getIsmn())
               && Objects.equals(getIsrc(), that.getIsrc());
    }

    public String getEnsemble() {
        return ensemble;
    }

    public String getMovements() {
        return movements;
    }

    public String getExtent() {
        return extent;
    }

    public UnconfirmedPublisher getPublisher() {
        return publisher;
    }

    public Ismn getIsmn() {
        return ismn;
    }

    public Isrc getIsrc() {
        return isrc;
    }
}

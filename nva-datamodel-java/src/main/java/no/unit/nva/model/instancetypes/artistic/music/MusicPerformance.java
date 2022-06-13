package no.unit.nva.model.instancetypes.artistic.music;

import static no.unit.nva.model.util.SerializationUtils.nullListAsEmpty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
import java.util.Objects;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.NullPages;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class MusicPerformance implements PublicationInstance<NullPages> {

    public static final String MANIFESTATIONS = "manifestations";
    @JsonProperty(MANIFESTATIONS)
    private final List<MusicPerformanceManifestation> manifestations;

    @JsonCreator
    public MusicPerformance(@JsonProperty(MANIFESTATIONS) List<MusicPerformanceManifestation> manifestations) {
        this.manifestations = nullListAsEmpty(manifestations);
    }

    public List<MusicPerformanceManifestation> getManifestations() {
        return manifestations;
    }

    @Override
    public NullPages getPages() {
        return new NullPages();
    }

    @Override
    public void setPages(NullPages pages) {
        // NO-OP
    }

    @Override
    public boolean isPeerReviewed() {
        return false;
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MusicPerformance)) {
            return false;
        }
        MusicPerformance that = (MusicPerformance) o;
        return Objects.equals(getManifestations(), that.getManifestations());
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getManifestations());
    }
}

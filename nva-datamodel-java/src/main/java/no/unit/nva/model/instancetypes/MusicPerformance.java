package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
import no.unit.nva.model.instancetypes.artistic.music.MusicPerformanceManifestation;
import no.unit.nva.model.pages.NullPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class MusicPerformance implements PublicationInstance<NullPages> {

    public static final String MANIFESTATIONS = "manifestations";
    @JsonProperty(MANIFESTATIONS)
    private final List<MusicPerformanceManifestation> manifestations;

    @JsonCreator
    public MusicPerformance(@JsonProperty(MANIFESTATIONS) List<MusicPerformanceManifestation> manifestations) {
        this.manifestations = manifestations;
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
}

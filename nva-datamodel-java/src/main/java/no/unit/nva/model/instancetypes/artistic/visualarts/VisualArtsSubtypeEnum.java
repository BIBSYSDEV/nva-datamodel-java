package no.unit.nva.model.instancetypes.artistic.visualarts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VisualArtsSubtypeEnum {
    INDIVIDUAL_EXHIBITION("IndividualExhibition"),
    COLLECTIVE_EXHIBITION("CollectiveExhibition"),
    INSTALLATION("Installation"),
    ART_IN_PUBLIC_SPACE("ArtInPublicSpace"),
    PERFORMANCE("Performance"),
    VISUAL_ARTS("VisualArts"),
    AUDIO_ART("AudioArt"),
    ARTIST_BOOK("ArtistBook"),
    OTHER("Other");

    private final String type;

    @JsonCreator
    VisualArtsSubtypeEnum(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }
}

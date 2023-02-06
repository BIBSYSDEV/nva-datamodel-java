package no.unit.nva.model.instancetypes.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class PopularScienceMonograph extends BookMonograph {

    private static final boolean ORIGINAL_RESEARCH = false;

    public PopularScienceMonograph(@JsonProperty(PAGES_FIELD) MonographPages pages,
                                   @JsonProperty(PEER_REVIEWED_FIELD) boolean peerReviewed) {
        super(pages, ORIGINAL_RESEARCH, peerReviewed);
    }
}

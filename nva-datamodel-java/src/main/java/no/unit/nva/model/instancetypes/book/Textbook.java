package no.unit.nva.model.instancetypes.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.model.pages.MonographPages;

public class Textbook extends BookMonograph {

    private static final boolean ORIGINAL_RESEARCH = false;

    public Textbook(@JsonProperty(PAGES_FIELD) MonographPages pages,
                    @JsonProperty(PEER_REVIEWED_FIELD) boolean peerReviewed) {
        super(pages, ORIGINAL_RESEARCH, peerReviewed);
    }
}

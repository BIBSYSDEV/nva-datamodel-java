package no.unit.nva.model.instancetypes.chapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.model.pages.Range;

public class TextbookChapter extends ChapterArticle {

    private static final boolean ORIGINAL_RESEARCH = false;

    public TextbookChapter(@JsonProperty(PAGES_FIELD) Range pages,
                           @JsonProperty(PEER_REVIEWED_FIELD) boolean peerReviewed) {
        super(pages, peerReviewed, ORIGINAL_RESEARCH);
    }
}

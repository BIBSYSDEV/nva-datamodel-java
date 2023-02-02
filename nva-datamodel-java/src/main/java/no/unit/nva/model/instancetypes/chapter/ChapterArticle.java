package no.unit.nva.model.instancetypes.chapter;

import no.unit.nva.model.instancetypes.PeerReviewedPaper;
import no.unit.nva.model.pages.Range;

public class ChapterArticle extends PeerReviewedPaper {

    public static final String PAGES_FIELD = "pages";
    public static final String PEER_REVIEWED_FIELD = "peerReviewed";

    public ChapterArticle(Range pages,
                          boolean peerReviewed,
                          boolean originalResearch) {
        super(pages, peerReviewed, originalResearch);
    }
}

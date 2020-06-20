package no.unit.nva.model.instancetypes;

import no.unit.nva.model.pages.Range;

public class NonPeerReviewedPaper extends NonPeerReviewed implements PublicationInstance<Range> {
    private Range pages;

    protected NonPeerReviewedPaper() {
        super();
    }

    protected NonPeerReviewedPaper(Range pages) {
        super();
        this.pages = pages;
    }

    @Override
    public Range getPages() {
        return this.pages;
    }

    @Override
    public void setPages(Range pages) {
        this.pages = pages;
    }

    @Override
    public boolean isPeerReviewed() {
        return false;
    }
}

package no.unit.nva.model.instancetypes;

import no.unit.nva.model.pages.Range;
import nva.commons.utils.JacocoGenerated;

import java.util.Objects;

public class NonPeerReviewedPaper extends NonPeerReviewed<Range> {
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

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NonPeerReviewedPaper)) {
            return false;
        }
        NonPeerReviewedPaper that = (NonPeerReviewedPaper) o;
        return Objects.equals(getPages(), that.getPages());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPages());
    }
}

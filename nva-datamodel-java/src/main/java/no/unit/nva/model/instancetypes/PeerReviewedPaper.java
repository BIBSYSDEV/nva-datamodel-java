package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import no.unit.nva.model.pages.Range;
import nva.commons.core.JacocoGenerated;

public class PeerReviewedPaper extends PeerReviewed<Range> {

    @JsonProperty("originalResearch")
    protected boolean originalResearch;
    @JsonProperty("pages")
    private Range pages;

    public PeerReviewedPaper() {
        super();
    }

    public PeerReviewedPaper(Range pages, boolean peerReviewed, boolean originalResearch) {
        super(peerReviewed);
        this.pages = pages;
        this.originalResearch = originalResearch;
    }

    @Override
    public Range getPages() {
        return pages;
    }

    @Override
    public void setPages(Range pages) {
        this.pages = pages;
    }

    public boolean isOriginalResearch() {
        return originalResearch;
    }

    public void setOriginalResearch(boolean originalResearch) {
        this.originalResearch = originalResearch;
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPages(), isPeerReviewed(), isOriginalResearch());
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PeerReviewedPaper)) {
            return false;
        }
        PeerReviewedPaper that = (PeerReviewedPaper) o;
        return isPeerReviewed() == that.isPeerReviewed()
               && Objects.equals(getPages(), that.getPages())
               && isOriginalResearch() == that.isOriginalResearch();
    }
}

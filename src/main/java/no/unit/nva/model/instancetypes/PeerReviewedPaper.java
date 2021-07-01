package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.model.pages.Range;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

public class PeerReviewedPaper extends PeerReviewed<Range> {

    @JsonProperty("pages")
    private Range pages;
    @JsonProperty("originalResearch")
    protected boolean originalResearch;


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

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPages(), isPeerReviewed(), isOriginalResearch());
    }

}

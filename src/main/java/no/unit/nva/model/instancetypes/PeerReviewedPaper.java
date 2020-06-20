package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JacocoGenerated;

import java.util.Objects;

public class PeerReviewedPaper implements PublicationInstance<Range> {

    @JsonProperty("pages")
    private Range pages;
    @JsonProperty("peerReviewed")
    private boolean peerReviewed;

    public PeerReviewedPaper() {
    }

    public PeerReviewedPaper(Range pages, boolean peerReviewed) {
        this.pages = pages;
        this.peerReviewed = peerReviewed;
    }

    @Override
    public Range getPages() {
        return pages;
    }

    @Override
    public void setPages(Range pages) {
        this.pages = pages;
    }

    @Override
    public boolean isPeerReviewed() {
        return peerReviewed;
    }

    public void setPeerReviewed(boolean peerReviewed) {
        this.peerReviewed = peerReviewed;
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
                && Objects.equals(getPages(), that.getPages());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPages(), isPeerReviewed());
    }
}

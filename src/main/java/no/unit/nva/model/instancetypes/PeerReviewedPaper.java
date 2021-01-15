package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.model.pages.Range;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

public class PeerReviewedPaper extends PeerReviewed<Range> {

    @JsonProperty("pages")
    private Range pages;


    public PeerReviewedPaper() {
        super();
    }

    public PeerReviewedPaper(Range pages, boolean peerReviewed) {
        super(peerReviewed);
        this.pages = pages;
    }

    @Override
    public Range getPages() {
        return pages;
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

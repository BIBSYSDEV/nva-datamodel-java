package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import no.unit.nva.model.pages.MonographPages;
import nva.commons.core.JacocoGenerated;

public class PeerReviewedMonograph extends PeerReviewed<MonographPages> {

    @JsonProperty("pages")
    private MonographPages pages;

    protected PeerReviewedMonograph() {
        super();
    }

    protected PeerReviewedMonograph(MonographPages pages, boolean peerReviewed) {
        super(peerReviewed);
        this.pages = pages;
    }

    @Override
    public MonographPages getPages() {
        return pages;
    }

    @Override
    public void setPages(MonographPages pages) {
        this.pages = pages;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PeerReviewedMonograph)) {
            return false;
        }
        PeerReviewedMonograph that = (PeerReviewedMonograph) o;
        return Objects.equals(getPages(), that.getPages());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPages());
    }
}

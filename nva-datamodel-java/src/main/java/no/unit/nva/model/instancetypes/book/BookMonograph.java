package no.unit.nva.model.instancetypes.book;

import no.unit.nva.model.instancetypes.PeerReviewedMonograph;
import no.unit.nva.model.pages.MonographPages;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

public class BookMonograph extends PeerReviewedMonograph {

    public static final String PAGES_FIELD = "pages";
    public static final String PEER_REVIEWED_FIELD = "peerReviewed";
    private final boolean originalResearch;

    public BookMonograph(MonographPages pages,
                         boolean originalResearch,
                         boolean peerReviewed) {
        super(pages, peerReviewed);
        this.originalResearch = originalResearch;
    }

    public boolean isOriginalResearch() {
        return originalResearch;
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookMonograph)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        BookMonograph that = (BookMonograph) o;
        return isOriginalResearch() == that.isOriginalResearch();
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(super.hashCode(), isOriginalResearch());
    }
}

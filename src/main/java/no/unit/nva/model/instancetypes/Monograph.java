package no.unit.nva.model.instancetypes;

import no.unit.nva.model.pages.MonographPages;
import nva.commons.utils.JacocoGenerated;

import java.util.Objects;

public class Monograph implements PublicationInstance<MonographPages> {

    private MonographPages pages;
    protected boolean peerReviewed;

    protected Monograph() {
    }

    protected Monograph(MonographPages pages, boolean peerReviewed) {
        this.pages = pages;
        this.peerReviewed = peerReviewed;
    }

    @Override
    public MonographPages getPages() {
        return pages;
    }

    @Override
    public void setPages(MonographPages pages) {
        this.pages = pages;
    }

    @Override
    public boolean isPeerReviewed() {
        return peerReviewed;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Monograph)) {
            return false;
        }
        Monograph monograph = (Monograph) o;
        return peerReviewed == monograph.peerReviewed
                && Objects.equals(getPages(), monograph.getPages());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPages(), peerReviewed);
    }
}

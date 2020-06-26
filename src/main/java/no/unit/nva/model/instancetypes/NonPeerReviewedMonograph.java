package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.model.pages.MonographPages;
import nva.commons.utils.JacocoGenerated;

import java.util.Objects;

public class NonPeerReviewedMonograph extends NonPeerReviewed<MonographPages>  {

    @JsonProperty("pages")
    private MonographPages pages;

    public NonPeerReviewedMonograph() {
        super();
    }

    protected NonPeerReviewedMonograph(MonographPages pages) {
        super();
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
        if (!(o instanceof NonPeerReviewedMonograph)) {
            return false;
        }
        NonPeerReviewedMonograph that = (NonPeerReviewedMonograph) o;
        return Objects.equals(getPages(), that.getPages());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPages());
    }
}

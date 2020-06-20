package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import no.unit.nva.model.pages.MonographPages;
import nva.commons.utils.JacocoGenerated;

import java.rmi.UnexpectedException;
import java.util.Objects;

@JsonPropertyOrder({"pages", "peerReviewed"})
public class NonPeerReviewedMonograph implements PublicationInstance<MonographPages> {
    public static final String PEER_REVIEWED_ERROR_TEMPLATE = "%s is assumed not to be peer-reviewed";

    @JsonProperty("pages")
    private MonographPages pages;

    public NonPeerReviewedMonograph() {
    }

    protected NonPeerReviewedMonograph(MonographPages pages) {
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

    @Override
    public boolean isPeerReviewed() {
        return false;
    }

    /**
     * In cases where data is inserted with a value peerReviewed = true, an Unexpected exception will be thrown,
     * otherwise the value is ignored as the class always returns false.
     *
     * @param peerReviewed a boolean value.
     * @throws UnexpectedException thrown if the boolean equals true.
     */
    public void setPeerReviewed(boolean peerReviewed) throws UnexpectedException {
        if (peerReviewed) {
            throw new UnexpectedException(String.format(PEER_REVIEWED_ERROR_TEMPLATE, this.getClass().getSimpleName()));
        }
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

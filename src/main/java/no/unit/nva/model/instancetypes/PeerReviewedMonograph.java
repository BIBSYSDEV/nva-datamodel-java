package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.model.pages.MonographPages;
import nva.commons.utils.JacocoGenerated;

import java.util.Objects;

public class PeerReviewedMonograph extends PeerReviewed<MonographPages> implements TextbookContent {

    @JsonProperty("pages")
    private MonographPages pages;
    private boolean textbookContent;

    protected PeerReviewedMonograph() {
        super();
    }

    protected PeerReviewedMonograph(MonographPages pages, boolean peerReviewed, boolean textbookContent) {
        super(peerReviewed);
        this.pages = pages;
        this.textbookContent = textbookContent;
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
    public boolean isTextbookContent() {
        return textbookContent;
    }

    @Override
    public void setTextbookContent(boolean textbookContent) {
        this.textbookContent = textbookContent;
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
        return isTextbookContent() == that.isTextbookContent()
                && Objects.equals(getPages(), that.getPages());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPages(), isTextbookContent());
    }
}

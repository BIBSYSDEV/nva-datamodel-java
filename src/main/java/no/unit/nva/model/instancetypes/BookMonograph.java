package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Pages;
import nva.commons.utils.JacocoGenerated;

import java.util.Objects;

import static java.util.Objects.nonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class BookMonograph implements PublicationInstance {

    private Pages pages;
    private boolean peerReviewed;
    private boolean openAccess;

    public BookMonograph() {
    }

    private BookMonograph(Builder builder) throws InvalidPageTypeException {
        setPages(builder.pages);
        setPeerReviewed(builder.peerReviewed);
        setOpenAccess(builder.openAccess);
    }

    @Override
    public Pages getPages() {
        return pages;
    }

    @Override
    public void setPages(Pages pages) throws InvalidPageTypeException {
        if (nonNull(pages) && !(pages instanceof MonographPages)) {
            throw new InvalidPageTypeException(BookMonograph.class, MonographPages.class, pages.getClass());
        }
        this.pages = pages;
    }

    @Override
    public void setPeerReviewed(boolean peerReviewed) {
        this.peerReviewed = peerReviewed;
    }

    @Override
    public boolean isPeerReviewed() {
        return peerReviewed;
    }

    public boolean isOpenAccess() {
        return openAccess;
    }

    public void setOpenAccess(boolean openAccess) {
        this.openAccess = openAccess;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookMonograph)) {
            return false;
        }
        BookMonograph that = (BookMonograph) o;
        return isPeerReviewed() == that.isPeerReviewed()
                && isOpenAccess() == that.isOpenAccess()
                && Objects.equals(getPages(), that.getPages());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPages(), isPeerReviewed(), isOpenAccess());
    }

    public static final class Builder {
        private Pages pages;
        private boolean peerReviewed;
        private boolean openAccess;

        public Builder() {
        }

        public Builder withPages(Pages pages) {
            this.pages = pages;
            return this;
        }

        public Builder withPeerReviewed(boolean peerReviewed) {
            this.peerReviewed = peerReviewed;
            return this;
        }

        public Builder withOpenAccess(boolean openAccess) {
            this.openAccess = openAccess;
            return this;
        }

        public BookMonograph build() throws InvalidPageTypeException {
            return new BookMonograph(this);
        }
    }
}

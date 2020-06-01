package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Pages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DegreeBachelor extends BookMonograph {
    public DegreeBachelor() {
        super();
    }

    private DegreeBachelor(Builder builder) throws InvalidPageTypeException {
        super();
        setPages(builder.pages);
        setPeerReviewed(builder.peerReviewed);
        setOpenAccess(builder.openAccess);
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

        public DegreeBachelor build() throws InvalidPageTypeException {
            return new DegreeBachelor(this);
        }
    }
}

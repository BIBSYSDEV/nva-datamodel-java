package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DegreeMaster extends BookMonograph {
    public DegreeMaster() {
        super();
    }

    private DegreeMaster(Builder builder) {
        super();
        setPages(builder.pages);
        setPeerReviewed(builder.peerReviewed);
        setOpenAccess(builder.openAccess);
    }

    public static final class Builder {
        private MonographPages pages;
        private boolean peerReviewed;
        private boolean openAccess;

        public Builder() {
        }

        public Builder withPages(MonographPages pages) {
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

        public DegreeMaster build() {
            return new DegreeMaster(this);
        }
    }
}

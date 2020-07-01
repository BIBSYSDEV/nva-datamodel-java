package no.unit.nva.model.instancetypes.degree;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.NonPeerReviewedMonograph;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DegreeMaster extends NonPeerReviewedMonograph {

    public DegreeMaster() {
        super();
    }

    private DegreeMaster(Builder builder) {
        super(builder.pages);
        setPages(builder.pages);
    }

    public static final class Builder {
        private MonographPages pages;

        public Builder() {
        }

        public Builder withPages(MonographPages pages) {
            this.pages = pages;
            return this;
        }

        public DegreeMaster build() {
            return new DegreeMaster(this);
        }
    }
}

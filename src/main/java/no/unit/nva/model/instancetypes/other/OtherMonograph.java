package no.unit.nva.model.instancetypes.other;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.NonPeerReviewedMonograph;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class OtherMonograph extends NonPeerReviewedMonograph {

    @JsonCreator
    public OtherMonograph(MonographPages pages) {
        super(pages);
    }

    private OtherMonograph(Builder builder) {
        super();
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

        public OtherMonograph build() {
            return new OtherMonograph(this);
        }
    }
}

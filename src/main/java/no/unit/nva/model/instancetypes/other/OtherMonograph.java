package no.unit.nva.model.instancetypes.other;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.NonPeerReviewedMonograph;
import no.unit.nva.model.pages.MonographPages;
import nva.commons.utils.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class OtherMonograph extends NonPeerReviewedMonograph {

    @JacocoGenerated
    @JsonCreator
    public OtherMonograph() {
        super();
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

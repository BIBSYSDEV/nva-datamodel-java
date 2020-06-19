package no.unit.nva.model.instancetypes.degree;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.NonPeerReviewedMonograph;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DegreePhd extends NonPeerReviewedMonograph {

    public DegreePhd(@JsonProperty("pages") MonographPages pages) {
        super(pages);
    }

    private DegreePhd(Builder builder) {
        super(builder.pages);
    }


    public static final class Builder {
        private MonographPages pages;

        public Builder() {
        }

        public Builder withPages(MonographPages pages) {
            this.pages = pages;
            return this;
        }

        public DegreePhd build() {
            return new DegreePhd(this);
        }
    }
}

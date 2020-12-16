package no.unit.nva.model.instancetypes.degree;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.PublicationDate;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DegreeBachelor extends DegreeBase {

    public DegreeBachelor() {
        super();
    }

    private DegreeBachelor(Builder builder) {
        super(builder.pages, builder.submittedDate);
    }

    public static final class Builder {
        private MonographPages pages;
        private PublicationDate submittedDate;

        public Builder() {
        }

        public Builder withPages(MonographPages pages) {
            this.pages = pages;
            return this;
        }

        public Builder withSubmittedDate(PublicationDate submittedDate) {
            this.submittedDate = submittedDate;
            return this;
        }

        public DegreeBachelor build() {
            return new DegreeBachelor(this);
        }
    }
}

package no.unit.nva.model.instancetypes.degree;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.PublicationDate;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DegreeMaster extends DegreeBase {

    public DegreeMaster() {
        super();
    }

    private DegreeMaster(Builder builder) {
        super(builder.pages, builder.submittedDate);
        setPages(builder.pages);
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

        public DegreeMaster.Builder withSubmittedDate(PublicationDate submittedDate) {
            this.submittedDate = submittedDate;
            return this;
        }

        public DegreeMaster build() {
            return new DegreeMaster(this);
        }
    }
}

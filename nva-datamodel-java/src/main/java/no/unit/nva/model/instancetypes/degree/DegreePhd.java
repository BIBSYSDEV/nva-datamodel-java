package no.unit.nva.model.instancetypes.degree;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.PublicationDate;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DegreePhd extends DegreeBase {

    public DegreePhd() {
        super();
    }

    private DegreePhd(Builder builder) {
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

        public DegreePhd.Builder withSubmittedDate(PublicationDate submittedDate) {
            this.submittedDate = submittedDate;
            return this;
        }

        public DegreePhd build() {
            return new DegreePhd(this);
        }
    }
}

package no.unit.nva.model.instancetypes.degree;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.PublicationDate;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DegreeLicentiate extends DegreeBase {

    /**
     * Placeholder class for holding the details of resource type Licentiate thesis.
     * Licentiate thesis: A thesis for the licentiate's degree. It is given by some countries of the European Union,
     * Latin America and Syria. In Swedish and Finnish universities, a licentiate's degree is now recognised
     * as a pre-doctoral degree, in rank above the master's degree.
     */
    public DegreeLicentiate() {
        super();
    }

    private DegreeLicentiate(Builder builder) {
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

        public DegreeLicentiate.Builder withSubmittedDate(PublicationDate submittedDate) {
            this.submittedDate = submittedDate;
            return this;
        }

        public DegreeLicentiate build() {
            return new DegreeLicentiate(this);
        }
    }
}

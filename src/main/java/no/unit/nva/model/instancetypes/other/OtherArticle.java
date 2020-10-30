package no.unit.nva.model.instancetypes.other;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.NonPeerReviewedPaper;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class OtherArticle extends NonPeerReviewedPaper {

    @JacocoGenerated
    @JsonCreator
    public OtherArticle() {
        super();
    }

    private OtherArticle(Builder builder) {
        super();
        setPages(builder.pages);
    }

    public static final class Builder {
        private Range pages;

        public Builder() {
        }

        public Builder withPages(Range pages) {
            this.pages = pages;
            return this;
        }

        public OtherArticle build() {
            return new OtherArticle(this);
        }
    }
}

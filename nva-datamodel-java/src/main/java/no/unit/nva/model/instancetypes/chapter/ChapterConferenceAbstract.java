package no.unit.nva.model.instancetypes.chapter;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import no.unit.nva.model.instancetypes.NonPeerReviewedPaper;
import no.unit.nva.model.pages.Range;

@JsonTypeInfo(use = Id.NAME, property = "type")
public class ChapterConferenceAbstract extends NonPeerReviewedPaper {

    public ChapterConferenceAbstract() {
        super();
    }

    private ChapterConferenceAbstract(Builder builder) {
        super(builder.pages);
    }

    public static final class Builder {
        private Range pages;

        public Builder() {
        }

        public Builder withPages(Range pages) {
            this.pages = pages;
            return this;
        }

        public ChapterConferenceAbstract build() {
            return new ChapterConferenceAbstract(this);
        }

    }
}

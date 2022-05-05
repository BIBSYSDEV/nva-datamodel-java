package no.unit.nva.model.instancetypes.report;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.NonPeerReviewedMonograph;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ReportBookOfAbstract extends NonPeerReviewedMonograph {

    /**
     * Book of abstracts: contains abstracts of presentations (lectures and posters) given at a specific conference.
     * Published by a publisher or by the conference itself.
     */
    public ReportBookOfAbstract() {
        super();
    }

    private ReportBookOfAbstract(Builder builder) {
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

        public ReportBookOfAbstract build() {
            return new ReportBookOfAbstract(this);
        }
    }
}

package no.unit.nva.model.instancetypes.degree;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.NonPeerReviewedMonograph;
import no.unit.nva.model.pages.MonographPages;
import nva.commons.utils.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class OtherStudentWork extends NonPeerReviewedMonograph {

    @JacocoGenerated
    @JsonCreator
    public OtherStudentWork() {
        super();
    }

    private OtherStudentWork(Builder builder) {
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

        public OtherStudentWork build() {
            return new OtherStudentWork(this);
        }
    }
}

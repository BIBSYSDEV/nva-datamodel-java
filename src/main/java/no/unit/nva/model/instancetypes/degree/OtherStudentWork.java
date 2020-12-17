package no.unit.nva.model.instancetypes.degree;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.PublicationDate;
import no.unit.nva.model.pages.MonographPages;
import nva.commons.utils.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class OtherStudentWork extends DegreeBase {

    @JacocoGenerated
    @JsonCreator
    public OtherStudentWork() {
        super();
    }

    private OtherStudentWork(Builder builder) {
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

        public OtherStudentWork.Builder withSubmittedDate(PublicationDate submittedDate) {
            this.submittedDate = submittedDate;
            return this;
        }

        public OtherStudentWork build() {
            return new OtherStudentWork(this);
        }
    }
}

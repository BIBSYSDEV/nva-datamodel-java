package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Pages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ReportWorkingPaper extends ReportContent {

    /**
     * Constructor for ReportWorkingPaper.
     *
     * @param pages the Pages of the PublicationInstance.
     * @throws InvalidPageTypeException if the type of Pages is incompatible with the PublicationInstance type.
     */
    @JsonCreator
    public ReportWorkingPaper(@JsonProperty("pages") Pages pages) throws InvalidPageTypeException {
        super(pages);
    }

    public static final class Builder {
        private Pages pages;

        public Builder() {
        }

        public ReportWorkingPaper.Builder withPages(Pages pages) {
            this.pages = pages;
            return this;
        }

        public ReportWorkingPaper build() throws InvalidPageTypeException {
            return new ReportWorkingPaper(this.pages);
        }
    }
}

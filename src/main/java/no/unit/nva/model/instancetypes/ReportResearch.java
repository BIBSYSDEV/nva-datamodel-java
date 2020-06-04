package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Pages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ReportResearch extends ReportContent {

    /**
     * Constructor for ReportResearch.
     *
     * @param pages the Pages of the PublicationInstance.
     * @throws InvalidPageTypeException if the type of Pages is incompatible with the PublicationInstance type.
     */
    @JsonCreator
    public ReportResearch(@JsonProperty("pages") Pages pages) throws InvalidPageTypeException {
        super(pages);
    }

    public static final class Builder {
        private Pages pages;

        public Builder() {
        }

        public ReportResearch.Builder withPages(Pages pages) {
            this.pages = pages;
            return this;
        }

        public ReportResearch build() throws InvalidPageTypeException {
            return new ReportResearch(this.pages);
        }
    }
}

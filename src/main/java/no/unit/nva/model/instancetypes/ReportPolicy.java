package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Pages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ReportPolicy extends ReportContent {

    /**
     * Constructor for ReportPolicy.
     *
     * @param pages the Pages of the PublicationInstance.
     * @throws InvalidPageTypeException if the type of Pages is incompatible with the PublicationInstance type.
     */
    @JsonCreator
    public ReportPolicy(@JsonProperty("pages") Pages pages) throws InvalidPageTypeException {
        super(pages);
    }

    public static final class Builder {
        private Pages pages;

        public Builder() {
        }

        public ReportPolicy.Builder withPages(Pages pages) {
            this.pages = pages;
            return this;
        }

        public ReportPolicy build() throws InvalidPageTypeException {
            return new ReportPolicy(this.pages);
        }
    }
}

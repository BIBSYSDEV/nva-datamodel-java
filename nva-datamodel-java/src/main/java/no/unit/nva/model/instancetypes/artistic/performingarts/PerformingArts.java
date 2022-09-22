package no.unit.nva.model.instancetypes.artistic.performingarts;

import static no.unit.nva.model.util.SerializationUtils.nullListAsEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.instancetypes.artistic.performingarts.realization.PerformingArtsOutput;
import no.unit.nva.model.pages.NullPages;
import nva.commons.core.JacocoGenerated;

import java.util.List;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class PerformingArts implements PublicationInstance<NullPages> {
    public static final String SUBTYPE = "subtype";
    public static final String DESCRIPTION = "description";
    public static final String OUTPUTS = "outputs";
    @JsonProperty(SUBTYPE)
    private final PerformingArtsSubtype subtype;
    @JsonProperty(DESCRIPTION)
    private final String description;
    @JsonProperty(OUTPUTS)
    private final List<PerformingArtsOutput> outputs;

    public PerformingArts(@JsonProperty(SUBTYPE) PerformingArtsSubtype subtype,
                          @JsonProperty(DESCRIPTION) String description,
                          @JsonProperty(OUTPUTS) List<PerformingArtsOutput> outputs) {
        this.subtype = subtype;
        this.description = description;
        this.outputs = nullListAsEmpty(outputs);
    }

    public PerformingArtsSubtype getSubtype() {
        return subtype;
    }

    public String getDescription() {
        return description;
    }

    public List<PerformingArtsOutput> getOutputs() {
        return outputs;
    }

    @Override
    public NullPages getPages() {
        return NullPages.NULL_PAGES;
    }

    @Override
    public void setPages(NullPages pages) {

    }

    @Override
    public boolean isPeerReviewed() {
        return false;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PerformingArts)) {
            return false;
        }
        PerformingArts that = (PerformingArts) o;
        return Objects.equals(getSubtype(), that.getSubtype())
                && Objects.equals(getDescription(), that.getDescription())
                && Objects.equals(getOutputs(), that.getOutputs());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getSubtype(), getDescription(), getOutputs());
    }
}

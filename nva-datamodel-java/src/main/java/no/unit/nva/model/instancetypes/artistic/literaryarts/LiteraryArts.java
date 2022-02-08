package no.unit.nva.model.instancetypes.artistic.literaryarts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.instancetypes.artistic.literaryarts.realization.LiteraryArtsOutput;
import no.unit.nva.model.pages.NullPages;
import nva.commons.core.JacocoGenerated;

import java.util.List;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class LiteraryArts implements PublicationInstance<NullPages> {
    private static final String SUBTYPE = "subtype";
    private static final String OUTPUTS = "outputs";

    @JsonProperty(SUBTYPE)
    private final LiteraryArtsSubtype subtype;
    @JsonProperty(OUTPUTS)
    private final List<LiteraryArtsOutput> outputs;

    public LiteraryArts(@JsonProperty(SUBTYPE) LiteraryArtsSubtype subtype,
                        @JsonProperty(OUTPUTS) List<LiteraryArtsOutput> outputs) {
        this.subtype = subtype;
        this.outputs = outputs;
    }

    public LiteraryArtsSubtype getSubtype() {
        return subtype;
    }


    public List<LiteraryArtsOutput> getOutputs() {
        return outputs;
    }

    @Override
    public NullPages getPages() {
        return new NullPages();
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
        if (!(o instanceof LiteraryArts)) {
            return false;
        }
        LiteraryArts that = (LiteraryArts) o;
        return Objects.equals(getSubtype(), that.getSubtype())
                && Objects.equals(getOutputs(), that.getOutputs());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getSubtype(), getOutputs());
    }
}

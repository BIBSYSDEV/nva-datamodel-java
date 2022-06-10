package no.unit.nva.model.instancetypes.artistic.film;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.instancetypes.artistic.film.realization.MovingPictureOutput;
import no.unit.nva.model.pages.NullPages;
import nva.commons.core.JacocoGenerated;

import java.util.List;
import java.util.Objects;

import static no.unit.nva.model.util.SerializationUtils.nullListAsEmpty;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class MovingPicture implements PublicationInstance<NullPages> {
    public static final String SUBTYPE = "subtype";
    public static final String DESCRIPTION = "description";
    public static final String OUTPUTS = "outputs";
    @JsonProperty(SUBTYPE)
    private final MovingPictureSubtype subtype;
    @JsonProperty(DESCRIPTION)
    private final String description;
    @JsonProperty(OUTPUTS)
    private final List<MovingPictureOutput> outputs;

    public MovingPicture(
            @JsonProperty(SUBTYPE) MovingPictureSubtype subtype,
            @JsonProperty(DESCRIPTION) String description,
            @JsonProperty(OUTPUTS) List<MovingPictureOutput> outputs) {
        this.subtype = subtype;
        this.description = description;
        this.outputs = nullListAsEmpty(outputs);
    }

    public MovingPictureSubtype getSubtype() {
        return subtype;
    }

    public String getDescription() {
        return description;
    }

    public List<MovingPictureOutput> getOutputs() {
        return outputs;
    }

    @JsonGetter
    @Override
    public NullPages getPages() {
        return new NullPages();
    }

    @JsonSetter
    @Override
    public void setPages(NullPages pages) {

    }

    @JsonGetter
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
        if (!(o instanceof MovingPicture)) {
            return false;
        }
        MovingPicture that = (MovingPicture) o;
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

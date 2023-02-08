package no.unit.nva.model.instancetypes.artistic.film;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    public static final String SUBTYPE_FIELD = "subtype";
    public static final String DESCRIPTION_FIELD = "description";
    public static final String OUTPUTS_FIELD = "outputs";
    @JsonProperty(SUBTYPE_FIELD)
    private final MovingPictureSubtype subtype;
    @JsonProperty(DESCRIPTION_FIELD)
    private final String description;
    @JsonProperty(OUTPUTS_FIELD)
    private final List<MovingPictureOutput> outputs;

    public MovingPicture(
            @JsonProperty(SUBTYPE_FIELD) MovingPictureSubtype subtype,
            @JsonProperty(DESCRIPTION_FIELD) String description,
            @JsonProperty(OUTPUTS_FIELD) List<MovingPictureOutput> outputs) {
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
        return NullPages.NULL_PAGES;
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

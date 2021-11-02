package no.unit.nva.model.instancetypes.artistic;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.NullPages;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ArtisticDesign implements PublicationInstance<NullPages> {

    public static final String SUBTYPE = "subtype";
    public static final String DESCRIPTION = "description";
    @JsonProperty(SUBTYPE)
    private final ArtisticDesignSubtype subtype;
    @JsonProperty(DESCRIPTION)
    private final String description;

    public ArtisticDesign(@JsonProperty(SUBTYPE) ArtisticDesignSubtype subtype,
                          @JsonProperty(DESCRIPTION) String description) {
        this.subtype = subtype;
        this.description = description;
    }

    public ArtisticDesignSubtype getSubtype() {
        return subtype;
    }

    public String getDescription() {
        return description;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArtisticDesign)) {
            return false;
        }
        ArtisticDesign that = (ArtisticDesign) o;
        return Objects.equals(getSubtype(), that.getSubtype())
                && Objects.equals(getDescription(), that.getDescription());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getSubtype(), getDescription());
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
}

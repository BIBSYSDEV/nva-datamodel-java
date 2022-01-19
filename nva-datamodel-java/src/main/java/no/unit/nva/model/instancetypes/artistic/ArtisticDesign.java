package no.unit.nva.model.instancetypes.artistic;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.contexttypes.Artistic;
import no.unit.nva.model.contexttypes.venue.Venue;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.NullPages;
import nva.commons.core.JacocoGenerated;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ArtisticDesign implements PublicationInstance<NullPages> {

    public static final String SUBTYPE = "subtype";
    public static final String DESCRIPTION = "description";
    public static final String VENUES = "venues";

    @JsonProperty(SUBTYPE)
    private final ArtisticDesignSubtype subtype;
    @JsonProperty(DESCRIPTION)
    private final String description;
    @JsonProperty(VENUES)
    private final List<Venue> venues;


    public ArtisticDesign(@JsonProperty(SUBTYPE) ArtisticDesignSubtype subtype,
                          @JsonProperty(DESCRIPTION) String description,
                          @JsonProperty(VENUES) List<Venue> venues) {
        this.subtype = subtype;
        this.description = description;
        this.venues = nonNull(venues) ? venues : Collections.emptyList();
    }

    public ArtisticDesignSubtype getSubtype() {
        return subtype;
    }

    public String getDescription() {
        return description;
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

    @JsonGetter
    public List<Venue> getVenues() {
        return venues;
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
                && Objects.equals(getDescription(), that.getDescription())
                && Objects.equals(getVenues(), that.getVenues());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getSubtype(), getDescription(), getVenues());
    }
}

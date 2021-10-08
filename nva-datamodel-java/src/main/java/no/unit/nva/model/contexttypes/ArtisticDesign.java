package no.unit.nva.model.contexttypes;

import static java.util.Objects.nonNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import no.unit.nva.model.contexttypes.venue.Venue;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ArtisticDesign implements PublicationContext {
    public static final String VENUES = "venues";

    @JsonProperty(VENUES)
    private final List<Venue> venues;

    public ArtisticDesign(@JsonProperty(VENUES) List<Venue> venues) {
        this.venues = nonNull(venues) ? venues : Collections.emptyList();
    }

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
        return Objects.equals(getVenues(), that.getVenues());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getVenues());
    }
}

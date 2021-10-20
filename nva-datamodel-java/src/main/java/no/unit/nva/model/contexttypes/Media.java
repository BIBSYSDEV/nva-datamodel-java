package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Media implements PublicationContext {
    public static final String PROGRAMME = "programme";
    public static final String PLACE = "place";

    @JsonProperty(PLACE)
    private final String place;
    @JsonProperty(PROGRAMME)
    private final String programme;

    @JsonCreator
    public Media(@JsonProperty(PLACE) String place, @JsonProperty(PROGRAMME) String programme) {
        this.place = place;
        this.programme = programme;
    }

    public String getPlace() {
        return place;
    }

    public String getProgramme() {
        return programme;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Media)) {
            return false;
        }
        Media media = (Media) o;
        return Objects.equals(getPlace(), media.getPlace())
                && Objects.equals(getProgramme(), media.getProgramme());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPlace(), getProgramme());
    }
}

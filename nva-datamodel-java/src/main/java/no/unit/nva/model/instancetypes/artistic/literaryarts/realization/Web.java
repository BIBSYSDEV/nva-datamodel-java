package no.unit.nva.model.instancetypes.artistic.literaryarts.realization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.contexttypes.PublishingHouse;
import no.unit.nva.model.time.Instant;
import no.unit.nva.model.time.Time;
import nva.commons.core.JacocoGenerated;

import java.net.URI;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Web implements LiteraryArtsOutput {
    public static final String ID = "id";
    public static final String PUBLISHER = "publisher";
    public static final String DATE = "date";
    @JsonProperty(ID)
    private final URI id;
    @JsonProperty(PUBLISHER)
    private final PublishingHouse publisher;
    @JsonProperty(DATE)
    private final Time date;

    public Web(@JsonProperty(ID) URI id,
               @JsonProperty(PUBLISHER) PublishingHouse publisher,
               @JsonProperty(DATE) Instant date) {
        this.id = id;
        this.publisher = publisher;
        this.date = date;
    }

    public URI getId() {
        return id;
    }

    public PublishingHouse getPublisher() {
        return publisher;
    }

    public Time getDate() {
        return date;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Web)) {
            return false;
        }
        Web web = (Web) o;
        return Objects.equals(getId(), web.getId())
                && Objects.equals(getPublisher(), web.getPublisher())
                && Objects.equals(getDate(), web.getDate());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPublisher(), getDate());
    }
}

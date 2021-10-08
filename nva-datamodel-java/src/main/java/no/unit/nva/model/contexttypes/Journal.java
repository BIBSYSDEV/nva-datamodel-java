package no.unit.nva.model.contexttypes;

import static java.util.Objects.isNull;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import no.unit.nva.model.exceptions.InvalidSeriesException;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Journal implements Periodical {
    private final URI id;

    @JsonCreator
    public Journal(@JsonProperty("id") String id) {
        if (isNull(id) || id.isEmpty()) {
            throw new InvalidSeriesException(id);
        }
        try {
            this.id = new URI(id);
        } catch (URISyntaxException e) {
            throw new InvalidSeriesException(id);
        }
    }

    public URI getId() {
        return id;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Journal)) {
            return false;
        }
        Journal journal = (Journal) o;
        return Objects.equals(getId(), journal.getId());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

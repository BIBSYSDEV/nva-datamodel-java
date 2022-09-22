package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ResearchData implements PublicationContext {

    public static final String PUBLISHER_FIELD = "publisher";
    @JsonProperty(PUBLISHER_FIELD)
    private final PublishingHouse publisher;

    public ResearchData(@JsonProperty(PUBLISHER_FIELD) PublishingHouse publisher) {
        this.publisher = publisher;
    }

    public PublishingHouse getPublisher() {
        return publisher;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResearchData)) {
            return false;
        }
        ResearchData that = (ResearchData) o;
        return Objects.equals(publisher, that.publisher);
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(publisher);
    }
}

package no.unit.nva.model.instancetypes.artistic.literaryarts.realization;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import java.util.List;
import java.util.Objects;
import no.unit.nva.model.contexttypes.PublishingHouse;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.time.Time;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.EXISTING_PROPERTY, property = "type", visible = true)
public class AudioVisual implements LiteraryArtsOutput, WithIsbn {

    public static final String TYPE = "type";
    public static final String PUBLISHER = "publisher";
    public static final String DATE = "date";
    public static final String ISBNS = "isbns";
    public static final String EXTENT = "extent";

    @JsonProperty(TYPE)
    private final AudioVisualType type;
    @JsonProperty(PUBLISHER)
    private final PublishingHouse publisher;
    @JsonProperty(DATE)
    private final Time date;
    @JsonProperty(ISBNS)
    private final List<String> isbns;
    @JsonProperty(EXTENT)
    private final String extent;

    public AudioVisual(@JsonProperty(TYPE) AudioVisualType type,
                       @JsonProperty(PUBLISHER) PublishingHouse publisher,
                       @JsonProperty(DATE) Time date,
                       @JsonProperty(ISBNS) List<String> isbns,
                       @JsonProperty(EXTENT) String extent) throws InvalidIsbnException {
        this.type = type;
        this.publisher = publisher;
        this.date = date;
        this.isbns = nonNull(isbns) ? validateIsbn(isbns) : emptyList();
        this.extent = extent;
    }

    public String getType() {
        return type.getType();
    }

    public PublishingHouse getPublisher() {
        return publisher;
    }

    public Time getDate() {
        return date;
    }

    public String getExtent() {
        return extent;
    }

    @Override
    public List<String> getIsbns() {
        return isbns;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AudioVisual)) {
            return false;
        }
        AudioVisual that = (AudioVisual) o;
        return Objects.equals(getType(), that.getType())
            && Objects.equals(getPublisher(), that.getPublisher())
            && Objects.equals(getDate(), that.getDate())
            && Objects.equals(getIsbns(), that.getIsbns())
            && Objects.equals(getExtent(), that.getExtent());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getType(), getPublisher(), getDate(), getIsbns(), getExtent());
    }
}

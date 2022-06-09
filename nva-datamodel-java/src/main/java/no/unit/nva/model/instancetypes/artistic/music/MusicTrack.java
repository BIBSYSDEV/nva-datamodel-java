package no.unit.nva.model.instancetypes.artistic.music;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class MusicTrack {

    public static final String EXTENT = "extent";
    public static final String COMPOSER = "composer";
    public static final String TITLE = "title";

    @JsonProperty(TITLE)
    private final String title;
    @JsonProperty(COMPOSER)
    private final String composer;
    @JsonProperty(EXTENT)
    private final String extent;

    @JsonCreator
    public MusicTrack(@JsonProperty(TITLE) String title,
                      @JsonProperty(COMPOSER) String composer,
                      @JsonProperty(EXTENT) String extent) {
        this.title = title;
        this.composer = composer;
        this.extent = extent;
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getTitle(), getComposer(), getExtent());
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MusicTrack)) {
            return false;
        }
        MusicTrack that = (MusicTrack) o;
        return Objects.equals(getTitle(), that.getTitle())
               && Objects.equals(getComposer(), that.getComposer())
               && Objects.equals(getExtent(), that.getExtent());
    }

    public String getTitle() {
        return title;
    }

    public String getComposer() {
        return composer;
    }

    public String getExtent() {
        return extent;
    }
}

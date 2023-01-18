package no.unit.nva.model.instancetypes.artistic.music;

import static no.unit.nva.model.util.SerializationUtils.nullListAsEmpty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
import java.util.Objects;
import no.unit.nva.model.contexttypes.PublishingHouse;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class AudioVisualPublication implements MusicPerformanceManifestation {

    public static final String MEDIA_TYPE = "mediaType";
    public static final String PUBLISHER = "publisher";
    public static final String CATALOGUE_NUMBER = "catalogueNumber";
    public static final String TRACK_LIST = "trackList";

    @JsonProperty(MEDIA_TYPE)
    private final MusicMediaType mediaType;
    @JsonProperty(PUBLISHER)
    private final PublishingHouse publisher;
    @JsonProperty(CATALOGUE_NUMBER)
    private final String catalogueNumber;
    @JsonProperty(TRACK_LIST)
    private final List<MusicTrack> trackList;

    @JsonCreator
    public AudioVisualPublication(@JsonProperty(MEDIA_TYPE) MusicMediaType mediaType,
                                  @JsonProperty(PUBLISHER) PublishingHouse publisher,
                                  @JsonProperty(CATALOGUE_NUMBER) String catalogueNumber,
                                  @JsonProperty(TRACK_LIST) List<MusicTrack> trackList) {

        this.mediaType = mediaType;
        this.publisher = publisher;
        this.catalogueNumber = catalogueNumber;
        this.trackList = nullListAsEmpty(trackList);
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getMediaType(), getPublisher(), getCatalogueNumber(), getTrackList());
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AudioVisualPublication)) {
            return false;
        }
        AudioVisualPublication that = (AudioVisualPublication) o;
        return getMediaType() == that.getMediaType()
               && Objects.equals(getPublisher(), that.getPublisher())
               && Objects.equals(getCatalogueNumber(), that.getCatalogueNumber())
               && Objects.equals(getTrackList(), that.getTrackList());
    }

    public MusicMediaType getMediaType() {
        return mediaType;
    }

    public PublishingHouse getPublisher() {
        return publisher;
    }

    public String getCatalogueNumber() {
        return catalogueNumber;
    }

    public List<MusicTrack> getTrackList() {
        return trackList;
    }
}

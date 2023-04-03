package no.unit.nva.model.instancetypes.artistic.music;

import static no.unit.nva.model.util.SerializationUtils.nullListAsEmpty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
import java.util.Objects;
import no.unit.nva.model.contexttypes.PublishingHouse;
import no.unit.nva.model.contexttypes.UnconfirmedPublisher;
import no.unit.nva.model.instancetypes.artistic.UnconfirmedPublisherMigrator;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class AudioVisualPublication implements MusicPerformanceManifestation, UnconfirmedPublisherMigrator {

    public static final String MEDIA_TYPE_FIELD = "mediaType";
    public static final String PUBLISHER_FIELD = "publisher";
    public static final String CATALOGUE_NUMBER_FIELD = "catalogueNumber";
    public static final String TRACK_LIST_FIELD = "trackList";
    public static final String ISRC_FIELD = "isrc";

    @JsonProperty(MEDIA_TYPE_FIELD)
    private final MusicMediaType mediaType;
    @JsonProperty(PUBLISHER_FIELD)
    private final PublishingHouse publisher;
    @JsonProperty(CATALOGUE_NUMBER_FIELD)
    private final String catalogueNumber;
    @JsonProperty(TRACK_LIST_FIELD)
    private final List<MusicTrack> trackList;
    @JsonProperty(ISRC_FIELD)
    private final Isrc isrc;

    public AudioVisualPublication(@JsonProperty(MEDIA_TYPE_FIELD) MusicMediaType mediaType,
                                  @JsonProperty(PUBLISHER_FIELD) PublishingHouse publisher,
                                  @JsonProperty(CATALOGUE_NUMBER_FIELD) String catalogueNumber,
                                  @JsonProperty(TRACK_LIST_FIELD) List<MusicTrack> trackList,
                                  @JsonProperty(ISRC_FIELD) Isrc isrc) {

        this.mediaType = mediaType;
        this.publisher = publisher;
        this.catalogueNumber = catalogueNumber;
        this.trackList = nullListAsEmpty(trackList);
        this.isrc = isrc;
    }

    @Deprecated
    @JsonCreator
    public static AudioVisualPublication fromJson(@JsonProperty(MEDIA_TYPE_FIELD) MusicMediaType mediaType,
                                                  @JsonProperty(PUBLISHER_FIELD) Object publisher,
                                                  @JsonProperty(CATALOGUE_NUMBER_FIELD) String catalogueNumber,
                                                  @JsonProperty(TRACK_LIST_FIELD) List<MusicTrack> trackList,
                                                  @JsonProperty(ISRC_FIELD) Isrc isrc) {
        var publishingHouse = publisher instanceof String
                                  ? new UnconfirmedPublisher((String) publisher)
                                  : UnconfirmedPublisherMigrator.toPublisher(publisher);
        return new AudioVisualPublication(mediaType, publishingHouse, catalogueNumber, trackList, isrc);
    }

    public Isrc getIsrc() {
        return isrc;
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

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getMediaType(), getPublisher(), getCatalogueNumber(), getTrackList(), getIsrc());
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AudioVisualPublication that = (AudioVisualPublication) o;
        return getMediaType() == that.getMediaType()
               && Objects.equals(getPublisher(), that.getPublisher())
               && Objects.equals(getCatalogueNumber(), that.getCatalogueNumber())
               && Objects.equals(getTrackList(), that.getTrackList())
               && Objects.equals(getIsrc(), that.getIsrc());
    }
}

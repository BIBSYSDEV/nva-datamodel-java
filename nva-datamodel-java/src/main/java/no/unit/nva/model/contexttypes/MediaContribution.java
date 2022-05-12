package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;
import no.unit.nva.model.contexttypes.media.MediaFormat;
import no.unit.nva.model.contexttypes.media.MediaSubType;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class MediaContribution implements PublicationContext {

    public static final String MEDIUM = "medium";
    public static final String FORMAT = "format";

    @JsonProperty(MEDIUM)
    private final MediaSubType medium;

    @JsonProperty(FORMAT)
    private final MediaFormat format;

    public MediaContribution(@JsonProperty(MEDIUM) MediaSubType medium, @JsonProperty(FORMAT) MediaFormat format) {
        this.medium = medium;
        this.format = format;
    }

    public MediaSubType getMedium() {
        return medium;
    }

    public MediaFormat getFormat() {
        return format;
    }

    public static final class Builder {

        private MediaSubType medium;
        private MediaFormat format;

        public Builder withMedium(MediaSubType medium) {
            this.medium = medium;
            return this;
        }

        public Builder withFormat(MediaFormat format) {
            this.format = format;
            return this;
        }

        public MediaContribution build() {
            return new MediaContribution(medium, format);
        }
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getMedium(), getFormat());
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MediaContribution)) {
            return false;
        }
        MediaContribution that = (MediaContribution) o;
        return Objects.equals(getMedium(), that.getMedium())
            && Objects.equals(getFormat(), that.getFormat());
    }
}

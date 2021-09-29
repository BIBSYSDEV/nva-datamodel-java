package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidSeriesException;
import nva.commons.core.JacocoGenerated;

import java.net.URI;
import java.util.Objects;
import java.util.regex.Pattern;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Series implements BookSeries {
    public static final Pattern EXPECTED_SERIES_URI_PATTERN =
            Pattern.compile("https://.*?nva\\.aws\\.unit\\.no/publication-channels/.*");

    private final URI id;

    @JsonCreator
    public Series(@JsonProperty("id") URI id) {
        validateSeriesUri(id);
        this.id = id;
    }

    public URI getId() {
        return id;
    }

    private void validateSeriesUri(URI seriesUri) {
        if (!EXPECTED_SERIES_URI_PATTERN.matcher(seriesUri.toString()).matches()) {
            throw new InvalidSeriesException(seriesUri.toString());
        }
    }

    @Override
    public boolean isConfirmed() {
        return true;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Series)) {
            return false;
        }
        Series series = (Series) o;
        return Objects.equals(getId(), series.getId());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

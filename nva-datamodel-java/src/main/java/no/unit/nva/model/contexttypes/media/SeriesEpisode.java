package no.unit.nva.model.contexttypes.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class SeriesEpisode {
    private static final String SERIES = "series";
    private static final String SERIES_PART = "seriesPart";
    @JsonProperty(SERIES)
    private final String series;
    @JsonProperty(SERIES_PART)
    private final String seriesPart;

    public SeriesEpisode(@JsonProperty(SERIES) String series,
                         @JsonProperty(SERIES_PART) String seriesPart) {
        this.series = series;
        this.seriesPart = seriesPart;
    }

    public String getSeries() {
        return series;
    }

    public String getSeriesPart() {
        return seriesPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SeriesEpisode)) {
            return false;
        }
        SeriesEpisode that = (SeriesEpisode) o;
        return Objects.equals(getSeries(), that.getSeries())
                && Objects.equals(getSeriesPart(), that.getSeriesPart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeries(), getSeriesPart());
    }
}

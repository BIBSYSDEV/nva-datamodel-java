package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nva.commons.utils.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class SeriesImpl implements Series {

    private final String title;
    private final String number;

    public SeriesImpl(@JsonProperty("title") String title, @JsonProperty("number") String number) {
        this.title = title;
        this.number = number;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SeriesImpl)) {
            return false;
        }
        SeriesImpl series = (SeriesImpl) o;
        return Objects.equals(title, series.title)
                && Objects.equals(number, series.number);
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(title, number);
    }
}

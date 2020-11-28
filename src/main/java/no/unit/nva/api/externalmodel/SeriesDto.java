package no.unit.nva.api.externalmodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SeriesDto {
    private final String title;

    public final String number;

    @JsonCreator
    public SeriesDto(@JsonProperty("title") String title, @JsonProperty("number") String number) {
        this.title = title;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public String getNumber() {
        return number;
    }
}

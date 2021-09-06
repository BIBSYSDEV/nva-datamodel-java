package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class UnconfirmedSeries implements BookSeries {
    private final String title;

    @JsonCreator
    public UnconfirmedSeries(@JsonProperty("title") String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean isConfirmed() {
        return false;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnconfirmedSeries)) {
            return false;
        }
        UnconfirmedSeries that = (UnconfirmedSeries) o;
        return Objects.equals(getTitle(), that.getTitle());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }
}

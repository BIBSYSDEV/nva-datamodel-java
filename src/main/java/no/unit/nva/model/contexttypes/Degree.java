package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import no.unit.nva.model.exceptions.InvalidIsbnException;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Degree extends Book {

    @JsonCreator
    public Degree(@JsonProperty("series") BookSeries series,
                  @JsonProperty("seriesTitle") String seriesTitle,
                  @JsonProperty("seriesNumber") String seriesNumber,
                  @JsonProperty("publisher") PublishingHouse publisher,
                  @JsonProperty("isbnList") List<String> isbnList)
            throws InvalidIsbnException {
        super(series, seriesTitle, seriesNumber, publisher, isbnList);
    }

    private Degree(Builder builder) throws InvalidIsbnException {
        super(builder.series, null, builder.seriesNumber, builder.publisher, builder.isbnList);
    }

    public static final class Builder {
        private BookSeries series;
        private String seriesNumber;
        private PublishingHouse publisher;
        private List<String> isbnList;

        public Builder() {
        }

        public Builder withSeries(BookSeries series) {
            this.series = series;
            return this;
        }

        public Builder withSeriesNumber(String seriesNumber) {
            this.seriesNumber = seriesNumber;
            return this;
        }

        public Builder withPublisher(PublishingHouse publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder withIsbnList(List<String> isbnList) {
            this.isbnList = isbnList;
            return this;
        }

        public Degree build() throws InvalidIsbnException {
            return new Degree(this);
        }
    }
}

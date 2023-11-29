package no.unit.nva.model.contexttypes;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidUnconfirmedSeriesException;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Degree extends Book {

    public static final String JSON_PROPERTY_COURSE_CODE = "courseCode";
    private final String courseCode;

    @JsonCreator
    public Degree(@JsonProperty(JSON_PROPERTY_SERIES) BookSeries series,
                  @JsonProperty(value = JSON_PROPERTY_SERIES_TITLE, access = WRITE_ONLY) String unconfirmedSeriesTitle,
                  @JsonProperty(JSON_PROPERTY_SERIES_NUMBER) String seriesNumber,
                  @JsonProperty(JSON_PROPERTY_PUBLISHER) PublishingHouse publisher,
                  @JsonProperty(JSON_PROPERTY_ISBN_LIST) List<String> isbnList,
                  @JsonProperty(JSON_PROPERTY_COURSE_CODE) String courseCode) throws InvalidUnconfirmedSeriesException {
        super(series, unconfirmedSeriesTitle, seriesNumber, publisher, isbnList);
        this.courseCode = courseCode;
    }

    private Degree(Builder builder, String courseCode) throws InvalidUnconfirmedSeriesException {
        super(builder.series, null, builder.seriesNumber, builder.publisher, builder.isbnList);
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public static final class Builder {

        private BookSeries series;
        private String seriesNumber;
        private PublishingHouse publisher;
        private List<String> isbnList;
        private String courseCode;

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

        public Builder withCourseCode(String courseCode) {
            this.courseCode = courseCode;
            return this;
        }

        public Degree build() throws InvalidIsbnException, InvalidUnconfirmedSeriesException {
            return new Degree(this, courseCode);
        }
    }
}

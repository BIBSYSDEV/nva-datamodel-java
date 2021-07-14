package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URI;
import java.net.URL;
import no.unit.nva.model.exceptions.InvalidIsbnException;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Degree extends Book {

    public Degree() {
        super();
    }

    private Degree(Builder builder) throws InvalidIsbnException {
        super();
        setSeriesTitle(builder.seriesTitle);
        setSeriesNumber(builder.seriesNumber);
        setPublisher(builder.publisher);
        setIsbnList(builder.isbnList);
        setUrl(builder.url);
        setLinkedContext(builder.linkedContext);
    }

    public static final class Builder {
        private String seriesTitle;
        private String seriesNumber;
        private String publisher;
        private List<String> isbnList;
        private URL url;
        private URI linkedContext;


        public Builder() {
        }

        public Builder withSeriesTitle(String seriesTitle) {
            this.seriesTitle = seriesTitle;
            return this;
        }

        public Builder withSeriesNumber(String seriesNumber) {
            this.seriesNumber = seriesNumber;
            return this;
        }

        public Builder withPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder withIsbnList(List<String> isbnList) {
            this.isbnList = isbnList;
            return this;
        }

        public Builder withUrl(URL url) {
            this.url = url;
            return this;
        }

        public Builder withLinkedContext(URI linkedContext) {
            this.linkedContext = linkedContext;
            return this;
        }

        public Degree build() throws InvalidIsbnException {
            return new Degree(this);
        }
    }
}

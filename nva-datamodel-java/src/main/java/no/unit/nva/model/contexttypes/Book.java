package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidUnconfirmedSeriesException;
import nva.commons.core.JacocoGenerated;
import org.apache.commons.validator.routines.ISBNValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Book implements BasicContext {

    public static final ISBNValidator ISBN_VALIDATOR = new ISBNValidator();
    public static final String JSON_PROPERTY_SERIES = "series";
    public static final String JSON_PROPERTY_SERIES_TITLE = "seriesTitle";
    public static final String JSON_PROPERTY_SERIES_NUMBER = "seriesNumber";
    public static final String JSON_PROPERTY_PUBLISHER = "publisher";
    public static final String JSON_PROPERTY_ISBN_LIST = "isbnList";
    public static final String SPACES_AND_HYPHENS_REGEX = "[ -]";

    @JsonProperty(JSON_PROPERTY_SERIES)
    private final BookSeries series;
    @JsonProperty(JSON_PROPERTY_SERIES_NUMBER)
    private final String seriesNumber;
    @JsonProperty(JSON_PROPERTY_PUBLISHER)
    private final PublishingHouse publisher;
    @JsonProperty(JSON_PROPERTY_ISBN_LIST)
    private final List<String> isbnList;

    public Book(@JsonProperty(JSON_PROPERTY_SERIES) BookSeries series,
                @JsonProperty(value = JSON_PROPERTY_SERIES_TITLE, access = WRITE_ONLY) String unconfirmedSeriesTitle,
                @JsonProperty(JSON_PROPERTY_SERIES_NUMBER) String seriesNumber,
                @JsonProperty(JSON_PROPERTY_PUBLISHER) PublishingHouse publisher,
                @JsonProperty(JSON_PROPERTY_ISBN_LIST) List<String> isbnList) throws InvalidIsbnException,
            InvalidUnconfirmedSeriesException {
        this(BookSeries.extractSeriesInformation(series, unconfirmedSeriesTitle),
                seriesNumber,
                publisher,
                isbnList);
    }

    public Book(BookSeries series, String seriesNumber, PublishingHouse publisher, List<String> isbnList)
            throws InvalidIsbnException {
        this.series = series;
        this.seriesNumber = seriesNumber;
        this.publisher = publisher;
        this.isbnList = extractValidIsbnList(isbnList);
    }

    public BookSeries getSeries() {
        return series;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public PublishingHouse getPublisher() {
        return publisher;
    }

    public List<String> getIsbnList() {
        return nonNull(isbnList) ? isbnList : Collections.emptyList();
    }

    /**
     * Returns the ISBN list to the object after checking that the ISBNs are valid and removing ISBN-punctuation.
     *
     * @param isbnList List of ISBN candidates.
     * @return List of valid ISBN strings.
     * @throws InvalidIsbnException If one of the ISBNs is found to be invalid.
     */
    private List<String> extractValidIsbnList(List<String> isbnList) throws InvalidIsbnException {
        if (isNull(isbnList) || isbnList.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> validIsbns = isbnList.stream()
            .map(isbn -> isbn.replaceAll(SPACES_AND_HYPHENS_REGEX, ""))
            .map(ISBN_VALIDATOR::validate)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        if (isbnList.size() != validIsbns.size()) {
            List<String> errors = new ArrayList<>(isbnList);
            errors.removeAll(validIsbns);
            throw new InvalidIsbnException(errors);
        }
        return validIsbns;
    }


    public BookBuilder copy() throws InvalidIsbnException {
        return new BookBuilder()
            .withSeriesNumber(getSeriesNumber())
            .withSeries(getSeries())
            .withPublisher(getPublisher())
            .withIsbnList(getIsbnList());
    }

    public static final class BookBuilder {
        private BookSeries series;
        private String seriesNumber;
        private PublishingHouse publisher;
        private List<String> isbnList;

        public BookBuilder() {
        }

        public BookBuilder withSeries(BookSeries series) {
            this.series = series;
            return this;
        }

        public BookBuilder withSeriesNumber(String seriesNumber) {
            this.seriesNumber = seriesNumber;
            return this;
        }

        public BookBuilder withPublisher(PublishingHouse publisher) {
            this.publisher = publisher;
            return this;
        }

        public BookBuilder withIsbnList(List<String> isbnList) {
            this.isbnList = isbnList;
            return this;
        }

        public Book build() throws InvalidIsbnException {
            return new Book(series, seriesNumber, publisher, isbnList);
        }
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(getSeries(), book.getSeries())
                && Objects.equals(getSeriesNumber(), book.getSeriesNumber())
                && Objects.equals(getPublisher(), book.getPublisher())
                && Objects.equals(getIsbnList(), book.getIsbnList());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getSeries(), getSeriesNumber(), getPublisher(), getIsbnList());
    }
}

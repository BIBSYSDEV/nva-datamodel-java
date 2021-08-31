package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidSeriesException;
import nva.commons.core.JacocoGenerated;
import org.apache.commons.validator.routines.ISBNValidator;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Book implements BasicContext {

    public static final ISBNValidator ISBN_VALIDATOR = new ISBNValidator();
    public static final Pattern EXPECTED_SERIES_URI_PATTERN =
        Pattern.compile("https://.*?nva\\.aws\\.unit\\.no/publication-channels/.*");
    @Deprecated
    private String seriesTitle;
    private URI seriesUri;
    private String seriesNumber;
    private String publisher;
    private List<String> isbnList;

    public Book() {
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getSeriesTitle(), getSeriesUri(), getSeriesNumber(), getPublisher(), getIsbnList());
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
        return Objects.equals(getSeriesTitle(), book.getSeriesTitle())
               && Objects.equals(getSeriesUri(), book.getSeriesUri())
               && Objects.equals(getSeriesNumber(), book.getSeriesNumber())
               && Objects.equals(getPublisher(), book.getPublisher())
               && Objects.equals(getIsbnList(), book.getIsbnList());
    }

    public URI getSeriesUri() {
        return seriesUri;
    }

    public void setSeriesUri(URI seriesUri) {
        validateSeriesUri(seriesUri);
        this.seriesUri = seriesUri;
    }

    private void validateSeriesUri(URI seriesUri) {
        if (!EXPECTED_SERIES_URI_PATTERN.matcher(seriesUri.toString()).matches()) {
            throw new InvalidSeriesException(seriesUri.toString());
        }
    }

    public List<String> getIsbnList() {
        return nonNull(isbnList) ? isbnList : Collections.emptyList();
    }

    /**
     * Adds the ISBN list to the object after checking that the ISBNs are valid and removing ISBN-punctuation.
     *
     * @param isbnList List of ISBN candidates.
     * @throws InvalidIsbnException If one of the ISBNs is found to be invalid
     */
    public void setIsbnList(List<String> isbnList) throws InvalidIsbnException {
        if (isNull(isbnList) || isbnList.isEmpty()) {
            this.isbnList = Collections.emptyList();
            return;
        }
        List<String> validIsbns = isbnList.stream()
            .map(ISBN_VALIDATOR::validate)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        if (isbnList.size() != validIsbns.size()) {
            List<String> errors = new ArrayList<>(isbnList);
            errors.removeAll(validIsbns);
            throw new InvalidIsbnException(errors);
        }
        this.isbnList = validIsbns;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Builder copy() throws InvalidIsbnException {
        return new Builder()
            .withSeriesNumber(getSeriesNumber())
            .withSeriesTitle(getSeriesTitle())
            .withPublisher(getPublisher())
            .withIsbnList(getIsbnList())
            .withSeriesUri(getSeriesUri());
    }

    public static final class Builder {

        private final Book book;

        public Builder() {
            book = new Book();
        }

        public Builder withSeriesTitle(String seriesTitle) {
            book.setSeriesTitle(seriesTitle);
            return this;
        }

        public Builder withSeriesUri(URI seriesUri) {
            book.setSeriesUri(seriesUri);
            return this;
        }

        public Builder withSeriesNumber(String seriesNumber) {
            book.setSeriesNumber(seriesNumber);
            return this;
        }

        public Builder withPublisher(String publisher) {
            book.setPublisher(publisher);
            return this;
        }

        public Builder withIsbnList(List<String> isbnList) throws InvalidIsbnException {
            book.setIsbnList(isbnList);
            return this;
        }

        public Book build() {
            return book;
        }
    }
}

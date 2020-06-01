package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.Level;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import nva.commons.utils.JacocoGenerated;
import org.apache.commons.validator.routines.ISBNValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Book implements BasicContext {

    private String seriesTitle;
    private String seriesNumber;
    private String publisher;
    private Level level;
    private boolean openAccess;
    private boolean peerReviewed;
    private List<String> isbnList;
    public static final ISBNValidator ISBN_VALIDATOR = new ISBNValidator();

    public Book() {
    }

    private Book(Builder builder) throws InvalidIsbnException {
        setSeriesTitle(builder.seriesTitle);
        setSeriesNumber(builder.seriesNumber);
        setPublisher(builder.publisher);
        setLevel(builder.level);
        setOpenAccess(builder.openAccess);
        setPeerReviewed(builder.peerReviewed);
        setIsbnList(builder.isbnList);
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public boolean isOpenAccess() {
        return openAccess;
    }

    @Override
    public void setOpenAccess(boolean openAccess) {
        this.openAccess = openAccess;
    }

    @Override
    public boolean isPeerReviewed() {
        return peerReviewed;
    }

    @Override
    public void setPeerReviewed(boolean peerReviewed) {
        this.peerReviewed = peerReviewed;
    }

    public List<String> getIsbnList() {
        return isbnList;
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
        return isOpenAccess() == book.isOpenAccess()
                && isPeerReviewed() == book.isPeerReviewed()
                && Objects.equals(getSeriesTitle(), book.getSeriesTitle())
                && Objects.equals(getSeriesNumber(), book.getSeriesNumber())
                && Objects.equals(getPublisher(), book.getPublisher())
                && getLevel() == book.getLevel()
                && Objects.equals(getIsbnList(), book.getIsbnList());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(
                getSeriesTitle(),
                getSeriesNumber(),
                getPublisher(),
                getLevel(),
                isOpenAccess(),
                isPeerReviewed(),
                getIsbnList()
        );
    }

    public static final class Builder {
        private String seriesTitle;
        private String seriesNumber;
        private String publisher;
        private Level level;
        private boolean openAccess;
        private boolean peerReviewed;
        private List<String> isbnList;

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

        public Builder withLevel(Level level) {
            this.level = level;
            return this;
        }

        public Builder withOpenAccess(boolean openAccess) {
            this.openAccess = openAccess;
            return this;
        }

        public Builder withPeerReviewed(boolean peerReviewed) {
            this.peerReviewed = peerReviewed;
            return this;
        }

        public Builder withIsbnList(List<String> isbnList) {
            this.isbnList = isbnList;
            return this;
        }

        public Book build() throws InvalidIsbnException {
            return new Book(this);
        }
    }
}

package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.Level;

import java.util.List;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Book implements PublicationContext {

    private String seriesTitle;
    private String seriesNumber;
    private Level level;
    private boolean openAccess;
    private boolean peerReviewed;
    private List<String> isbnList;

    public Book() {
    }

    private Book(Builder builder) {
        setSeriesTitle(builder.seriesTitle);
        setSeriesNumber(builder.seriesNumber);
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

    public void setIsbnList(List<String> isbnList) {
        this.isbnList = isbnList;
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
                && getLevel() == book.getLevel()
                && Objects.equals(getIsbnList(), book.getIsbnList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeriesTitle(), getSeriesNumber(), getLevel(), isOpenAccess(), isPeerReviewed(),
                getIsbnList());
    }


    public static final class Builder {
        private String seriesTitle;
        private String seriesNumber;
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

        public Book build() {
            return new Book(this);
        }
    }
}

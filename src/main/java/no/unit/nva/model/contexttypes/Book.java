package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.Level;
import no.unit.nva.model.NullSeries;
import no.unit.nva.model.Series;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Book implements PublicationContext {

    private String title;
    private Series series;
    private Level level;
    private boolean openAccess;
    private boolean peerReviewed;
    private List<String> isbnList;

    public Book() {
    }

    private Book(Builder builder) {
        setTitle(builder.title);
        setSeries(builder.series);
        setLevel(builder.level);
        setOpenAccess(builder.openAccess);
        setPeerReviewed(builder.peerReviewed);
        setIsbnList(builder.isbnList);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
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

    public Series getSeries() {
        return series;
    }

    @JsonSetter
    public void setSeries(Series series) {
        this.series = nonNull(series) && nonNull(series.getTitle()) ? series : new NullSeries();
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
                && Objects.equals(getTitle(), book.getTitle())
                && Objects.equals(getSeries(), book.getSeries())
                && getLevel() == book.getLevel()
                && Objects.equals(getIsbnList(), book.getIsbnList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getSeries(), getLevel(), isOpenAccess(), isPeerReviewed(), getIsbnList());
    }

    public static final class Builder {
        private String title;
        private Series series;
        private Level level;
        private boolean openAccess;
        private boolean peerReviewed;
        private List<String> isbnList;

        public Builder() {
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withSeries(Series series) {
            this.series = series;
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

package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.Level;
import no.unit.nva.model.NullSeries;
import no.unit.nva.model.Series;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Book {

    private String title;
    private Series series;
    private Level level;
    private boolean openAccess;
    private boolean peerReviewed;
    @JsonProperty("isbn")
    private List<String> isbns;

    public Book() {

    }

    private Book(Builder builder) {
        setTitle(builder.title);
        setSeries(builder.series);
        setLevel(builder.level);
        setOpenAccess(builder.openAccess);
        setPeerReviewed(builder.peerReviewed);
        setIsbns(builder.isbns);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean isOpenAccess() {
        return openAccess;
    }

    public void setOpenAccess(boolean openAccess) {
        this.openAccess = openAccess;
    }

    public boolean isPeerReviewed() {
        return peerReviewed;
    }

    public void setPeerReviewed(boolean peerReviewed) {
        this.peerReviewed = peerReviewed;
    }

    public List<String> getIsbns() {
        return isbns;
    }

    public void setIsbns(List<String> isbns) {
        this.isbns = isbns;
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
                && Objects.equals(getIsbns(), book.getIsbns());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getSeries(), getLevel(), isOpenAccess(), isPeerReviewed(), getIsbns());
    }

    public static final class Builder {
        private String title;
        private Series series;
        private Level level;
        private boolean openAccess;
        private boolean peerReviewed;
        private List<String> isbns;

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

        public Builder withIsbns(List<String> isbns) {
            this.isbns = isbns;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}

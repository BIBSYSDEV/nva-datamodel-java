package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JacocoGenerated;

import java.util.Objects;

import static java.util.Objects.nonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ShortCommunication implements PublicationInstance {
    private String volume;
    private String issue;
    private String articleNumber;
    private Pages pages;
    private boolean peerReviewed = false;

    @JacocoGenerated
    public ShortCommunication() {
        super();
    }

    private ShortCommunication(Builder builder) throws InvalidPageTypeException {
        super();
        setVolume(builder.volume);
        setIssue(builder.issue);
        setArticleNumber(builder.articleNumber);
        setPages(builder.pages);
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    @Override
    public Pages getPages() {
        return pages;
    }

    @Override
    public void setPages(Pages pages) throws InvalidPageTypeException {
        if (nonNull(pages) && !(pages instanceof Range)) {
            throw new InvalidPageTypeException(ShortCommunication.class, Range.class, pages.getClass());
        }
        this.pages = pages;
    }

    @Override
    public boolean isPeerReviewed() {
        return peerReviewed;
    }

    public static final class Builder {
        private String volume;
        private String issue;
        private String articleNumber;
        private Range pages;

        public Builder() {
        }

        public Builder withVolume(String volume) {
            this.volume = volume;
            return this;
        }

        public Builder withIssue(String issue) {
            this.issue = issue;
            return this;
        }

        public Builder withArticleNumber(String articleNumber) {
            this.articleNumber = articleNumber;
            return this;
        }

        public Builder withPages(Range pages) {
            this.pages = pages;
            return this;
        }

        public ShortCommunication build() throws InvalidPageTypeException {
            return new ShortCommunication(this);
        }
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShortCommunication)) {
            return false;
        }
        ShortCommunication that = (ShortCommunication) o;
        return Objects.equals(getVolume(), that.getVolume())
                && Objects.equals(getIssue(), that.getIssue())
                && Objects.equals(getArticleNumber(), that.getArticleNumber())
                && Objects.equals(getPages(), that.getPages());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getVolume(), getIssue(), getArticleNumber(), getPages());
    }
}

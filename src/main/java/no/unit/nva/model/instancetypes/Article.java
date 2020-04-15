package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.Range;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Article extends PublicationInstance {
    private String volume;
    private String issue;
    private String articleNumber;

    public Article() {
        super();
    }

    private Article(Builder builder) {
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

        public Article build() {
            return new Article(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Article)) {
            return false;
        }
        Article article = (Article) o;
        return Objects.equals(getVolume(), article.getVolume())
                && Objects.equals(getIssue(), article.getIssue())
                && Objects.equals(getArticleNumber(), article.getArticleNumber())
                && Objects.equals(getPages(), article.getPages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVolume(), getIssue(), getArticleNumber(), getPages());
    }
}

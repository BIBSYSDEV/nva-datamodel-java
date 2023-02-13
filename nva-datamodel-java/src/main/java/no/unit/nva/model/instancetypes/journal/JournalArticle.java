package no.unit.nva.model.instancetypes.journal;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.Range;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class JournalArticle implements PublicationInstance<Range> {
    public static final String PAGES_FIELD = "pages";
    public static final String TYPE = "type";
    public static final String VOLUME_FIELD = "volume";
    public static final String ISSUE_FIELD = "issue";
    public static final String ARTICLE_NUMBER_FIELD = "articleNumber";
    private final Range pages;
    private final String volume;
    private final String issue;
    private final String articleNumber;

    protected JournalArticle(Range pages,
                             String volume,
                             String issue,
                             String articleNumber) {
        this.pages = pages;
        this.volume = volume;
        this.issue = issue;
        this.articleNumber = articleNumber;
    }

    public String getVolume() {
        return volume;
    }

    public String getIssue() {
        return issue;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    @Override
    public Range getPages() {
        return pages;
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JournalArticle)) {
            return false;
        }
        JournalArticle that = (JournalArticle) o;
        return Objects.equals(getPages(), that.getPages())
                && Objects.equals(getVolume(), that.getVolume())
                && Objects.equals(getIssue(), that.getIssue())
                && Objects.equals(getArticleNumber(), that.getArticleNumber());
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getPages(), getVolume(), getIssue(), getArticleNumber());
    }
}

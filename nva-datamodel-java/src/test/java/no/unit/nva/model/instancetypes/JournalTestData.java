package no.unit.nva.model.instancetypes;

import no.unit.nva.model.instancetypes.journal.JournalArticleContentType;
import no.unit.nva.model.pages.Range;

public class JournalTestData extends TestData {
    private final String volume;
    private final String issue;
    private final String articleNumber;
    private final Range pages;
    private JournalArticleContentType content;
    private boolean originalResearch;

    /**
     * Generates a random test data set for a journal article.
     *
     */
    public JournalTestData() {
        volume = random(1, 255);
        issue = random(1, 12);
        articleNumber = random(10000, 50000);
        pages = generateRange();
        content = randomContent();
    }

    public JournalTestData(boolean peerReviewed) {
        this();
        setPeerReviewed(peerReviewed);
    }

    public JournalTestData(String content) {
        this();
        setContent(JournalArticleContentType.lookup(content));
    }

    public JournalTestData(JournalArticleContentType content) {
        this();
        setContent(content);
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

    public Range getPages() {
        return pages;
    }

    public JournalArticleContentType getContent() {
        return content;
    }

    private void setContent(JournalArticleContentType content) {
        this.content = content;
    }

    private JournalArticleContentType randomContent() {
        return JournalArticleContentType.values()[RANDOM.nextInt(JournalArticleContentType.values().length)];
    }

    public void setOriginalResearch(boolean originalResearch) {
        this.originalResearch = originalResearch;
    }

    public boolean isOriginalResearch() {
        return originalResearch;
    }
}

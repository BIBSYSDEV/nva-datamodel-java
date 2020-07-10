package no.unit.nva.model.instancetypes;

import no.unit.nva.model.pages.Range;

public class JournalTestData extends TestData {
    private final String volume;
    private final String issue;
    private final String articleNumber;
    private final Range pages;

    /**
     * Generates a random test data set for a journal article.
     *
     */
    public JournalTestData() {
        volume = random(1, 255);
        issue = random(1, 12);
        articleNumber = random(10000, 50000);
        pages = generateRange();
    }

    public JournalTestData(boolean peerReviewed) {
        this();
        setPeerReviewed(peerReviewed);
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

}

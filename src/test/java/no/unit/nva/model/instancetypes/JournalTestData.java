package no.unit.nva.model.instancetypes;

import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.pages.Range;

import java.util.Random;

public class JournalTestData {
    private final String volume;
    private final String issue;
    private final String articleNumber;
    private final String begin;
    private final String end;
    private final Range pages;
    private final boolean illustrated;
    private boolean peerReviewed;

    private static final Random RANDOM = new Random();

    /**
     * Generates a random test data set for a journal article.
     *
     * @throws InvalidPageRangeException thrown if the article page range is not properly structured.
     */
    public JournalTestData() throws InvalidPageRangeException {
        volume = random(1, 255);
        issue = random(1, 12);
        articleNumber = random(10000, 50000);
        begin = random(1, 12);
        end = random(12, 55);
        pages = new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();
        illustrated = RANDOM.nextBoolean();
        peerReviewed = RANDOM.nextBoolean();
    }

    public JournalTestData(boolean peerReviewed) throws InvalidPageRangeException {
        this();
        this.peerReviewed = peerReviewed;
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

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

    public Range getPages() {
        return pages;
    }

    public boolean isIllustrated() {
        return illustrated;
    }

    public boolean isPeerReviewed() {
        return peerReviewed;
    }

    private String random(int lower, int upper) {
        RANDOM.ints(lower, upper);
        return Integer.toString(RANDOM.nextInt());
    }
}

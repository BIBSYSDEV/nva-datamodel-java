package no.unit.nva.model.instancetypes;

import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.pages.Range;

import java.util.Random;

public class TestData {
    protected static final Random RANDOM = new Random();

    private final String begin;
    private final String end;
    private boolean peerReviewed;

    /**
     * Creates a random set of data for the Range that is present in each Pages subtype.
     */
    public TestData() {
        begin = random(1, 12);
        end = random(12, 55);
        peerReviewed = RANDOM.nextBoolean();
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

    public boolean isPeerReviewed() {
        return peerReviewed;
    }

    public void setPeerReviewed(boolean peerReviewed) {
        this.peerReviewed = peerReviewed;
    }

    protected Range generateRange() throws InvalidPageRangeException {
        return new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();
    }

    protected String random(int lower, int upper) {
        RANDOM.ints(lower, upper);
        return Integer.toString(RANDOM.nextInt());
    }
}

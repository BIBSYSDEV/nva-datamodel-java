package no.unit.nva.model.instancetypes;

import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.pages.MonographPages;

public class MonographTestData extends TestData {
    private final MonographPages pages;

    public MonographTestData() throws InvalidPageRangeException {
        super();
        this.pages = generateMonographPages();
    }

    public MonographTestData(boolean peerReviewed) throws InvalidPageRangeException {
        this();
        setPeerReviewed(peerReviewed);
    }

    public MonographPages getPages() {
        return pages;
    }

    private MonographPages generateMonographPages() throws InvalidPageRangeException {
        return new MonographPages.Builder()
            .withIntroduction(generateRange())
            .withPages(String.valueOf(RANDOM.ints(100, 500)))
            .withIllustrated(RANDOM.nextBoolean())
            .build();
    }
}

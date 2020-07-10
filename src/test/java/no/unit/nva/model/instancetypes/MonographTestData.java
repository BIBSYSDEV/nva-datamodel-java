package no.unit.nva.model.instancetypes;

import no.unit.nva.model.pages.MonographPages;

public class MonographTestData extends TestData {
    private final MonographPages pages;

    public MonographTestData() {
        super();
        this.pages = generateMonographPages();
    }

    public MonographTestData(boolean peerReviewed) {
        this();
        setPeerReviewed(peerReviewed);
    }

    public MonographPages getPages() {
        return pages;
    }

    private MonographPages generateMonographPages() {
        return new MonographPages.Builder()
            .withIntroduction(generateRange())
            .withPages(String.valueOf(RANDOM.ints(100, 500)))
            .withIllustrated(RANDOM.nextBoolean())
            .build();
    }
}

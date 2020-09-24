package no.unit.nva.model.instancetypes;

import no.unit.nva.model.pages.MonographPages;

public class MonographTestData extends TestData {
    private final MonographPages pages;

    public MonographTestData() {
        super();
        this.pages = generateMonographPages();
    }

    /**
     * Builds test data object for testing Monograph objects.
     * @param peerReviewed if the object is peer-reviewed.
     * @param textbookContent if the object is a textbook.
     */
    public MonographTestData(boolean peerReviewed, boolean textbookContent) {
        this();
        setPeerReviewed(peerReviewed);
        setTextbookContent(textbookContent);
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

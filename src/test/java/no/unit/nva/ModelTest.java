package no.unit.nva;

import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Range;

public class ModelTest {


    protected static MonographPages generateMonographPages(String introductionBegin,
                                                    String introductionEnd,
                                                    String pages,
                                                    boolean illustrated) throws InvalidPageRangeException {
        return new MonographPages.Builder()
                .withPages(pages)
                .withIllustrated(illustrated)
                .withIntroduction(generateRange(introductionBegin, introductionEnd))
                .build();
    }

    protected static Range generateRange(String begin, String end) throws InvalidPageRangeException {
        return new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();
    }
}

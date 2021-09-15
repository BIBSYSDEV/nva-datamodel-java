package no.unit.nva.utils;

import static no.unit.nva.model.util.PublicationGenerator.randomIssn;
import static no.unit.nva.utils.RandomData.randomIsbn;
import static no.unit.nva.utils.RandomData.randomString;

import java.util.List;

import no.unit.nva.model.contexttypes.Book;
import no.unit.nva.model.contexttypes.UnconfirmedPublisher;
import no.unit.nva.model.contexttypes.UnconfirmedSeries;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidIssnException;

public final class RandomPublicationContexts {

    private RandomPublicationContexts() {

    }

    public static Book randomBook() throws InvalidIsbnException, InvalidIssnException {
        return new Book.BookBuilder()
            .withIsbnList(randomIsbnList())
            .withPublisher(new UnconfirmedPublisher(randomString()))
            .withSeries(new UnconfirmedSeries(randomString(),randomIssn()))
            .withSeriesNumber(randomString())
            .build();
    }

    private static List<String> randomIsbnList() {
        return List.of(randomIsbn());
    }
}

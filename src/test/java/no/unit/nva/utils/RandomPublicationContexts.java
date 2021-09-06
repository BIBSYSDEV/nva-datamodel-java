package no.unit.nva.utils;

import static no.unit.nva.utils.RandomData.randomIsbn;
import static no.unit.nva.utils.RandomData.randomString;

import java.util.List;

import no.unit.nva.model.contexttypes.Book;
import no.unit.nva.model.contexttypes.UnconfirmedSeries;
import no.unit.nva.model.exceptions.InvalidIsbnException;

public final class RandomPublicationContexts {

    private RandomPublicationContexts() {

    }

    public static Book randomBook() throws InvalidIsbnException {
        return new Book.BookBuilder()
            .withIsbnList(randomIsbnList())
            .withPublisher(randomString())
            .withSeries(new UnconfirmedSeries(randomString()))
            .withSeriesNumber(randomString())
            .build();
    }

    private static List<String> randomIsbnList() {
        return List.of(randomIsbn());
    }
}

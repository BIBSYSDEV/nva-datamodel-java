package no.unit.nva.utils;

import static no.unit.nva.utils.RandomData.randomIsbn;
import static no.unit.nva.utils.RandomData.randomString;
import static no.unit.nva.utils.RandomData.randomUri;
import java.net.URI;
import java.util.List;
import no.unit.nva.model.contexttypes.Book;
import no.unit.nva.model.contexttypes.Book.Builder;
import no.unit.nva.model.exceptions.InvalidIsbnException;

public final class RandomPublicationContexts {

    private RandomPublicationContexts() {

    }

    public static Book randomBook() throws InvalidIsbnException {
        return new Builder()
            .withIsbnList(randomIsbnList())
            .withPublisher(randomString())
            .withSeriesTitle(randomString())
            .withSeriesNumber(randomString())
            .withSeriesUri(validSeriesUri())
            .build();
    }

    private static URI validSeriesUri() {
        return URI.create("https://api.dev.nva.aws.unit.no/publication-channels/journal/449575/2020");
    }

    private static List<String> randomIsbnList() {
        return List.of(randomIsbn());
    }
}

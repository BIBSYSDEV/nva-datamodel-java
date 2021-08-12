package no.unit.nva.utils;

import static no.unit.nva.utils.RandomData.randomBoolean;
import static no.unit.nva.utils.RandomData.randomElement;
import static no.unit.nva.utils.RandomData.randomIsbn;
import static no.unit.nva.utils.RandomData.randomString;
import static no.unit.nva.utils.RandomData.randomUri;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import no.unit.nva.model.Level;
import no.unit.nva.model.contexttypes.Book;
import no.unit.nva.model.contexttypes.Book.Builder;
import no.unit.nva.model.exceptions.InvalidIsbnException;

public final class RandomPublicationContexts {

    private RandomPublicationContexts() {

    }

    public static Book randomBook() throws MalformedURLException, InvalidIsbnException {
        return new Builder()
            .withIsbnList(randomIsbnList())
            .withLevel(randomLevel())
            .withOpenAccess(randomBoolean())
            .withPeerReviewed(randomBoolean())
            .withPublisher(randomString())
            .withLinkedContext(randomUri())
            .withSeriesTitle(randomString())
            .withSeriesNumber(randomString())
            .withUrl(randomUri().toURL())
            .withSeriesUri(validSeriesUri())
            .build();
    }

    private static URI validSeriesUri() {
        return URI.create("https://api.dev.nva.aws.unit.no/publication-channels/journal/449575/2020");
    }

    private static Level randomLevel() {
        return randomElement(Level.values());
    }

    private static List<String> randomIsbnList() {
        return List.of(randomIsbn());
    }
}

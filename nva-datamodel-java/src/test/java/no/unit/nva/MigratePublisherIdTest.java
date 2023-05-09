package no.unit.nva;

import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import java.net.URI;
import java.time.LocalDate;
import no.unit.nva.model.contexttypes.Publisher;
import nva.commons.core.paths.UriWrapper;
import org.junit.jupiter.api.Test;

class MigratePublisherIdTest {

    private static final String API_HOST = "localhost";
    private static final String BASE_PATH = "publication-channels";
    private static final String PUBLISHER_PATH_ELEMENT = "publisher";
    private static final int YEAR_START = 1900;

    @Test
    void shouldReplaceOldPublisherIdWithNewId() {
        var year = randomYear();
        var oldIdentifier = "28102";
        var newIdentifier = "D61B0D47-C78A-48DC-8537-3AD87DEF4D5B";
        var oldId = constructPublicationChannelId(year, oldIdentifier);
        var newId = constructPublicationChannelId(year, newIdentifier);
        var publisher = new Publisher(oldId);
        assertThat(publisher.getId(), is(equalTo(newId)));
    }

    private static URI constructPublicationChannelId(String year, String identifier) {
        return UriWrapper.fromHost(API_HOST)
            .addChild(BASE_PATH)
            .addChild(PUBLISHER_PATH_ELEMENT)
            .addChild(identifier)
            .addChild(year)
            .getUri();
    }

    private String randomYear() {
        var bound = (LocalDate.now().getYear() + 1) - YEAR_START;
        return Integer.toString(YEAR_START + randomInteger(bound));
    }
}

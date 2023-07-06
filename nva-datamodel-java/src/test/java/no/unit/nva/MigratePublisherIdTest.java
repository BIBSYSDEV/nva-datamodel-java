package no.unit.nva;

import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.BASE_PATH_V1;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.EXAMPLE_COM;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.PUBLISHER_PATH_ELEMENT;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.constructNewStyleId;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.constructOldStyleId;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.constructPublicationChannelId;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.getNewIdentifierByOldIdentifier;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.randomOldPublisherIdentifier;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.randomYear;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import no.unit.nva.model.contexttypes.Publisher;
import no.unit.nva.model.contexttypes.utils.ChannelType;
import org.junit.jupiter.api.Test;

@Deprecated
class MigratePublisherIdTest {

    private static final ChannelType PUBLISHER = ChannelType.PUBLISHER;
    private static final String OLD_IDENTIFIER_WITHOUT_MAPPING = "111111";

    @Test
    void shouldReplaceOldPublisherIdWithNewIdIfOldIdIsHostedInDev() {
        var year = randomYear();
        var oldIdentifier = randomOldPublisherIdentifier();
        var newIdentifier = getNewIdentifierByOldIdentifier(oldIdentifier, PUBLISHER);
        var oldId = constructOldStyleId(year, oldIdentifier, PUBLISHER_PATH_ELEMENT);
        var expectedNewId = constructNewStyleId(year, newIdentifier, PUBLISHER_PATH_ELEMENT);
        var publisher = new Publisher(oldId);
        assertThat(publisher.getId(), is(equalTo(expectedNewId)));
    }

    @Test
    void shouldNotReplaceOldPublisherIfOldIdIsNotHostedInDev() {
        var year = randomYear();
        var oldIdentifier = randomOldPublisherIdentifier();
        var oldId = constructPublicationChannelId(year, oldIdentifier, PUBLISHER_PATH_ELEMENT,
                                                  EXAMPLE_COM, BASE_PATH_V1);
        var publisher = new Publisher(oldId);
        assertThat(publisher.getId(), is(equalTo(oldId)));
    }

    @Test
    void shouldNotReplaceOldPublisherIdIfNotFound() {
        var year = randomYear();
        var oldId = constructOldStyleId(year, OLD_IDENTIFIER_WITHOUT_MAPPING, PUBLISHER_PATH_ELEMENT);
        var publisher = new Publisher(oldId);
        assertThat(publisher.getId(), is(equalTo(oldId)));
    }
}

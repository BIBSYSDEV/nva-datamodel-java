package no.unit.nva;

import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.constructPublicationChannelId;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.getNewIdentifierByOldIdentifier;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.randomOldPublisherIdentifier;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.randomYear;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import no.unit.nva.model.contexttypes.Publisher;
import no.unit.nva.model.contexttypes.utils.ChannelType;
import no.unit.nva.utils.MigratePublicationChannelIdTestUtils;
import org.junit.jupiter.api.Test;

class MigratePublisherIdTest {

    private static final ChannelType PUBLISHER = ChannelType.PUBLISHER;

    @Test
    void shouldReplaceOldPublisherIdWithNewId() {
        var year = randomYear();
        var oldIdentifier = randomOldPublisherIdentifier();
        var newIdentifier = getNewIdentifierByOldIdentifier(oldIdentifier, PUBLISHER);
        var oldId = constructPublicationChannelId(year, oldIdentifier,
                                                  MigratePublicationChannelIdTestUtils.PUBLISHER_PATH_ELEMENT);
        var newId = constructPublicationChannelId(year, newIdentifier,
                                                  MigratePublicationChannelIdTestUtils.PUBLISHER_PATH_ELEMENT);
        var publisher = new Publisher(oldId);
        assertThat(publisher.getId(), is(equalTo(newId)));
    }
}

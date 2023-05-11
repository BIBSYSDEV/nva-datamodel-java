package no.unit.nva;

import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.JOURNAL_PATH_ELEMENT;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.constructPublicationChannelId;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.getNewIdentifierByOldIdentifier;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.randomOldJournalIdentifier;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.randomYear;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import no.unit.nva.model.contexttypes.Journal;
import no.unit.nva.model.contexttypes.utils.ChannelType;
import org.junit.jupiter.api.Test;

class MigrateJournalIdTest {

    public static final ChannelType JOURNAL = ChannelType.JOURNAL;

    @Test
    void shouldReplaceOldJournalIdWithNewId() {
        var year = randomYear();
        var oldIdentifier = randomOldJournalIdentifier();
        var newIdentifier = getNewIdentifierByOldIdentifier(oldIdentifier, JOURNAL);
        var oldId = constructPublicationChannelId(year, oldIdentifier, JOURNAL_PATH_ELEMENT);
        var expectedNewId = constructPublicationChannelId(year, newIdentifier, JOURNAL_PATH_ELEMENT);
        var journal = new Journal(oldId);
        assertThat(journal.getId(), is(equalTo(expectedNewId)));
    }
}

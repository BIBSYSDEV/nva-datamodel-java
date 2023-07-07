package no.unit.nva;

import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.BASE_PATH_V1;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.EXAMPLE_COM;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.JOURNAL_PATH_ELEMENT;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.constructNewStyleId;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.constructOldStyleId;
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

@Deprecated
class MigrateJournalIdTest {

    private static final ChannelType JOURNAL = ChannelType.JOURNAL;
    private static final String OLD_IDENTIFIER_WITHOUT_MAPPING = "111111";

    @Test
    void shouldReplaceOldJournalIdWithNewIdIfOldIdIsHostedInDev() {
        var year = randomYear();
        var oldIdentifier = randomOldJournalIdentifier();
        var newIdentifier = getNewIdentifierByOldIdentifier(oldIdentifier, JOURNAL);
        var oldId = constructOldStyleId(year, oldIdentifier, JOURNAL_PATH_ELEMENT);
        var expectedNewId = constructNewStyleId(year, newIdentifier, JOURNAL_PATH_ELEMENT);
        var journal = new Journal(oldId);
        assertThat(journal.getId(), is(equalTo(expectedNewId)));
    }

    @Test
    void shouldNotReplaceOldJournalIdWithNewIdIfOldIdIsNotHostedInDev() {
        var year = randomYear();
        var oldIdentifier = randomOldJournalIdentifier();
        var oldId = constructPublicationChannelId(year, oldIdentifier, JOURNAL_PATH_ELEMENT,
                                                  EXAMPLE_COM, BASE_PATH_V1);
        var journal = new Journal(oldId);
        assertThat(journal.getId(), is(equalTo(oldId)));
    }

    @Test
    void shouldNotReplaceOldJournalIdIfNotFound() {
        var year = randomYear();
        var oldId = constructOldStyleId(year, OLD_IDENTIFIER_WITHOUT_MAPPING,
                                        JOURNAL_PATH_ELEMENT);
        var journal = new Journal(oldId);
        assertThat(journal.getId(), is(equalTo(oldId)));
    }
}

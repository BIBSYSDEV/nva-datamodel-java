package no.unit.nva;

import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.JOURNAL_PATH_ELEMENT;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.constructPublicationChannelId;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.randomYear;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import no.unit.nva.model.contexttypes.Journal;
import org.junit.jupiter.api.Test;

class MigrateJournalIdTest {

    @Test
    void shouldReplaceOldJournalIdWithNewId() {
        var year = randomYear();
        var oldIdentifier = "28102";
        var newIdentifier = "D61B0D47-C78A-48DC-8537-3AD87DEF4D5B";
        var oldId = constructPublicationChannelId(year, oldIdentifier, JOURNAL_PATH_ELEMENT);
        var expectedNewId = constructPublicationChannelId(year, newIdentifier, JOURNAL_PATH_ELEMENT);
        var journal = new Journal(oldId);
        assertThat(journal.getId(), is(equalTo(expectedNewId)));
    }
}

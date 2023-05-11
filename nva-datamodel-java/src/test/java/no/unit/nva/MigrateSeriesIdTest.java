package no.unit.nva;

import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.constructPublicationChannelId;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.randomYear;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import no.unit.nva.model.contexttypes.Series;
import no.unit.nva.utils.MigratePublicationChannelIdTestUtils;
import org.junit.jupiter.api.Test;

class MigrateSeriesIdTest {

    @Test
    void shouldReplaceOldSeriesIdWithNewId() {
        var year = randomYear();
        var oldIdentifier = "28102";
        var newIdentifier = "D61B0D47-C78A-48DC-8537-3AD87DEF4D5B";
        var oldId = constructPublicationChannelId(year, oldIdentifier,
                                                  MigratePublicationChannelIdTestUtils.SERIES_PATH_ELEMENT);
        var newId = constructPublicationChannelId(year, newIdentifier,
                                                  MigratePublicationChannelIdTestUtils.SERIES_PATH_ELEMENT);
        var series = new Series(oldId);
        assertThat(series.getId(), is(equalTo(newId)));
    }
}

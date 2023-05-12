package no.unit.nva;

import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.SERIES_PATH_ELEMENT;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.constructPublicationChannelId;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.getNewIdentifierByOldIdentifier;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.randomOldSeriesIdentifier;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.randomYear;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import no.unit.nva.model.contexttypes.Series;
import no.unit.nva.model.contexttypes.utils.ChannelType;
import org.junit.jupiter.api.Test;

class MigrateSeriesIdTest {

    private static final ChannelType SERIES = ChannelType.SERIES;
    private static final String OLD_IDENTIFIER_WITHOUT_MAPPING = "111111";

    @Test
    void shouldReplaceOldSeriesIdWithNewId() {
        var year = randomYear();
        var oldIdentifier = randomOldSeriesIdentifier();
        var newIdentifier = getNewIdentifierByOldIdentifier(oldIdentifier, SERIES);
        var oldId = constructPublicationChannelId(year, oldIdentifier, SERIES_PATH_ELEMENT);
        var newId = constructPublicationChannelId(year, newIdentifier, SERIES_PATH_ELEMENT);
        var series = new Series(oldId);
        assertThat(series.getId(), is(equalTo(newId)));
    }

    @Test
    void shouldNotReplaceOldSeriesIdIfNotFound() {
        var year = randomYear();
        var oldId = constructPublicationChannelId(year, OLD_IDENTIFIER_WITHOUT_MAPPING, SERIES_PATH_ELEMENT);
        var series = new Series(oldId);
        assertThat(series.getId(), is(equalTo(oldId)));
    }
}

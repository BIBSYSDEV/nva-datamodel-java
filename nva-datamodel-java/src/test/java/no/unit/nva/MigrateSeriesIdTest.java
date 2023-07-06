package no.unit.nva;

import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.BASE_PATH_V1;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.EXAMPLE_COM;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.SERIES_PATH_ELEMENT;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.constructNewStyleId;
import static no.unit.nva.utils.MigratePublicationChannelIdTestUtils.constructOldStyleId;
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

@Deprecated
class MigrateSeriesIdTest {

    private static final ChannelType SERIES = ChannelType.SERIES;
    private static final String OLD_IDENTIFIER_WITHOUT_MAPPING = "111111";

    @Test
    void shouldReplaceOldSeriesIdWithNewId() {
        var year = randomYear();
        var oldIdentifier = randomOldSeriesIdentifier();
        var newIdentifier = getNewIdentifierByOldIdentifier(oldIdentifier, SERIES);
        var oldId = constructOldStyleId(year, oldIdentifier, SERIES_PATH_ELEMENT);
        var newId = constructNewStyleId(year, newIdentifier, SERIES_PATH_ELEMENT);
        var series = new Series(oldId);
        assertThat(series.getId(), is(equalTo(newId)));
    }

    @Test
    void shouldNotReplaceOldSeriesIdIfOldIdIsNotHostedInDev() {
        var year = randomYear();
        var oldIdentifier = randomOldSeriesIdentifier();
        var oldId = constructPublicationChannelId(year, oldIdentifier, SERIES_PATH_ELEMENT, EXAMPLE_COM, BASE_PATH_V1);
        var series = new Series(oldId);
        assertThat(series.getId(), is(equalTo(oldId)));
    }

    @Test
    void shouldNotReplaceOldSeriesIdIfNotFound() {
        var year = randomYear();
        var oldId = constructOldStyleId(year, OLD_IDENTIFIER_WITHOUT_MAPPING, SERIES_PATH_ELEMENT);
        var series = new Series(oldId);
        assertThat(series.getId(), is(equalTo(oldId)));
    }
}

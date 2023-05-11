package no.unit.nva.utils;

import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import java.net.URI;
import java.time.LocalDate;
import nva.commons.core.paths.UriWrapper;

public class MigratePublicationChannelIdTestUtils {

    private static final String API_HOST = "localhost";
    private static final String BASE_PATH = "publication-channels";

    public static final String JOURNAL_PATH_ELEMENT = "journal";
    private static final int YEAR_START = 1900;
    public static final String SERIES_PATH_ELEMENT = "series";
    public static final String PUBLISHER_PATH_ELEMENT = "publisher";

    private MigratePublicationChannelIdTestUtils() {
    }

    public static URI constructPublicationChannelId(String year, String identifier, String pathElement) {
        return UriWrapper.fromHost(API_HOST)
                   .addChild(BASE_PATH)
                   .addChild(pathElement)
                   .addChild(identifier)
                   .addChild(year)
                   .getUri();
    }

    public static String randomYear() {
        var bound = (LocalDate.now().getYear() + 1) - YEAR_START;
        return Integer.toString(YEAR_START + randomInteger(bound));
    }
}

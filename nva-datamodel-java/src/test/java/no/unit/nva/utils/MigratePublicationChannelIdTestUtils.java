package no.unit.nva.utils;

import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static nva.commons.core.ioutils.IoUtils.linesfromResource;
import java.net.URI;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import no.unit.nva.model.contexttypes.utils.ChannelType;
import nva.commons.core.paths.UriWrapper;

public class MigratePublicationChannelIdTestUtils {

    public static final String JOURNAL_PATH_ELEMENT = "journal";
    public static final String SERIES_PATH_ELEMENT = "series";
    public static final String PUBLISHER_PATH_ELEMENT = "publisher";
    private static final String API_HOST = "localhost";
    private static final String BASE_PATH = "publication-channels";
    private static final int YEAR_START = 1900;
    private static final String CSV_SEPARATOR = ";";
    private static final int NUMBER_OF_COLUMNS = 2;
    private static final int OLD_ID_COLUMN_NUMBER = 0;
    private static final int NEW_ID_COLUMN_NUMBER = 1;

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

    public static String randomOldIdentifier(ChannelType type) {
        var identifierMap = getIdentifierMap(type.migrationFileName);
        var oldIdentifiers = new ArrayList<String>(identifierMap.keySet());
        return oldIdentifiers.get(randomInteger(identifierMap.size() - 1));
    }

    public static String getNewIdentifierByOldIdentifier(String oldIdentifier, ChannelType type) {
        var identifierMap = getIdentifierMap(type.migrationFileName);
        return identifierMap.get(oldIdentifier);
    }

    private static Map<String, String> getIdentifierMap(String file) {
        var identifierMap = new HashMap<String, String>();
        var lines = linesfromResource(Path.of(file));
        lines.stream().filter(line -> line.contains(CSV_SEPARATOR)).forEach(line -> {
            var idMapping = line.split(CSV_SEPARATOR, NUMBER_OF_COLUMNS);
            identifierMap.putIfAbsent(idMapping[OLD_ID_COLUMN_NUMBER], idMapping[NEW_ID_COLUMN_NUMBER]);
        });
        return identifierMap;
    }
}

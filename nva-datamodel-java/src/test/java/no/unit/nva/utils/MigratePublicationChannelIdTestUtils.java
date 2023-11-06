package no.unit.nva.utils;

import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static nva.commons.core.ioutils.IoUtils.linesfromResource;
import java.net.URI;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import no.unit.nva.model.contexttypes.utils.ChannelType;
import nva.commons.core.paths.UriWrapper;

@Deprecated
public class MigratePublicationChannelIdTestUtils {

    public static final String JOURNAL_PATH_ELEMENT = "journal";
    public static final String SERIES_PATH_ELEMENT = "series";
    public static final String PUBLISHER_PATH_ELEMENT = "publisher";
    public static final String API_HOST_PROD = "api.nva.aws.unit.no";
    public static final String EXAMPLE_COM = "example.com";
    public static final String BASE_PATH_V1 = "publication-channels";
    public static final String BASE_PATH_V2 = "publication-channels-v2";
    private static final String JOURNAL_ID_MAPPING_FILE = ChannelType.JOURNAL.migrationFileName;
    private static final String SERIES_ID_MAPPING_FILE = ChannelType.SERIES.migrationFileName;
    private static final String PUBLISHER_ID_MAPPING_FILE = ChannelType.PUBLISHER.migrationFileName;
    private static final int YEAR_START = 1900;
    private static final String CSV_SEPARATOR = ";";
    private static final int NUMBER_OF_COLUMNS = 2;
    private static final int OLD_ID_COLUMN_NUMBER = 0;
    private static final int NEW_ID_COLUMN_NUMBER = 1;
    private static final Map<String, String> journalIdMapping = linesfromResource(
        Path.of(JOURNAL_ID_MAPPING_FILE)).stream()
                                                    .filter(
                                                        MigratePublicationChannelIdTestUtils::containsCsvSeparator)
                                                    .map(
                                                        MigratePublicationChannelIdTestUtils::splitLineBySeparator)
                                                    .collect(Collectors.toMap(
                                                        identifierList -> identifierList[OLD_ID_COLUMN_NUMBER],
                                                        identifierList -> identifierList[NEW_ID_COLUMN_NUMBER]));
    public static final ArrayList<String> OLD_JOURNAL_IDENTIFIERS = new ArrayList<>(journalIdMapping.keySet());
    private static final Map<String, String> seriesIdMapping = linesfromResource(
        Path.of(SERIES_ID_MAPPING_FILE)).stream()
                                                   .filter(
                                                       MigratePublicationChannelIdTestUtils::containsCsvSeparator)
                                                   .map(
                                                       MigratePublicationChannelIdTestUtils::splitLineBySeparator)
                                                   .collect(Collectors.toMap(
                                                       identifierList -> identifierList[OLD_ID_COLUMN_NUMBER],
                                                       identifierList -> identifierList[NEW_ID_COLUMN_NUMBER]));
    public static final ArrayList<String> OLD_SERIES_IDENTIFIERS = new ArrayList<>(seriesIdMapping.keySet());
    private static final Map<String, String> publisherIdMapping = linesfromResource(
        Path.of(PUBLISHER_ID_MAPPING_FILE)).stream()
                                                      .filter(
                                                          MigratePublicationChannelIdTestUtils::containsCsvSeparator)
                                                      .map(
                                                          MigratePublicationChannelIdTestUtils::splitLineBySeparator)
                                                      .collect(Collectors.toMap(
                                                          identifierList -> identifierList[OLD_ID_COLUMN_NUMBER],
                                                          identifierList -> identifierList[NEW_ID_COLUMN_NUMBER]));
    public static final ArrayList<String> OLD_PUBLISHER_IDENTIFIERS = new ArrayList<>(publisherIdMapping.keySet());

    private MigratePublicationChannelIdTestUtils() {
    }

    public static URI constructNewStyleId(String year, String newIdentifier, String pathElement) {
        return constructPublicationChannelId(year, newIdentifier, pathElement, API_HOST_PROD, BASE_PATH_V2);
    }

    public static URI constructOldStyleId(String year, String oldIdentifier, String pathElement) {
        return constructPublicationChannelId(year, oldIdentifier, pathElement, API_HOST_PROD, BASE_PATH_V1);
    }

    public static URI constructPublicationChannelId(String year, String identifier, String pathElement, String apiHost,
                                                    String basePath) {
        return UriWrapper.fromHost(apiHost)
                   .addChild(basePath)
                   .addChild(pathElement)
                   .addChild(identifier)
                   .addChild(year)
                   .getUri();
    }

    public static String randomYear() {
        var bound = (LocalDate.now().getYear() + 1) - YEAR_START;
        return Integer.toString(YEAR_START + randomInteger(bound));
    }

    public static String randomOldJournalIdentifier() {
        return OLD_JOURNAL_IDENTIFIERS.get(randomInteger(journalIdMapping.size()));
    }

    public static String randomOldSeriesIdentifier() {
        return OLD_SERIES_IDENTIFIERS.get(randomInteger(seriesIdMapping.size()));
    }

    public static String randomOldPublisherIdentifier() {
        return OLD_PUBLISHER_IDENTIFIERS.get(randomInteger(publisherIdMapping.size()));
    }

    public static String getNewIdentifierByOldIdentifier(String oldIdentifier, ChannelType type) {
        switch (type) {
            case PUBLISHER:
                return publisherIdMapping.get(oldIdentifier);
            case SERIES:
                return seriesIdMapping.get(oldIdentifier);
            case JOURNAL:
                return journalIdMapping.get(oldIdentifier);
            default:
                throw new IllegalArgumentException("Invalid channel type");
        }
    }

    private static boolean containsCsvSeparator(String line1) {
        return line1.contains(CSV_SEPARATOR);
    }

    private static String[] splitLineBySeparator(String line) {
        return line.split(CSV_SEPARATOR, NUMBER_OF_COLUMNS);
    }
}

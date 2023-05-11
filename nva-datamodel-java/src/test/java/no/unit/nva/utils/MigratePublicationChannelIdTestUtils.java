package no.unit.nva.utils;

import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static nva.commons.core.ioutils.IoUtils.linesfromResource;
import java.net.URI;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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

    private static final Map<String, String> journalIdMapping = linesfromResource(
        Path.of(ChannelType.JOURNAL.migrationFileName)).stream()
                                                                    .filter(getSeparatorPredicate())
                                                                    .map(getLineSplitFunction())
                                                                    .collect(Collectors.toMap(
                                                                        identifierMap -> identifierMap[OLD_ID_COLUMN_NUMBER],
                                                                        identifierMap -> identifierMap[NEW_ID_COLUMN_NUMBER]));
    public static final ArrayList<String> OLD_JOURNAL_IDENTIFIERS = new ArrayList<>(journalIdMapping.keySet());
    private static final Map<String, String> seriesIdMapping = linesfromResource(
        Path.of(ChannelType.SERIES.migrationFileName)).stream()
                                                                   .filter(getSeparatorPredicate())
                                                                   .map(getLineSplitFunction())
                                                                   .collect(Collectors.toMap(
                                                                       identifierMap -> identifierMap[OLD_ID_COLUMN_NUMBER],
                                                                       identifierMap -> identifierMap[NEW_ID_COLUMN_NUMBER]));
    public static final ArrayList<String> OLD_SERIES_IDENTIFIERS = new ArrayList<>(seriesIdMapping.keySet());
    private static final Map<String, String> publisherIdMapping = linesfromResource(
        Path.of(ChannelType.PUBLISHER.migrationFileName)).stream()
                                                                      .filter(getSeparatorPredicate())
                                                                      .map(getLineSplitFunction())
                                                                      .collect(Collectors.toMap(
                                                                          identifierMap -> identifierMap[OLD_ID_COLUMN_NUMBER],
                                                                          identifierMap -> identifierMap[NEW_ID_COLUMN_NUMBER]));
    public static final ArrayList<String> OLD_PUBLISHER_IDENTIFIERS = new ArrayList<>(publisherIdMapping.keySet());

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

    public static String randomOldJournalIdentifier() {
        return OLD_JOURNAL_IDENTIFIERS.get(randomInteger(journalIdMapping.size() - 1));
    }

    public static String randomOldSeriesIdentifier() {
        return OLD_SERIES_IDENTIFIERS.get(randomInteger(seriesIdMapping.size() - 1));
    }

    public static String randomOldPublisherIdentifier() {
        return OLD_PUBLISHER_IDENTIFIERS.get(randomInteger(publisherIdMapping.size() - 1));
    }

    public static String getNewIdentifierByOldIdentifier(String oldIdentifier, ChannelType type) {
        switch (type) {
            case PUBLISHER -> {
                return publisherIdMapping.get(oldIdentifier);
            }
            case SERIES -> {
                return seriesIdMapping.get(oldIdentifier);
            }
            case JOURNAL -> {
                return journalIdMapping.get(oldIdentifier);
            }
        }
        throw new IllegalArgumentException("Invalid channel type");
    }

    private static Function<String, String[]> getLineSplitFunction() {
        return line -> line.split(CSV_SEPARATOR, NUMBER_OF_COLUMNS);
    }

    private static Predicate<String> getSeparatorPredicate() {
        return line -> line.contains(CSV_SEPARATOR);
    }
}

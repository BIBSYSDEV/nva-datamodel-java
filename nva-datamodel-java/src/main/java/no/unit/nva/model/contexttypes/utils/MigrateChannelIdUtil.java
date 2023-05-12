package no.unit.nva.model.contexttypes.utils;

import static java.util.Objects.nonNull;
import static nva.commons.core.ioutils.IoUtils.linesfromResource;
import java.net.URI;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import nva.commons.core.paths.UriWrapper;

public final class MigrateChannelIdUtil {

    private static final int IDENTIFIER_PATH_INDEX_FROM_END = 1;
    private static final String JOURNAL_ID_MAPPING_FILE = ChannelType.JOURNAL.migrationFileName;
    private static final String SERIES_ID_MAPPING_FILE = ChannelType.SERIES.migrationFileName;
    private static final String PUBLISHER_ID_MAPPING_FILE = ChannelType.PUBLISHER.migrationFileName;
    private static final String BASE_PATH = "publication-channels";
    private static final String CSV_SEPARATOR = ";";
    private static final int NUMBER_OF_COLUMNS = 2;
    private static final int OLD_ID_COLUMN_NUMBER = 0;
    private static final int NEW_ID_COLUMN_NUMBER = 1;
    private static final Map<String, String> journalIdMapping = linesfromResource(
            Path.of(JOURNAL_ID_MAPPING_FILE)).stream()
            .filter(MigrateChannelIdUtil::containsCsvSeparator)
            .map(MigrateChannelIdUtil::splitLineBySeparator)
            .collect(Collectors.toMap(
                    identifierList -> identifierList[OLD_ID_COLUMN_NUMBER],
                    identifierList -> identifierList[NEW_ID_COLUMN_NUMBER]));
    private static final Map<String, String> seriesIdMapping = linesfromResource(
            Path.of(SERIES_ID_MAPPING_FILE)).stream()
            .filter(MigrateChannelIdUtil::containsCsvSeparator)
            .map(MigrateChannelIdUtil::splitLineBySeparator)
            .collect(Collectors.toMap(
                    identifierList -> identifierList[OLD_ID_COLUMN_NUMBER],
                    identifierList -> identifierList[NEW_ID_COLUMN_NUMBER]));
    private static final Map<String, String> publisherIdMapping = linesfromResource(
            Path.of(PUBLISHER_ID_MAPPING_FILE)).stream()
            .filter(
                    MigrateChannelIdUtil::containsCsvSeparator)
            .map(MigrateChannelIdUtil::splitLineBySeparator)
            .collect(Collectors.toMap(
                    identifierList -> identifierList[OLD_ID_COLUMN_NUMBER],
                    identifierList -> identifierList[NEW_ID_COLUMN_NUMBER]));

    private MigrateChannelIdUtil() {
    }

    public static URI migrateToNewIdIfFound(URI id, ChannelType type) {
        var newIdentifier = getNewIdentifier(id, type);
        var year = UriWrapper.fromUri(id).getPath().getLastPathElement();
        return nonNull(newIdentifier) ? constructNewPublicationChannelId(id, year, newIdentifier, type.pathElement)
                   : id;
    }

    private static boolean containsCsvSeparator(String line1) {
        return line1.contains(CSV_SEPARATOR);
    }

    private static String[] splitLineBySeparator(String line) {
        return line.split(CSV_SEPARATOR, NUMBER_OF_COLUMNS);
    }

    private static String getNewIdentifier(URI id, ChannelType type) {
        var oldIdentifier = UriWrapper.fromUri(id)
                                .getPath()
                                .getPathElementByIndexFromEnd(IDENTIFIER_PATH_INDEX_FROM_END);
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

    private static URI constructNewPublicationChannelId(URI id, String year, String newIdentifier, String pathElement) {
        return UriWrapper.fromHost(id.getHost())
                   .addChild(BASE_PATH)
                   .addChild(pathElement)
                   .addChild(newIdentifier)
                   .addChild(year)
                   .getUri();
    }
}

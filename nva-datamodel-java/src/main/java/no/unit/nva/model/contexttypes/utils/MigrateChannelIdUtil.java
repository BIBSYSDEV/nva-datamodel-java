package no.unit.nva.model.contexttypes.utils;

import static java.util.Objects.nonNull;
import static nva.commons.core.ioutils.IoUtils.linesfromResource;
import java.net.URI;
import java.nio.file.Path;
import java.util.HashMap;
import nva.commons.core.paths.UriWrapper;

public final class MigrateChannelIdUtil {

    private static final String BASE_PATH = "publication-channels";
    private static final String JOURNAL_PATH_ELEMENT = "journal";
    private static final String JOURNAL_MIGRATION_EXAMPLE_CSV = "journal_migration_example.csv";
    private static final String CSV_SEPERATOR = ";";
    private static final int NUMBER_OF_COLUMNS = 2;
    private static final int OLD_ID_COLUMN_NUMBER = 0;
    private static final int NEW_ID_COLUMN_NUMBER = 1;

    private MigrateChannelIdUtil() {
    }

    public static URI migrateToNewIdIfFound(URI id) {
        var newIdentifier = getNewIdentifier(id);
        var year = UriWrapper.fromUri(id).getPath().getLastPathElement();
        return nonNull(newIdentifier) ? constructNewPublicationChannelId(id, year, newIdentifier) : id;
    }

    private static String getNewIdentifier(URI id) {
        var oldIdentifier = UriWrapper.fromUri(id)
            .getPath()
            .getLastPathElement(); //TODO: replace with UnixPath.getPathElementByIndexFromEnd(2)
        return getIdentifierMap().get(oldIdentifier);
    }

    private static HashMap<String, String> getIdentifierMap() {
        var identifierMap = new HashMap<String, String>();
        var lines = linesfromResource(Path.of(JOURNAL_MIGRATION_EXAMPLE_CSV));
        lines.stream()
            .filter(line -> line.contains(CSV_SEPERATOR))
            .forEach(line -> {
                var idMapping = line.split(CSV_SEPERATOR, NUMBER_OF_COLUMNS);
                identifierMap.putIfAbsent(idMapping[OLD_ID_COLUMN_NUMBER], idMapping[NEW_ID_COLUMN_NUMBER]);
            });
        return identifierMap;
    }

    private static URI constructNewPublicationChannelId(URI id, String year, String newIdentifier) {
        return UriWrapper.fromHost(id.getHost())
            .addChild(BASE_PATH)
            .addChild(JOURNAL_PATH_ELEMENT)
            .addChild(newIdentifier)
            .addChild(year)
            .getUri();
    }
}

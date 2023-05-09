package no.unit.nva.model.contexttypes.utils;

import static java.util.Objects.nonNull;
import static nva.commons.core.ioutils.IoUtils.linesfromResource;
import java.net.URI;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import nva.commons.core.paths.UriWrapper;

public final class MigrateChannelIdUtil {

    private static final String BASE_PATH = "publication-channels";
    private static final String CSV_SEPARATOR = ";";
    private static final int NUMBER_OF_COLUMNS = 2;
    private static final int OLD_ID_COLUMN_NUMBER = 0;
    private static final int NEW_ID_COLUMN_NUMBER = 1;

    private MigrateChannelIdUtil() {
    }

    public static URI migrateToNewIdIfFound(URI id, ChannelType type) {
        var newIdentifier = getNewIdentifier(id, type);
        var year = UriWrapper.fromUri(id).getPath().getLastPathElement();
        return nonNull(newIdentifier) ? constructNewPublicationChannelId(id, year, newIdentifier, type.pathElement)
                   : id;
    }

    private static String getNewIdentifier(URI id, ChannelType type) {
        var oldIdentifier = "28102";
        //        UriWrapper.fromUri(id)
        //            .getPath()
        //            .getLastPathElement(); //TODO: replace with UnixPath.getPathElementByIndexFromEnd(2)
        return getIdentifierMap(type.migrationFileName).get(oldIdentifier);
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

    private static URI constructNewPublicationChannelId(URI id, String year, String newIdentifier, String pathElement) {
        return UriWrapper.fromHost(id.getHost())
                   .addChild(BASE_PATH)
                   .addChild(pathElement)
                   .addChild(newIdentifier)
                   .addChild(year)
                   .getUri();
    }
}

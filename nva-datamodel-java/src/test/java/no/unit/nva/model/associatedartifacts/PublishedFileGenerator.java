package no.unit.nva.model.associatedartifacts;

import no.unit.nva.model.associatedartifacts.file.PublishedFile;

import java.util.UUID;

import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;

public final class PublishedFileGenerator extends AssociatedFileGenerator {
    private PublishedFileGenerator() {
        // NO-OP
    }

    public static PublishedFile random() {
        return new PublishedFile(UUID.randomUUID(), randomString(), randomString(), randomInteger().longValue(),
                randomLicense(), false, true, null);
    }
}

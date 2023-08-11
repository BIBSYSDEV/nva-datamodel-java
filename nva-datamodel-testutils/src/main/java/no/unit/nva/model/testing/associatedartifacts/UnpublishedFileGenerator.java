package no.unit.nva.model.testing.associatedartifacts;

import no.unit.nva.model.associatedartifacts.file.UnpublishedFile;

import java.util.UUID;
import nva.commons.core.JacocoGenerated;

import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static no.unit.nva.testutils.RandomDataGenerator.randomUri;
@JacocoGenerated
public final class UnpublishedFileGenerator {

    private UnpublishedFileGenerator() {
        // NO-OP
    }

    public static UnpublishedFile random() {
        return new UnpublishedFile(UUID.randomUUID(), randomString(), randomString(), randomInteger().longValue(),
        randomUri(), false, true, null);
    }
}

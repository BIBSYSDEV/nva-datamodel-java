package no.unit.nva.model.testing.associatedartifacts;

import no.unit.nva.model.associatedartifacts.file.UnpublishedFile;

import java.util.UUID;

import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;

public final class UnpublishedFileGenerator {

    private UnpublishedFileGenerator() {
        // NO-OP
    }

    public static UnpublishedFile random() {
        return new UnpublishedFile(UUID.randomUUID(), randomString(), randomString(), randomInteger().longValue(),
        LicenseGenerator.random(), false, true, null);
    }
}

package no.unit.nva.model.associatedartifacts;

import no.unit.nva.model.associatedartifacts.file.UnpublishedFile;

import java.util.UUID;

import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;

public final class UnpublishedFileGenerator extends AssociatedFileGenerator {

    private UnpublishedFileGenerator() {
        //NO-OP
    }

    public static UnpublishedFile random() {
        return new UnpublishedFile(UUID.randomUUID(), randomString(), randomString(), randomInteger().longValue(),
        randomLicense(), false, true, null);
    }
}

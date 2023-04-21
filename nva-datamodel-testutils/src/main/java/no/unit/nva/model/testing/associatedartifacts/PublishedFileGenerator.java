package no.unit.nva.model.testing.associatedartifacts;

import static no.unit.nva.testutils.RandomDataGenerator.randomInstant;
import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import java.util.UUID;
import no.unit.nva.model.Approver;
import no.unit.nva.model.associatedartifacts.file.PublishedFile;

public final class PublishedFileGenerator {

    private PublishedFileGenerator() {
        // NO-OP
    }

    public static PublishedFile random() {
        return new PublishedFile(UUID.randomUUID(), randomString(), randomString(), randomInteger().longValue(),
                                 LicenseGenerator.random(), false, true, null, randomApprover(),
                                 randomInstant());
    }

    private static Approver randomApprover() {
        return new Approver(randomString());
    }
}

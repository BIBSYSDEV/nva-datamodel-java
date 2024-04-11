package no.unit.nva.model.testing.associatedartifacts;

import no.unit.nva.model.Username;
import no.unit.nva.model.associatedartifacts.file.UnpublishedFile;

import java.util.UUID;
import no.unit.nva.model.testing.associatedartifacts.util.RightsRetentionStrategyGenerator;

import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static no.unit.nva.testutils.RandomDataGenerator.randomUri;

public final class UnpublishedFileGenerator {

    private UnpublishedFileGenerator() {
        // NO-OP
    }

    public static UnpublishedFile random() {
        return new UnpublishedFile(UUID.randomUUID(), randomString(), randomString(), randomInteger().longValue(),
                                   randomUri(), false, true, null,
                                   RightsRetentionStrategyGenerator.randomRightsRetentionStrategy(), randomString(),
                                   randomUsername());
    }

    private static Username randomUsername() {
        return new Username(randomString());
    }
}

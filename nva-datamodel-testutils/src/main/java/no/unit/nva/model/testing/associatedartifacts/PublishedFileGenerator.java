package no.unit.nva.model.testing.associatedartifacts;

import static no.unit.nva.testutils.RandomDataGenerator.randomInstant;
import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static no.unit.nva.testutils.RandomDataGenerator.randomUri;
import java.util.Random;
import java.util.UUID;
import no.unit.nva.model.Username;
import no.unit.nva.model.associatedartifacts.file.PublisherVersion;
import no.unit.nva.model.associatedartifacts.file.UploadDetails;
import no.unit.nva.model.associatedartifacts.file.PublishedFile;
import no.unit.nva.model.testing.associatedartifacts.util.RightsRetentionStrategyGenerator;

public final class PublishedFileGenerator {

    private PublishedFileGenerator() {
        // NO-OP
    }

    public static PublishedFile random() {
        return new PublishedFile(UUID.randomUUID(), randomString(), randomString(), randomInteger().longValue(),
                                 randomUri(), false, randomPublisherVersion(), null,
                                 RightsRetentionStrategyGenerator.randomRightsRetentionStrategy(), randomString(),
                                 randomInstant(), randomInserted());
    }

    private static PublisherVersion randomPublisherVersion() {
        return PublisherVersion.values()[new Random().nextInt(PublisherVersion.values().length)];
    }

    private static UploadDetails randomInserted() {
        return new UploadDetails(randomUsername(), randomInstant());
    }

    private static Username randomUsername() {
        return new Username(randomString());
    }
}

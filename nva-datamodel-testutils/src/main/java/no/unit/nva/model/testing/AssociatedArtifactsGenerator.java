package no.unit.nva.model.testing;

import static no.unit.nva.model.testing.RandomUtils.randomLabels;
import static no.unit.nva.testutils.RandomDataGenerator.randomBoolean;
import static no.unit.nva.testutils.RandomDataGenerator.randomInstant;
import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static no.unit.nva.testutils.RandomDataGenerator.randomUri;
import java.util.List;
import java.util.UUID;
import no.unit.nva.model.associatedartifacts.AssociatedArtifact;
import no.unit.nva.model.associatedartifacts.AssociatedArtifactList;
import no.unit.nva.model.associatedartifacts.AssociatedLink;
import no.unit.nva.model.associatedartifacts.file.File;
import no.unit.nva.model.associatedartifacts.file.License;
import no.unit.nva.model.associatedartifacts.file.PublishedFile;
import nva.commons.core.JacocoGenerated;

@JacocoGenerated
public final class AssociatedArtifactsGenerator {
    
    private static final boolean NOT_ADMINISTRATIVE_AGREEMENT = false;
    
    private AssociatedArtifactsGenerator() {
        // NO-OP
    }

    public static List<AssociatedArtifact> randomAssociatedArtifacts() {
        return new AssociatedArtifactList(randomFile(),randomAssociatedLink());
    }

    public static File randomFile() {
        return new PublishedFile(UUID.randomUUID(), randomString(),
                randomString(), randomInteger().longValue(), randomLicense(), NOT_ADMINISTRATIVE_AGREEMENT,
                randomBoolean(), randomInstant());
    }

    public static AssociatedLink randomAssociatedLink() {
        return new AssociatedLink(randomUri(), randomString(), randomString());
    }

    private static License randomLicense() {
        return new License.Builder()
                   .withIdentifier(randomLicenseIdentifier())
                   .withLabels(randomLabels())
                   .withLink(randomUri())
                   .build();
    }

    private static String randomLicenseIdentifier() {
        return randomString();
    }
}

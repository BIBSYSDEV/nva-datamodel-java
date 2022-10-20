package no.unit.nva.model.testing;

import static no.unit.nva.model.testing.RandomUtils.randomLabels;
import static no.unit.nva.testutils.RandomDataGenerator.randomBoolean;
import static no.unit.nva.testutils.RandomDataGenerator.randomInstant;
import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static no.unit.nva.testutils.RandomDataGenerator.randomUri;
import java.util.List;
import java.util.UUID;
import no.unit.nva.file.model.FileType;
import no.unit.nva.file.model.License;
import no.unit.nva.model.associatedartifacts.AssociatedArtifact;
import no.unit.nva.model.associatedartifacts.AssociatedFile;
import no.unit.nva.model.associatedartifacts.AssociatedLink;
import nva.commons.core.JacocoGenerated;

@JacocoGenerated
public final class AssociatedArtifactsGenerator {

    private AssociatedArtifactsGenerator() {
        // NO-OP
    }

    public static List<AssociatedArtifact> randomAssociatedArtifacts() {
        return List.of(randomAssociatedArtifact());
    }

    public static AssociatedFile randomAssociatedArtifact() {
        return new AssociatedFile(FileType.PUBLISHED_FILE.getValue(), UUID.randomUUID(), randomString(),
                randomString(), randomInteger().longValue(), randomLicense(), randomBoolean(),
                randomBoolean(), randomInstant());
    }

    private static AssociatedLink randomAssociatedLink() {
        return new AssociatedLink(randomUri(), randomString(), randomString(), null);
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

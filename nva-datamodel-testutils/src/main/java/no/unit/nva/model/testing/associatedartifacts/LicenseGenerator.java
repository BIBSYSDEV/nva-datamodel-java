package no.unit.nva.model.testing.associatedartifacts;

import no.unit.nva.model.associatedartifacts.file.License;

import static no.unit.nva.model.testing.RandomUtils.randomLabels;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static no.unit.nva.testutils.RandomDataGenerator.randomUri;

public final class LicenseGenerator {

    private LicenseGenerator() {
        // NO-OP
    }

    public static License random() {
        return new License.Builder()
                .withIdentifier(randomString())
                .withLabels(randomLabels())
                .withLink(randomUri())
                .build();
    }
}

package no.unit.nva.model.associatedartifacts;

import no.unit.nva.model.associatedartifacts.file.License;

import static no.unit.nva.model.testing.RandomUtils.randomLabels;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static no.unit.nva.testutils.RandomDataGenerator.randomUri;

public class AssociatedFileGenerator {

    public AssociatedFileGenerator() {
    }

    static License randomLicense() {
        return new License.Builder()
                .withIdentifier(randomString())
                .withLabels(randomLabels())
                .withLink(randomUri())
                .build();
    }
}

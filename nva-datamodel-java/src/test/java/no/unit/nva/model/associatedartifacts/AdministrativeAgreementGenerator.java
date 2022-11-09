package no.unit.nva.model.associatedartifacts;

import no.unit.nva.model.associatedartifacts.file.AdministrativeAgreement;

import java.util.UUID;

import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;

public final class AdministrativeAgreementGenerator extends AssociatedFileGenerator {

    private AdministrativeAgreementGenerator() {
        // NO-OP
    }

    public static AdministrativeAgreement random() {
        return new AdministrativeAgreement(UUID.randomUUID(), randomString(), randomString(),
                randomInteger().longValue(), randomLicense(), true, false, null);
    }
}

package no.unit.nva.model.testing.associatedartifacts;

import no.unit.nva.model.associatedartifacts.file.AdministrativeAgreement;

import java.util.UUID;

import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static no.unit.nva.testutils.RandomDataGenerator.randomUri;

public final class AdministrativeAgreementGenerator {

    private AdministrativeAgreementGenerator() {
        // NO-OP
    }

    public static AdministrativeAgreement random() {
        return new AdministrativeAgreement(UUID.randomUUID(), randomString(), randomString(),
                randomInteger().longValue(), randomUri(), true, false, null);
    }
}

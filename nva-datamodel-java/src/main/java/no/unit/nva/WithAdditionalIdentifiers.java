package no.unit.nva;

import java.util.Set;
import no.unit.nva.model.AdditionalIdentifier;

public interface WithAdditionalIdentifiers {

    Set<AdditionalIdentifier> getAdditionalIdentifiers();

    void setAdditionalIdentifiers(Set<AdditionalIdentifier> additionalIdentifiers);
}

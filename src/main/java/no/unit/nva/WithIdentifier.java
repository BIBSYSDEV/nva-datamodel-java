package no.unit.nva;

import java.util.UUID;

public interface WithIdentifier extends PublicationBase {

    UUID getIdentifier();

    void setIdentifier(UUID identifier);

}

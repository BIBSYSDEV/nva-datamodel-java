package no.unit.nva.model.contexttypes;

import java.net.URI;

public interface LinkedContext extends PublicationContext {

    URI getLinkedContext();

    void setLinkedContext(String linkedContext);
}

package no.unit.nva.model.contexttypes;

import java.net.URI;

/**
 * LinkedContexts are contexts that are expressed as a URI, allowing an existing (external) Publication
 * to be used as a PublicationContext. For example, a BookAnthology containing a Chapter.
 */
public interface LinkedContext extends PublicationContext {

    URI getLinkedContext();

    void setLinkedContext(String linkedContext);
}

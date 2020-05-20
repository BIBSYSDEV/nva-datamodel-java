package no.unit.nva;

import java.net.URI;
import java.time.Instant;
import java.util.UUID;
import no.unit.nva.model.DoiRequest;
import no.unit.nva.model.Organization;
import no.unit.nva.model.PublicationStatus;

public interface WithInternal extends PublicationBase {

    Instant getCreatedDate();

    void setCreatedDate(Instant createdDate);

    PublicationStatus getStatus();

    void setStatus(PublicationStatus status);

    URI getHandle();

    void setHandle(URI handle);

    Instant getPublishedDate();

    void setPublishedDate(Instant publishedDate);

    Instant getModifiedDate();

    void setModifiedDate(Instant modifiedDate);

    String getOwner();

    void setOwner(String owner);

    Instant getIndexedDate();

    void setIndexedDate(Instant indexedDate);

    UUID getIdentifier();

    void setIdentifier(UUID identifier);

    URI getLink();

    void setLink(URI link);

    Organization getPublisher();

    void setPublisher(Organization publisher);

    URI getDoi();

    void setDoi(URI doi);

    DoiRequest getDoiRequest();

    void setDoiRequest(DoiRequest doiRequest);

}

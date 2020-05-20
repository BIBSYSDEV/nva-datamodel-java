package no.unit.nva.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URI;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import no.unit.nva.WithContext;
import no.unit.nva.WithFile;
import no.unit.nva.WithIdentifier;
import no.unit.nva.WithInternal;
import no.unit.nva.WithMetadata;
import no.unit.nva.model.DoiRequest;
import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.FileSet;
import no.unit.nva.model.Organization;
import no.unit.nva.model.PublicationStatus;
import no.unit.nva.model.ResearchProject;
import nva.commons.utils.JacocoGenerated;

@SuppressWarnings("PMD.TooManyFields")
public class PublicationResponse implements WithIdentifier, WithInternal, WithMetadata, WithFile, WithContext {

    private UUID identifier;
    private PublicationStatus status;
    private String owner;
    private Organization publisher;
    private Instant createdDate;
    private Instant modifiedDate;
    private Instant publishedDate;
    private Instant indexedDate;
    private URI handle;
    private URI link;
    private EntityDescription entityDescription;
    private FileSet fileSet;
    private ResearchProject project;
    private URI doi;
    private DoiRequest doiRequest;
    @JsonProperty("@context")
    private JsonNode context;

    @Override
    public FileSet getFileSet() {
        return  fileSet;
    }

    @Override
    public void setFileSet(FileSet fileSet) {
        this.fileSet = fileSet;
    }

    @Override
    public UUID getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    @Override
    public Instant getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public PublicationStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(PublicationStatus status) {
        this.status = status;
    }

    @Override
    public URI getHandle() {
        return handle;
    }

    @Override
    public void setHandle(URI handle) {
        this.handle = handle;
    }

    @Override
    public Instant getPublishedDate() {
        return publishedDate;
    }

    @Override
    public void setPublishedDate(Instant publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public Instant getModifiedDate() {
        return modifiedDate;
    }

    @Override
    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public Instant getIndexedDate() {
        return indexedDate;
    }

    @Override
    public void setIndexedDate(Instant indexedDate) {
        this.indexedDate = indexedDate;
    }

    @Override
    public URI getLink() {
        return link;
    }

    @Override
    public void setLink(URI link) {
        this.link = link;
    }

    @Override
    public Organization getPublisher() {
        return publisher;
    }

    @Override
    public void setPublisher(Organization publisher) {
        this.publisher = publisher;
    }

    @Override
    public ResearchProject getProject() {
        return project;
    }

    @Override
    public void setProject(ResearchProject project) {
        this.project = project;
    }

    @Override
    public EntityDescription getEntityDescription() {
        return entityDescription;
    }

    @Override
    public void setEntityDescription(EntityDescription entityDescription) {
        this.entityDescription = entityDescription;
    }

    @Override
    public URI getDoi() {
        return doi;
    }

    @Override
    public void setDoi(URI doi) {
        this.doi = doi;
    }

    @Override
    public DoiRequest getDoiRequest() {
        return doiRequest;
    }

    @Override
    public void setDoiRequest(DoiRequest doiRequest) {
        this.doiRequest = doiRequest;
    }

    @Override
    public JsonNode getContext() {
        return context;
    }

    @Override
    public void setContext(JsonNode context) {
        this.context = context;
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PublicationResponse that = (PublicationResponse) o;
        return Objects.equals(identifier, that.identifier)
            && status == that.status
            && Objects.equals(owner, that.owner)
            && Objects.equals(publisher, that.publisher)
            && Objects.equals(createdDate, that.createdDate)
            && Objects.equals(modifiedDate, that.modifiedDate)
            && Objects.equals(publishedDate, that.publishedDate)
            && Objects.equals(indexedDate, that.indexedDate)
            && Objects.equals(handle, that.handle)
            && Objects.equals(link, that.link)
            && Objects.equals(entityDescription, that.entityDescription)
            && Objects.equals(fileSet, that.fileSet)
            && Objects.equals(project, that.project)
            && Objects.equals(doi, that.doi)
            && Objects.equals(doiRequest, that.doiRequest)
            && Objects.equals(context, that.context);
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(identifier, status, owner, publisher, createdDate, modifiedDate, publishedDate, indexedDate,
            handle, link, entityDescription, fileSet, project, doi, doiRequest, context);
    }
}

package no.unit.nva.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URI;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import no.unit.nva.WithContext;
import no.unit.nva.WithFile;
import no.unit.nva.WithId;
import no.unit.nva.WithIdentifier;
import no.unit.nva.WithInternal;
import no.unit.nva.WithMetadata;
import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.DoiRequest;
import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.FileSet;
import no.unit.nva.model.Organization;
import no.unit.nva.model.Publication;
import no.unit.nva.model.PublicationStatus;
import no.unit.nva.model.ResearchProject;
import nva.commons.core.JacocoGenerated;

@SuppressWarnings("PMD.TooManyFields")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonTypeName("Publication")
public class PublicationResponse implements WithIdentifier, WithInternal, WithMetadata, WithFile, WithId, WithContext {

    private SortableIdentifier identifier;
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
    private URI doi;
    private DoiRequest doiRequest;
    private Boolean doiRequested;
    @JsonProperty("@context")
    private JsonNode context;
    private List<ResearchProject> projects;
    private List<URI> subjects;

    public static PublicationResponse fromPublication(Publication publication) {
        var response = new PublicationResponse();
        response.setIdentifier(publication.getIdentifier());
        response.setStatus(publication.getStatus());
        response.setOwner(publication.getOwner());
        response.setPublisher(publication.getPublisher());
        response.setCreatedDate(publication.getCreatedDate());
        response.setModifiedDate(publication.getModifiedDate());
        response.setPublishedDate(publication.getPublishedDate());
        response.setIndexedDate(publication.getIndexedDate());
        response.setHandle(publication.getHandle());
        response.setLink(publication.getLink());
        response.setEntityDescription(publication.getEntityDescription());
        response.setFileSet(publication.getFileSet());
        response.setDoi(publication.getDoi());
        response.setDoiRequest(publication.getDoiRequest());
        response.setProjects(publication.getProjects());
        response.setSubjects(publication.getSubjects());
        response.setContext(PublicationContext.getContext());
        return response;
    }

    @Override
    public FileSet getFileSet() {
        return fileSet;
    }

    @Override
    public void setFileSet(FileSet fileSet) {
        this.fileSet = fileSet;
    }

    @Override
    public SortableIdentifier getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(SortableIdentifier identifier) {
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
        this.doiRequested = Objects.nonNull(doiRequest);
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
    public List<ResearchProject> getProjects() {
        return Objects.nonNull(projects) ? projects : Collections.emptyList();
    }

    @Override
    public void setProjects(List<ResearchProject> projects) {
        this.projects = projects;
    }

    @Override
    public List<URI> getSubjects() {
        return Objects.nonNull(subjects) ? subjects : Collections.emptyList();
    }

    @Override
    public void setSubjects(List<URI> subjects) {
        this.subjects = subjects;
    }

    public Boolean getDoiRequested() {
        return doiRequested;
    }

    @Override
    public JsonNode getContext() {
        return context;
    }

    @Override
    public void setContext(JsonNode context) {
        this.context = context;
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier(), getStatus(), getOwner(), getPublisher(), getCreatedDate(),
                            getModifiedDate(), getPublishedDate(), getIndexedDate(), getHandle(), getLink(),
                            getEntityDescription(), getFileSet(), getDoi(), getDoiRequest(), getDoiRequested(),
                            getContext(), getProjects(), getSubjects());
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PublicationResponse)) {
            return false;
        }
        PublicationResponse that = (PublicationResponse) o;
        return Objects.equals(getId(), that.getId())
               && Objects.equals(getIdentifier(), that.getIdentifier())
               && getStatus() == that.getStatus()
               && Objects.equals(getOwner(), that.getOwner())
               && Objects.equals(getPublisher(), that.getPublisher())
               && Objects.equals(getCreatedDate(), that.getCreatedDate())
               && Objects.equals(getModifiedDate(), that.getModifiedDate())
               && Objects.equals(getPublishedDate(), that.getPublishedDate())
               && Objects.equals(getIndexedDate(), that.getIndexedDate())
               && Objects.equals(getHandle(), that.getHandle())
               && Objects.equals(getLink(), that.getLink())
               && Objects.equals(getEntityDescription(), that.getEntityDescription())
               && Objects.equals(getFileSet(), that.getFileSet())
               && Objects.equals(getDoi(), that.getDoi())
               && Objects.equals(getDoiRequest(), that.getDoiRequest())
               && Objects.equals(getDoiRequested(), that.getDoiRequested())
               && Objects.equals(getContext(), that.getContext())
               && Objects.equals(getProjects(), that.getProjects())
               && Objects.equals(getSubjects(), that.getSubjects());
    }
}

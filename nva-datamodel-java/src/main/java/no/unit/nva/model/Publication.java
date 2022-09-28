package no.unit.nva.model;

import static java.util.Objects.hash;
import static java.util.Objects.nonNull;
import static nva.commons.core.attempt.Try.attempt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.bibsysdev.ResourcesBuildConfig;
import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import no.unit.nva.WithFile;
import no.unit.nva.WithIdentifier;
import no.unit.nva.WithInternal;
import no.unit.nva.WithMetadata;
import no.unit.nva.commons.json.JsonUtils;
import no.unit.nva.file.model.File;
import no.unit.nva.file.model.FileSet;
import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.exceptions.InvalidPublicationStatusTransitionException;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@SuppressWarnings({"PMD.ExcessivePublicCount", "PMD.TooManyFields", "PMD.GodClass"})
public class Publication
    implements WithIdentifier, WithInternal, WithFile, WithMetadata, WithCopy<Publication.Builder> {
    
    public static final Map<PublicationStatus, List<PublicationStatus>> validStatusTransitionsMap = Map.of(
        PublicationStatus.NEW, List.of(PublicationStatus.DRAFT),
        PublicationStatus.DRAFT, List.of(PublicationStatus.PUBLISHED, PublicationStatus.DRAFT_FOR_DELETION)
    );
    public static final String ERROR_MESSAGE_UPDATEDOIREQUEST_MISSING_DOIREQUEST =
        "You must initiate creation of a DoiRequest before you can update it.";
    private static final String MODEL_VERSION = ResourcesBuildConfig.RESOURCES_MODEL_VERSION;
    private SortableIdentifier identifier;
    private PublicationStatus status;
    private ResourceOwner resourceOwner;
    private Organization publisher;
    private Instant createdDate;
    private Instant modifiedDate;
    private Instant publishedDate;
    private Instant indexedDate;
    private URI handle;
    private URI doi;
    private URI link;
    private EntityDescription entityDescription;
    private FileSet fileSet;
    private List<ResearchProject> projects;
    private Set<AdditionalIdentifier> additionalIdentifiers;
    private List<URI> subjects;
    
    public Publication() {
    
    }
    
    public Set<AdditionalIdentifier> getAdditionalIdentifiers() {
        return Objects.nonNull(additionalIdentifiers) ? additionalIdentifiers : Collections.emptySet();
    }
    
    public void setAdditionalIdentifiers(Set<AdditionalIdentifier> additionalIdentifiers) {
        this.additionalIdentifiers = additionalIdentifiers;
    }
    
    @JsonProperty("owner")
    @Deprecated(since = "Resource owner was introduced. "
                        + "Should be removed when publisher is moved into resourceOwner"
                        + "and NP-9175 is fixed")
    public String getOwner() {
        return Optional.of(resourceOwner).map(ResourceOwner::getOwner).orElse(null);
    }
    
    public void setOwner() {
        // NO-OP
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
    public ResourceOwner getResourceOwner() {
        return resourceOwner;
    }
    
    @Override
    public void setResourceOwner(ResourceOwner resourceOwner) {
        this.resourceOwner = resourceOwner;
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
    public SortableIdentifier getIdentifier() {
        return identifier;
    }
    
    @Override
    public void setIdentifier(SortableIdentifier identifier) {
        this.identifier = identifier;
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
    
    @Override
    public FileSet getFileSet() {
        return fileSet;
    }
    
    @Override
    public void setFileSet(FileSet fileSet) {
        this.fileSet = fileSet;
    }
    
    @JsonProperty("modelVersion")
    public String getModelVersion() {
        return MODEL_VERSION;
    }
    
    @JsonProperty("modelVersion")
    public void setModelVersion() {
        //NO-OP;
    }
    
    @Override
    public Builder copy() {
        return new Builder()
            .withIdentifier(getIdentifier())
            .withStatus(getStatus())
            .withResourceOwner(getResourceOwner())
            .withPublisher(getPublisher())
            .withCreatedDate(getCreatedDate())
            .withModifiedDate(getModifiedDate())
            .withPublishedDate(getPublishedDate())
            .withIndexedDate(getIndexedDate())
            .withHandle(getHandle())
            .withDoi(getDoi())
            .withLink(getLink())
            .withEntityDescription(getEntityDescription())
            .withFileSet(getFileSet())
            .withProjects(getProjects())
            .withAdditionalIdentifiers(getAdditionalIdentifiers())
            .withSubjects(getSubjects());
    }
    
    /**
     * Updates the status of the publication using rules for valid status transitions.
     *
     * @param nextStatus the status to update to
     * @throws InvalidPublicationStatusTransitionException if the status transition is not allowed
     */
    public void updateStatus(PublicationStatus nextStatus) throws InvalidPublicationStatusTransitionException {
        verifyStatusTransition(nextStatus);
        setStatus(nextStatus);
    }
    
    @JacocoGenerated
    @Override
    public int hashCode() {
        return hash(getIdentifier(), getStatus(), getPublisher(), getCreatedDate(), getModifiedDate(),
            getPublishedDate(), getIndexedDate(), getHandle(), getDoi(), getLink(),
            getEntityDescription(), getFileSet(), getProjects(), getAdditionalIdentifiers(), getSubjects());
    }
    
    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Publication)) {
            return false;
        }
        Publication that = (Publication) o;
        boolean firstHalf = Objects.equals(getIdentifier(), that.getIdentifier())
                            && getStatus() == that.getStatus()
                            && Objects.equals(getResourceOwner(), that.getResourceOwner())
                            && Objects.equals(getPublisher(), that.getPublisher())
                            && Objects.equals(getCreatedDate(), that.getCreatedDate())
                            && Objects.equals(getModifiedDate(), that.getModifiedDate())
                            && Objects.equals(getPublishedDate(), that.getPublishedDate());
        boolean secondHalf = Objects.equals(getIndexedDate(), that.getIndexedDate())
                             && Objects.equals(getHandle(), that.getHandle())
                             && Objects.equals(getDoi(), that.getDoi())
                             && Objects.equals(getLink(), that.getLink())
                             && Objects.equals(getEntityDescription(), that.getEntityDescription())
                             && Objects.equals(getFileSet(), that.getFileSet())
                             && Objects.equals(getProjects(), that.getProjects())
                             && Objects.equals(getAdditionalIdentifiers(), that.getAdditionalIdentifiers())
                             && Objects.equals(getSubjects(), that.getSubjects());
        return firstHalf && secondHalf;
    }
    
    @Override
    public String toString() {
        return attempt(() -> JsonUtils.dtoObjectMapper.writeValueAsString(this)).orElseThrow();
    }
    
    private void verifyStatusTransition(PublicationStatus nextStatus)
        throws InvalidPublicationStatusTransitionException {
        final PublicationStatus currentStatus = getStatus();
        if (!validStatusTransitionsMap.get(currentStatus).contains(nextStatus)) {
            throw new InvalidPublicationStatusTransitionException(currentStatus, nextStatus);
        }
    }

    public List<File> getAssociatedArtifacts() {
        var files = Optional.of(getFileSet())
                .orElse(new FileSet(Collections.emptyList())).getFiles();
        return new ArrayList<>(files);
    }

    public static final class Builder {
        
        private final Publication publication;
        
        public Builder() {
            publication = new Publication();
        }
        
        public Builder withIdentifier(SortableIdentifier identifier) {
            publication.setIdentifier(identifier);
            return this;
        }
        
        public Builder withStatus(PublicationStatus status) {
            publication.setStatus(status);
            return this;
        }
        
        public Builder withPublisher(Organization publisher) {
            publication.setPublisher(publisher);
            return this;
        }
        
        public Builder withCreatedDate(Instant createdDate) {
            publication.setCreatedDate(createdDate);
            return this;
        }
        
        public Builder withModifiedDate(Instant modifiedDate) {
            publication.setModifiedDate(modifiedDate);
            return this;
        }
        
        public Builder withPublishedDate(Instant publishedDate) {
            publication.setPublishedDate(publishedDate);
            return this;
        }
        
        public Builder withIndexedDate(Instant indexedDate) {
            publication.setIndexedDate(indexedDate);
            return this;
        }
        
        public Builder withHandle(URI handle) {
            publication.setHandle(handle);
            return this;
        }
        
        public Builder withDoi(URI doi) {
            publication.setDoi(doi);
            return this;
        }
        
        public Builder withLink(URI link) {
            publication.setLink(link);
            return this;
        }
        
        public Builder withEntityDescription(EntityDescription entityDescription) {
            publication.setEntityDescription(entityDescription);
            return this;
        }
        
        public Builder withFileSet(FileSet fileSet) {
            publication.setFileSet(fileSet);
            return this;
        }
        
        public Builder withProjects(List<ResearchProject> projects) {
            publication.setProjects(projects);
            return this;
        }
        
        public Builder withAdditionalIdentifiers(Set<AdditionalIdentifier> additionalIdentifiers) {
            publication.setAdditionalIdentifiers(additionalIdentifiers);
            return this;
        }
        
        public Builder withSubjects(List<URI> subjects) {
            publication.setSubjects(subjects);
            return this;
        }
        
        public Builder withResourceOwner(ResourceOwner randomResourceOwner) {
            publication.setResourceOwner(randomResourceOwner);
            return this;
        }
        
        public Publication build() {
            return publication;
        }
    }
}

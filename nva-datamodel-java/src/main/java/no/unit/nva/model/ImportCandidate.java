package no.unit.nva.model;

import static java.util.Objects.hash;
import static java.util.Objects.nonNull;
import static nva.commons.core.attempt.Try.attempt;
import static nva.commons.core.ioutils.IoUtils.stringFromResources;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.bibsysdev.ResourcesBuildConfig;
import java.net.URI;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import no.unit.nva.WithAssociatedArtifact;
import no.unit.nva.WithIdentifier;
import no.unit.nva.WithMetadata;
import no.unit.nva.commons.json.JsonUtils;
import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.associatedartifacts.AssociatedArtifact;
import no.unit.nva.model.associatedartifacts.AssociatedArtifactList;
import no.unit.nva.model.funding.Funding;
import no.unit.nva.model.funding.FundingList;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@SuppressWarnings({"PMD.ExcessivePublicCount", "PMD.TooManyFields", "PMD.GodClass"})
public class ImportCandidate
    implements WithIdentifier, WithAssociatedArtifact, WithMetadata {

    private static final String MODEL_VERSION = ResourcesBuildConfig.RESOURCES_MODEL_VERSION;
    private SortableIdentifier identifier;
    private ImportStatus status;
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
    private List<ResearchProject> projects;
    private FundingList fundings;
    private Set<AdditionalIdentifier> additionalIdentifiers;
    private List<URI> subjects;
    private AssociatedArtifactList associatedArtifacts;
    private String rightsHolder;

    public ImportCandidate() {
        // Default constructor, use setters.
    }

    public Set<AdditionalIdentifier> getAdditionalIdentifiers() {
        return nonNull(additionalIdentifiers) ? additionalIdentifiers : Collections.emptySet();
    }

    public void setAdditionalIdentifiers(Set<AdditionalIdentifier> additionalIdentifiers) {
        this.additionalIdentifiers = additionalIdentifiers;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public ImportStatus getStatus() {
        return status;
    }

    public void setStatus(ImportStatus status) {
        this.status = status;
    }

    public URI getHandle() {
        return handle;
    }

    public void setHandle(URI handle) {
        this.handle = handle;
    }

    public Instant getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Instant publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public ResourceOwner getResourceOwner() {
        return resourceOwner;
    }

    public void setResourceOwner(ResourceOwner resourceOwner) {
        this.resourceOwner = resourceOwner;
    }

    public Instant getIndexedDate() {
        return indexedDate;
    }

    public void setIndexedDate(Instant indexedDate) {
        this.indexedDate = indexedDate;
    }

    public URI getLink() {
        return link;
    }

    public void setLink(URI link) {
        this.link = link;
    }

    public Organization getPublisher() {
        return publisher;
    }

    public void setPublisher(Organization publisher) {
        this.publisher = publisher;
    }

    public URI getDoi() {
        return doi;
    }

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
        return nonNull(projects) ? projects : Collections.emptyList();
    }

    @Override
    public void setProjects(List<ResearchProject> projects) {
        this.projects = projects;
    }

    @Override
    public List<URI> getSubjects() {
        return nonNull(subjects) ? subjects : Collections.emptyList();
    }

    @Override
    public void setSubjects(List<URI> subjects) {
        this.subjects = subjects;
    }

    @Override
    public List<Funding> getFundings() {
        return nonNull(fundings) ? fundings : Collections.emptyList();
    }

    @Override
    public void setFundings(List<Funding> fundings) {
        this.fundings = new FundingList(fundings);
    }

    @Override
    public String getRightsHolder() {
        return rightsHolder;
    }

    @Override
    public void setRightsHolder(String rightsHolder) {
        this.rightsHolder = rightsHolder;
    }

    @JsonProperty("modelVersion")
    public String getModelVersion() {
        return MODEL_VERSION;
    }

    @JsonProperty("modelVersion")
    public void setModelVersion() {
        // NO-OP
    }

    @Override
    public AssociatedArtifactList getAssociatedArtifacts() {
        return nonNull(associatedArtifacts)
                   ? associatedArtifacts
                   : AssociatedArtifactList.empty();
    }

    @Override
    public void setAssociatedArtifacts(AssociatedArtifactList associatedArtifacts) {
        this.associatedArtifacts = associatedArtifacts;
    }

    public ImportCandidate importCandidate() {
        return new Builder()
                   .withIdentifier(getIdentifier())
                   .withStatus(ImportStatus.IMPORTED)
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
                   .withProjects(getProjects())
                   .withFundings(getFundings())
                   .withAdditionalIdentifiers(getAdditionalIdentifiers())
                   .withAssociatedArtifacts(getAssociatedArtifacts())
                   .withSubjects(getSubjects())
                   .withFundings(getFundings())
                   .withRightsHolder(getRightsHolder())
                   .build();
    }

    public Publication toPublication() {
        return new Publication.Builder()
                   .withIdentifier(getIdentifier())
                   .withStatus(PublicationStatus.PUBLISHED)
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
                   .withProjects(getProjects())
                   .withFundings(getFundings())
                   .withAdditionalIdentifiers(getAdditionalIdentifiers())
                   .withAssociatedArtifacts(getAssociatedArtifacts())
                   .withSubjects(getSubjects())
                   .withFundings(getFundings())
                   .withRightsHolder(getRightsHolder())
                   .build();
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return hash(getIdentifier(), getStatus(), getPublisher(), getCreatedDate(), getModifiedDate(),
                    getPublishedDate(), getIndexedDate(), getHandle(), getDoi(), getLink(),
                    getEntityDescription(), getProjects(), getFundings(), getAdditionalIdentifiers(), getSubjects(),
                    getAssociatedArtifacts(), getRightsHolder());
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ImportCandidate)) {
            return false;
        }
        ImportCandidate that = (ImportCandidate) o;
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
                             && Objects.equals(getAssociatedArtifacts(), that.getAssociatedArtifacts())
                             && Objects.equals(getProjects(), that.getProjects())
                             && Objects.equals(getFundings(), that.getFundings())
                             && Objects.equals(getAdditionalIdentifiers(), that.getAdditionalIdentifiers())
                             && Objects.equals(getSubjects(), that.getSubjects())
                             && Objects.equals(getRightsHolder(), that.getRightsHolder());
        return firstHalf && secondHalf;
    }

    @Override
    public String toString() {
        return attempt(() -> JsonUtils.dtoObjectMapper.writeValueAsString(this)).orElseThrow();
    }

    @JsonIgnore
    @Deprecated
    public String getJsonLdContext() {
        return stringFromResources(Path.of("publicationContextDeprecated.json"));
    }

    public static class Builder {

        private final ImportCandidate importCandidate;

        public Builder() {
            importCandidate = new ImportCandidate();
        }

        public Builder withIdentifier(SortableIdentifier identifier) {
            importCandidate.setIdentifier(identifier);
            return this;
        }

        public Builder withStatus(ImportStatus status) {
            importCandidate.setStatus(status);
            return this;
        }

        public Builder withPublisher(Organization publisher) {
            importCandidate.setPublisher(publisher);
            return this;
        }

        public Builder withCreatedDate(Instant createdDate) {
            importCandidate.setCreatedDate(createdDate);
            return this;
        }

        public Builder withModifiedDate(Instant modifiedDate) {
            importCandidate.setModifiedDate(modifiedDate);
            return this;
        }

        public Builder withPublishedDate(Instant publishedDate) {
            importCandidate.setPublishedDate(publishedDate);
            return this;
        }

        public Builder withIndexedDate(Instant indexedDate) {
            importCandidate.setIndexedDate(indexedDate);
            return this;
        }

        public Builder withHandle(URI handle) {
            importCandidate.setHandle(handle);
            return this;
        }

        public Builder withDoi(URI doi) {
            importCandidate.setDoi(doi);
            return this;
        }

        public Builder withLink(URI link) {
            importCandidate.setLink(link);
            return this;
        }

        public Builder withEntityDescription(EntityDescription entityDescription) {
            importCandidate.setEntityDescription(entityDescription);
            return this;
        }

        public Builder withAssociatedArtifacts(List<AssociatedArtifact> associatedArtifacts) {
            importCandidate.setAssociatedArtifacts(new AssociatedArtifactList(associatedArtifacts));
            return this;
        }

        public Builder withProjects(List<ResearchProject> projects) {
            importCandidate.setProjects(projects);
            return this;
        }

        public Builder withFundings(List<Funding> fundings) {
            importCandidate.setFundings(fundings);
            return this;
        }

        public Builder withAdditionalIdentifiers(Set<AdditionalIdentifier> additionalIdentifiers) {
            importCandidate.setAdditionalIdentifiers(additionalIdentifiers);
            return this;
        }

        public Builder withSubjects(List<URI> subjects) {
            importCandidate.setSubjects(subjects);
            return this;
        }

        public Builder withResourceOwner(ResourceOwner randomResourceOwner) {
            importCandidate.setResourceOwner(randomResourceOwner);
            return this;
        }

        public ImportCandidate build() {
            return importCandidate;
        }

        public Builder withRightsHolder(String rightsHolder) {
            this.importCandidate.setRightsHolder(rightsHolder);
            return this;
        }
    }
}

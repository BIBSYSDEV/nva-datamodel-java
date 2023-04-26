package no.unit.nva.model;

import static java.util.Objects.hash;
import static java.util.Objects.nonNull;
import static no.unit.nva.model.PublicationStatus.DRAFT_FOR_DELETION;
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
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import no.unit.nva.WithAssociatedArtifact;
import no.unit.nva.WithIdentifier;
import no.unit.nva.WithInternal;
import no.unit.nva.WithMetadata;
import no.unit.nva.commons.json.JsonUtils;
import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.associatedartifacts.AssociatedArtifact;
import no.unit.nva.model.associatedartifacts.AssociatedArtifactList;
import no.unit.nva.model.exceptions.InvalidPublicationStatusTransitionException;
import no.unit.nva.model.funding.Funding;
import no.unit.nva.model.funding.FundingList;
import nva.commons.core.JacocoGenerated;
import nva.commons.core.StringUtils;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@SuppressWarnings({"PMD.ExcessivePublicCount", "PMD.TooManyFields", "PMD.GodClass"})
public class Publication
    implements WithIdentifier, WithInternal, WithAssociatedArtifact, WithMetadata, WithCopy<Publication.Builder> {

    public static final Map<PublicationStatus, List<PublicationStatus>> validStatusTransitionsMap = Map.of(
        PublicationStatus.NEW, List.of(PublicationStatus.DRAFT),
        PublicationStatus.DRAFT, List.of(PublicationStatus.PUBLISHED, DRAFT_FOR_DELETION)
    );

    private static final String MODEL_VERSION = ResourcesBuildConfig.RESOURCES_MODEL_VERSION;
    private static final String BASE_URI = "__BASE_URI__";
    private static final String PUBLICATION_CONTEXT = stringFromResources(Path.of("publicationContext.json"));
    private static final String ONTOLOGY = stringFromResources(Path.of("publication-ontology.ttl"));

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
    private List<ResearchProject> projects;
    private FundingList fundings;
    private Set<AdditionalIdentifier> additionalIdentifiers;
    private List<URI> subjects;
    private AssociatedArtifactList associatedArtifacts;
    private String rightsHolder;

    public Publication() {
        // Default constructor, use setters.
    }

    public Set<AdditionalIdentifier> getAdditionalIdentifiers() {
        return nonNull(additionalIdentifiers) ? additionalIdentifiers : Collections.emptySet();
    }

    public void setAdditionalIdentifiers(Set<AdditionalIdentifier> additionalIdentifiers) {
        this.additionalIdentifiers = additionalIdentifiers;
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
                   .withProjects(getProjects())
                   .withFundings(getFundings())
                   .withAdditionalIdentifiers(getAdditionalIdentifiers())
                   .withAssociatedArtifacts(getAssociatedArtifacts())
                   .withSubjects(getSubjects())
                   .withFundings(getFundings())
                   .withRightsHolder(getRightsHolder());
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
                    getEntityDescription(), getProjects(), getFundings(), getAdditionalIdentifiers(), getSubjects(),
                    getAssociatedArtifacts(), getRightsHolder());
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

    @JsonIgnore
    public static String getJsonLdContext(URI baseUri) {
        return PUBLICATION_CONTEXT.replace(BASE_URI, baseUri.toString());
    }

    @JsonIgnore
    public static String getOntology(URI baseUri) {
        return ONTOLOGY.replace(BASE_URI, baseUri.toString());
    }

    @JsonIgnore
    public boolean isPublishable() {
        return !DRAFT_FOR_DELETION.equals(getStatus()) && hasMainTitle() && hasReferencedContent();
    }

    public boolean satisfiesFindableDoiRequirements() {
        return FindableDoiRequirementsValidator.meetsFindableDoiRequirements(this);
    }

    private void verifyStatusTransition(PublicationStatus nextStatus)
        throws InvalidPublicationStatusTransitionException {
        final PublicationStatus currentStatus = getStatus();
        if (!validStatusTransitionsMap.get(currentStatus).contains(nextStatus)) {
            throw new InvalidPublicationStatusTransitionException(currentStatus, nextStatus);
        }
    }

    private boolean hasReferencedContent() {
        return getAssociatedArtifacts().isPublishable() || hasOriginalDoi();
    }

    private boolean hasMainTitle() {
        return Optional.ofNullable(getEntityDescription())
                   .map(EntityDescription::getMainTitle)
                   .filter(string -> !StringUtils.isEmpty(string))
                   .isPresent();
    }

    private boolean hasOriginalDoi() {
        return Optional.ofNullable(getEntityDescription())
                   .map(EntityDescription::getReference)
                   .map(Reference::getDoi)
                   .isPresent();
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

        public Builder withAssociatedArtifacts(List<AssociatedArtifact> associatedArtifacts) {
            publication.setAssociatedArtifacts(new AssociatedArtifactList(associatedArtifacts));
            return this;
        }

        public Builder withProjects(List<ResearchProject> projects) {
            publication.setProjects(projects);
            return this;
        }

        public Builder withFundings(List<Funding> fundings) {
            publication.setFundings(fundings);
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

        public Builder withRightsHolder(String rightsHolder) {
            this.publication.setRightsHolder(rightsHolder);
            return this;
        }
    }
}

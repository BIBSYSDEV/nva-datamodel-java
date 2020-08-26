package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.WithFile;
import no.unit.nva.WithIdentifier;
import no.unit.nva.WithIndex;
import no.unit.nva.WithMetadata;
import nva.commons.utils.JacocoGenerated;

import java.net.URI;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@SuppressWarnings("PMD.ExcessivePublicCount")
public class Publication
        implements WithIdentifier, WithIndex, WithFile, WithMetadata, WithCopy<Publication.Builder> {

    private UUID identifier;
    private PublicationStatus status;
    private String owner;
    private Organization publisher;
    private Instant createdDate;
    private Instant modifiedDate;
    private Instant publishedDate;
    private Instant indexedDate;
    private URI handle;
    private URI doi;
    private DoiRequest doiRequest;
    private URI link;
    private EntityDescription entityDescription;
    private FileSet fileSet;
    private ResearchProject project;

    public Publication() {

    }

    private Publication(Builder builder) {
        setIdentifier(builder.identifier);
        setStatus(builder.status);
        setOwner(builder.owner);
        setPublisher(builder.publisher);
        setCreatedDate(builder.createdDate);
        setModifiedDate(builder.modifiedDate);
        setPublishedDate(builder.publishedDate);
        setIndexedDate(builder.indexedDate);
        setHandle(builder.handle);
        setDoi(builder.doi);
        setDoiRequest(builder.doiRequest);
        setLink(builder.link);
        setEntityDescription(builder.entityDescription);
        setFileSet(builder.fileSet);
        setProject(builder.project);
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
    public UUID getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
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
    public EntityDescription getEntityDescription() {
        return entityDescription;
    }

    @Override
    public void setEntityDescription(EntityDescription entityDescription) {
        this.entityDescription = entityDescription;
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
    public ResearchProject getProject() {
        return project;
    }

    @Override
    public void setProject(ResearchProject project) {
        this.project = project;
    }

    @Override
    public Publication.Builder copy() {
        return new Publication.Builder()
                .withIdentifier(getIdentifier())
                .withStatus(getStatus())
                .withOwner(getOwner())
                .withPublisher(getPublisher())
                .withCreatedDate(getCreatedDate())
                .withModifiedDate(getModifiedDate())
                .withPublishedDate(getPublishedDate())
                .withIndexedDate(getIndexedDate())
                .withHandle(getHandle())
                .withDoi(getDoi())
                .withDoiRequest(getDoiRequest())
                .withLink(getLink())
                .withEntityDescription(getEntityDescription())
                .withFileSet(getFileSet())
                .withProject(getProject());
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
        return Objects.equals(getIdentifier(), that.getIdentifier())
                && getStatus() == that.getStatus()
                && Objects.equals(getOwner(), that.getOwner())
                && Objects.equals(getPublisher(), that.getPublisher())
                && Objects.equals(getCreatedDate(), that.getCreatedDate())
                && Objects.equals(getModifiedDate(), that.getModifiedDate())
                && Objects.equals(getPublishedDate(), that.getPublishedDate())
                && Objects.equals(getIndexedDate(), that.getIndexedDate())
                && Objects.equals(getHandle(), that.getHandle())
                && Objects.equals(getDoi(), that.getDoi())
                && Objects.equals(getDoiRequest(), that.getDoiRequest())
                && Objects.equals(getLink(), that.getLink())
                && Objects.equals(getEntityDescription(), that.getEntityDescription())
                && Objects.equals(getFileSet(), that.getFileSet())
                && Objects.equals(getProject(), that.getProject());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier(),
                getStatus(),
                getOwner(),
                getPublisher(),
                getCreatedDate(),
                getModifiedDate(),
                getPublishedDate(),
                getIndexedDate(),
                getHandle(),
                getDoi(),
                getDoiRequest(),
                getLink(),
                getEntityDescription(),
                getFileSet(),
                getProject());
    }

    public static final class Builder {
        private UUID identifier;
        private PublicationStatus status;
        private String owner;
        private Organization publisher;
        private Instant createdDate;
        private Instant modifiedDate;
        private Instant publishedDate;
        private Instant indexedDate;
        private URI handle;
        private URI doi;
        private DoiRequest doiRequest;
        private URI link;
        private EntityDescription entityDescription;
        private FileSet fileSet;
        private ResearchProject project;

        public Builder() {
        }

        public Builder withIdentifier(UUID identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder withStatus(PublicationStatus status) {
            this.status = status;
            return this;
        }

        public Builder withOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public Builder withPublisher(Organization publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder withCreatedDate(Instant createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder withModifiedDate(Instant modifiedDate) {
            this.modifiedDate = modifiedDate;
            return this;
        }

        public Builder withPublishedDate(Instant publishedDate) {
            this.publishedDate = publishedDate;
            return this;
        }

        public Builder withIndexedDate(Instant indexedDate) {
            this.indexedDate = indexedDate;
            return this;
        }

        public Builder withHandle(URI handle) {
            this.handle = handle;
            return this;
        }

        public Builder withDoi(URI doi) {
            this.doi = doi;
            return this;
        }

        public Builder withDoiRequest(DoiRequest doiRequest) {
            this.doiRequest = doiRequest;
            return this;
        }

        public Builder withLink(URI link) {
            this.link = link;
            return this;
        }

        public Builder withEntityDescription(EntityDescription entityDescription) {
            this.entityDescription = entityDescription;
            return this;
        }

        public Builder withFileSet(FileSet fileSet) {
            this.fileSet = fileSet;
            return this;
        }

        public Builder withProject(ResearchProject project) {
            this.project = project;
            return this;
        }

        public Publication build() {
            return new Publication(this);
        }
    }
}

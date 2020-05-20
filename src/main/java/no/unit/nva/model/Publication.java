package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URI;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@SuppressWarnings("PMD.ExcessivePublicCount")
public class Publication {

    public static final String DYNAMODB_KEY_DELIMITER = "#";

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

    /**
     * Getter to create value used in DynamoDB indexing during serialization.
     *
     * @return publisherId
     */
    public String getPublisherId() {
        Organization publisher = Optional.ofNullable(getPublisher()).orElseThrow(
            () -> new IllegalStateException("Object publisher can not be null"));
        return Optional.ofNullable(publisher.getId()).orElseThrow(
            () -> new IllegalStateException("Property publisher.id can not be null")).toString();
    }

    /**
     * Getter to create value used in DynamoDB indexing during serialization.
     *
     * @return publisherOwnerDate
     */
    public String getPublisherOwnerDate() {
        return String.join(DYNAMODB_KEY_DELIMITER,
                getPublisherId(),
                Optional.ofNullable(getOwner()).orElseThrow(
                    () -> new IllegalStateException("Property owner can not be null")),
                Optional.ofNullable(getModifiedDate()).orElseThrow(
                    () -> new IllegalStateException("Property modifiedDate can not be null")).toString()
        );
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public PublicationStatus getStatus() {
        return status;
    }

    public void setStatus(PublicationStatus status) {
        this.status = status;
    }

    public URI getHandle() {
        return handle;
    }

    public void setHandle(URI handle) {
        this.handle = handle;
    }

    public URI getDoi() {
        return doi;
    }

    public void setDoi(URI doi) {
        this.doi = doi;
    }

    public DoiRequest getDoiRequest() {
        return doiRequest;
    }

    public void setDoiRequest(DoiRequest doiRequest) {
        this.doiRequest = doiRequest;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Instant getIndexedDate() {
        return indexedDate;
    }

    public void setIndexedDate(Instant indexedDate) {
        this.indexedDate = indexedDate;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
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

    public EntityDescription getEntityDescription() {
        return entityDescription;
    }

    public void setEntityDescription(EntityDescription entityDescription) {
        this.entityDescription = entityDescription;
    }

    public FileSet getFileSet() {
        return fileSet;
    }

    public void setFileSet(FileSet fileSet) {
        this.fileSet = fileSet;
    }

    public ResearchProject getProject() {
        return project;
    }

    public void setProject(ResearchProject project) {
        this.project = project;
    }

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

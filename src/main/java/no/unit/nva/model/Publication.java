package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URI;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Publication {

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
    private License license;
    private FileSet fileSet;

    public Publication() {

    }

    private Publication(Builder builder) {
        setCreatedDate(builder.createdDate);
        setStatus(builder.status);
        setHandle(builder.handle);
        setPublishedDate(builder.publishedDate);
        setModifiedDate(builder.modifiedDate);
        setOwner(builder.owner);
        setIndexedDate(builder.indexedDate);
        setIdentifier(builder.identifier);
        setLink(builder.link);
        setPublisher(builder.publisher);
        setEntityDescription(builder.entityDescription);
        setLicense(builder.license);
        setFileSet(builder.fileSet);
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
        return String.join("#",
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

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public FileSet getFileSet() {
        return fileSet;
    }

    public void setFileSet(FileSet fileSet) {
        this.fileSet = fileSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Publication that = (Publication) o;
        return Objects.equals(getCreatedDate(), that.getCreatedDate())
                && getStatus() == that.getStatus()
                && Objects.equals(getHandle(), that.getHandle())
                && Objects.equals(getPublishedDate(), that.getPublishedDate())
                && Objects.equals(getModifiedDate(), that.getModifiedDate())
                && Objects.equals(getOwner(), that.getOwner())
                && Objects.equals(getIndexedDate(), that.getIndexedDate())
                && Objects.equals(getIdentifier(), that.getIdentifier())
                && Objects.equals(getLink(), that.getLink())
                && Objects.equals(getPublisher(), that.getPublisher())
                && Objects.equals(getEntityDescription(), that.getEntityDescription())
                && Objects.equals(getLicense(), that.getLicense())
                && Objects.equals(getFileSet(), that.getFileSet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreatedDate(), getStatus(), getHandle(), getPublishedDate(), getModifiedDate(),
                getOwner(), getIndexedDate(), getIdentifier(), getLink(), getPublisher(), getEntityDescription(),
                getLicense(), getFileSet());
    }

    public static final class Builder {
        private Instant createdDate;
        private PublicationStatus status;
        private URI handle;
        private Instant publishedDate;
        private Instant modifiedDate;
        private String owner;
        private Instant indexedDate;
        private UUID identifier;
        private URI link;
        private Organization publisher;
        private EntityDescription entityDescription;
        private License license;
        private FileSet fileSet;

        public Builder() {
        }

        public Builder withCreatedDate(Instant createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder withStatus(PublicationStatus status) {
            this.status = status;
            return this;
        }

        public Builder withHandle(URI handle) {
            this.handle = handle;
            return this;
        }

        public Builder withPublishedDate(Instant publishedDate) {
            this.publishedDate = publishedDate;
            return this;
        }

        public Builder withModifiedDate(Instant modifiedDate) {
            this.modifiedDate = modifiedDate;
            return this;
        }

        public Builder withOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public Builder withIndexedDate(Instant indexedDate) {
            this.indexedDate = indexedDate;
            return this;
        }

        public Builder withIdentifier(UUID identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder withLink(URI link) {
            this.link = link;
            return this;
        }

        public Builder withPublisher(Organization publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder withEntityDescription(EntityDescription entityDescription) {
            this.entityDescription = entityDescription;
            return this;
        }

        public Builder withLicense(License license) {
            this.license = license;
            return this;
        }

        public Builder withFileSet(FileSet fileSet) {
            this.fileSet = fileSet;
            return this;
        }

        public Publication build() {
            return new Publication(this);
        }
    }
}

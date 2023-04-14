package no.unit.nva.model.associatedartifacts.file;

import static java.util.Objects.isNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import no.unit.nva.commons.json.JsonSerializable;
import no.unit.nva.model.associatedartifacts.AssociatedArtifact;
import nva.commons.core.JacocoGenerated;

/**
 * An object that represents the description of a file.
 */
@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = PublishedFile.TYPE, value = PublishedFile.class),
    @JsonSubTypes.Type(name = UnpublishedFile.TYPE, value = UnpublishedFile.class),
    @JsonSubTypes.Type(name = AdministrativeAgreement.TYPE, value = AdministrativeAgreement.class)
})
public abstract class File implements JsonSerializable, AssociatedArtifact {
    
    public static final String IDENTIFIER_FIELD = "identifier";
    public static final String NAME_FIELD = "name";
    public static final String MIME_TYPE_FIELD = "mimeType";
    public static final String SIZE_FIELD = "size";
    public static final String LICENSE_FIELD = "license";
    public static final String ADMINISTRATIVE_AGREEMENT_FIELD = "administrativeAgreement";
    public static final String PUBLISHER_AUTHORITY_FIELD = "publisherAuthority";
    public static final String EMBARGO_DATE_FIELD = "embargoDate";
    
    public static final String MISSING_LICENSE =
        "The file is not annotated as an administrative agreement and should have a license";
    
    @JsonProperty(IDENTIFIER_FIELD)
    private final UUID identifier;
    @JsonProperty(NAME_FIELD)
    private final String name;
    @JsonProperty(MIME_TYPE_FIELD)
    private final String mimeType;
    @JsonProperty(SIZE_FIELD)
    private final Long size;
    @JsonProperty(LICENSE_FIELD)
    private final License license;
    @JsonProperty(ADMINISTRATIVE_AGREEMENT_FIELD)
    private final boolean administrativeAgreement;
    @JsonProperty(PUBLISHER_AUTHORITY_FIELD)
    private final boolean publisherAuthority;
    @JsonProperty(EMBARGO_DATE_FIELD)
    private final Instant embargoDate;
    
    /**
     * Constructor for no.unit.nva.file.model.File objects. A file object is valid if it has a license or is explicitly
     * marked as an administrative agreement.
     *
     * @param identifier              A UUID that identifies the file in storage
     * @param name                    The original name of the file
     * @param mimeType                The mimetype of the file
     * @param size                    The size of the file
     * @param license                 The license for the file, may be null if and only if the file is an administrative
     *                                agreement
     * @param administrativeAgreement True if the file is an administrative agreement
     * @param publisherAuthority      True if the file owner has publisher authority
     * @param embargoDate             The date after which the file may be published
     */
    
    protected File(
        @JsonProperty(IDENTIFIER_FIELD) UUID identifier,
        @JsonProperty(NAME_FIELD) String name,
        @JsonProperty(MIME_TYPE_FIELD) String mimeType,
        @JsonProperty(SIZE_FIELD) Long size,
        @JsonProperty(LICENSE_FIELD) License license,
        @JsonProperty(ADMINISTRATIVE_AGREEMENT_FIELD) boolean administrativeAgreement,
        @JsonProperty(PUBLISHER_AUTHORITY_FIELD) boolean publisherAuthority,
        @JsonProperty(EMBARGO_DATE_FIELD) Instant embargoDate) {
        
        this.identifier = identifier;
        this.name = name;
        this.mimeType = mimeType;
        this.size = size;
        this.license = license;
        this.administrativeAgreement = administrativeAgreement;
        this.publisherAuthority = publisherAuthority;
        this.embargoDate = embargoDate;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    /**
     * Validate the file.
     */
    public void validate() {
        if (!administrativeAgreement && isNull(license)) {
            throw new MissingLicenseException(MISSING_LICENSE);
        }
    }
    
    public UUID getIdentifier() {
        return identifier;
    }
    
    public String getName() {
        return name;
    }
    
    public String getMimeType() {
        return mimeType;
    }
    
    public Long getSize() {
        return size;
    }
    
    public License getLicense() {
        return license;
    }
    
    public boolean isAdministrativeAgreement() {
        return administrativeAgreement;
    }
    
    public boolean isPublisherAuthority() {
        return publisherAuthority;
    }
    
    public Optional<Instant> getEmbargoDate() {
        return Optional.ofNullable(embargoDate);
    }
    
    public boolean fileDoesNotHaveActiveEmbargo() {
        return getEmbargoDate().map(date -> Instant.now().isAfter(date)).orElse(true);
    }
    
    public UnpublishedFile toUnpublishedFile() {
        return new UnpublishedFile(getIdentifier(), getName(), getMimeType(), getSize(), getLicense(),
            isAdministrativeAgreement(), isPublisherAuthority(), getEmbargoDate().orElse(null));
    }
    
    public PublishedFile toPublishedFile() {
        return new PublishedFile(getIdentifier(), getName(), getMimeType(), getSize(), getLicense(),
            isAdministrativeAgreement(), isPublisherAuthority(), getEmbargoDate().orElse(null));
    }
    
    public final AdministrativeAgreement toUnpublishableFile() {
        if (isAdministrativeAgreement()) {
            return new AdministrativeAgreement(getIdentifier(), getName(), getMimeType(), getSize(), getLicense(),
                isAdministrativeAgreement(), isPublisherAuthority(), getEmbargoDate().orElse(null));
        }
        throw new IllegalStateException("Cannot make unpublishable a non-administrative agreement");
    }
    
    public abstract boolean isVisibleForNonOwner();
    
    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getIdentifier(), getName(), getMimeType(), getSize(), getLicense(),
            isAdministrativeAgreement(),
            isPublisherAuthority(), getEmbargoDate());
    }
    
    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof File)) {
            return false;
        }
        File file = (File) o;
        return isAdministrativeAgreement() == file.isAdministrativeAgreement()
               && isPublisherAuthority() == file.isPublisherAuthority()
               && Objects.equals(getIdentifier(), file.getIdentifier())
               && Objects.equals(getName(), file.getName())
               && Objects.equals(getMimeType(), file.getMimeType())
               && Objects.equals(getSize(), file.getSize())
               && Objects.equals(getLicense(), file.getLicense())
               && Objects.equals(getEmbargoDate(), file.getEmbargoDate());
    }
    
    @Override
    public String toString() {
        return toJsonString();
    }
    
  
    
    public static final class Builder {
        
        private UUID identifier;
        private String name;
        private String mimeType;
        private Long size;
        private License license;
        private boolean administrativeAgreement;
        private boolean publisherAuthority;
        private Instant embargoDate;
        
        private Builder() {
        }
        
        public Builder withIdentifier(UUID identifier) {
            this.identifier = identifier;
            return this;
        }
        
        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder withMimeType(String mimeType) {
            this.mimeType = mimeType;
            return this;
        }
        
        public Builder withSize(Long size) {
            this.size = size;
            return this;
        }
        
        public Builder withLicense(License license) {
            this.license = license;
            return this;
        }
        
        public Builder withAdministrativeAgreement(boolean administrativeAgreement) {
            this.administrativeAgreement = administrativeAgreement;
            return this;
        }
        
        public Builder withPublisherAuthority(boolean publisherAuthority) {
            this.publisherAuthority = publisherAuthority;
            return this;
        }
        
        public Builder withEmbargoDate(Instant embargoDate) {
            this.embargoDate = embargoDate;
            return this;
        }
        
        public File buildPublishedFile() {
            return new PublishedFile(identifier, name, mimeType, size, license, administrativeAgreement,
                publisherAuthority,
                embargoDate);
        }
        
        public File buildUnpublishedFile() {
            return new UnpublishedFile(identifier, name, mimeType, size, license, administrativeAgreement,
                publisherAuthority,
                embargoDate);
        }
        
        public File buildUnpublishableFile() {
            return new AdministrativeAgreement(identifier, name, mimeType, size, license, administrativeAgreement,
                publisherAuthority,
                embargoDate);
        }
    }
}
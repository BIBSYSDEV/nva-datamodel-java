package no.unit.nva.model;

import static java.util.Objects.isNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import no.unit.nva.model.exceptions.MissingLicenseException;
import nva.commons.utils.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class File {

    public static final String MISSING_LICENSE =
            "The file is not annotated as an administrative agreement and should have a license";
    private UUID identifier;
    private String name;
    private String mimeType;
    private Long size;
    private License license;
    private boolean administrativeAgreement;
    private boolean publisherAuthority;
    private Instant embargoDate;

    /**
     * Constructor for File objects. A file object is valid if it has a license or is explicitly marked as
     * being an administrative agreement.
     *
     * @param identifier              A UUID that identifies the file in storage
     * @param name                    The original name of the file
     * @param mimeType                The mimetype of the file
     * @param size                    The size of the file
     * @param license                 The license for the file, may be null if and only if the file is an
     *                                administrative agreement
     * @param administrativeAgreement True if the file is an administrative agreement
     * @param publisherAuthority      True if the file owner has publisher authority
     * @param embargoDate             The date after which the file may be published
     */
    @JsonCreator
    public File(
            @JsonProperty("identifier") UUID identifier,
            @JsonProperty("name") String name,
            @JsonProperty("mimeType") String mimeType,
            @JsonProperty("size") Long size,
            @JsonProperty("license") License license,
            @JsonProperty("administrativeAgreement") boolean administrativeAgreement,
            @JsonProperty("publisherAuthority") boolean publisherAuthority,
            @JsonProperty("embargoDate") Instant embargoDate) {

        this.identifier = identifier;
        this.name = name;
        this.mimeType = mimeType;
        this.size = size;
        this.license = license;
        this.administrativeAgreement = administrativeAgreement;
        this.publisherAuthority = publisherAuthority;
        this.embargoDate = embargoDate;
    }

    /**
     * Validate the file.
     */
    public void validate() {
        if (!administrativeAgreement && isNull(license)) {
            throw new MissingLicenseException(MISSING_LICENSE);
        }
    }

    private File(Builder builder) {
        this(
                builder.identifier,
                builder.name,
                builder.mimeType,
                builder.size,
                builder.license,
                builder.administrativeAgreement,
                builder.publisherAuthority,
                builder.embargoDate
        );
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

    public Instant getEmbargoDate() {
        return embargoDate;
    }

    @JacocoGenerated
    @Override
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

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier(), getName(), getMimeType(), getSize(),
                getLicense(), isAdministrativeAgreement(), isPublisherAuthority(), getEmbargoDate());
    }

    public static final class Builder {
        public boolean administrativeAgreement;
        public boolean publisherAuthority;
        public Instant embargoDate;
        private UUID identifier;
        private String name;
        private String mimeType;
        private Long size;
        private License license;

        public Builder() {
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

        public File build() {
            return new File(this);
        }
    }
}

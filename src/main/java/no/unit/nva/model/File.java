package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class File {

    private UUID identifier;
    private String name;
    private String mimeType;
    private Long size;
    private License license;
    private boolean administrativeAgreement;
    private boolean publisher;
    private Instant embargoDate;

    public File() {

    }

    private File(Builder builder) {
        setIdentifier(builder.identifier);
        setName(builder.name);
        setMimeType(builder.mimeType);
        setSize(builder.size);
        setLicense(builder.license);
        setAdministrativeAgreement(builder.administrativeAgreement);
        setPublisher(builder.publisherAuthority);
        setEmbargoDate(builder.embargoDate);
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public boolean isAdministrativeAgreement() {
        return administrativeAgreement;
    }

    public void setAdministrativeAgreement(boolean administrativeAgreement) {
        this.administrativeAgreement = administrativeAgreement;
    }

    public boolean isPublisher() {
        return publisher;
    }

    public void setPublisher(boolean publisher) {
        this.publisher = publisher;
    }

    public Instant getEmbargoDate() {
        return embargoDate;
    }

    public void setEmbargoDate(Instant embargoDate) {
        this.embargoDate = embargoDate;
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
                && isPublisher() == file.isPublisher()
                && Objects.equals(getIdentifier(), file.getIdentifier())
                && Objects.equals(getName(), file.getName())
                && Objects.equals(getMimeType(), file.getMimeType())
                && Objects.equals(getSize(), file.getSize())
                && Objects.equals(getLicense(), file.getLicense())
                && Objects.equals(getEmbargoDate(), file.getEmbargoDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier(), getName(), getMimeType(), getSize(),
                getLicense(), isAdministrativeAgreement(), isPublisher(), getEmbargoDate());
    }
}

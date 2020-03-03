package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.UUID;

@SuppressWarnings("PMD.ShortClassName")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class File {

    private UUID identifier;
    private String name;
    private String mimeType;
    private Long size;
    private License license;

    public File() {

    }

    private File(Builder builder) {
        setIdentifier(builder.identifier);
        setName(builder.name);
        setMimeType(builder.mimeType);
        setSize(builder.size);
        setLicense(builder.license);
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

    public static final class Builder {
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

        public File build() {
            return new File(this);
        }
    }
}

package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URI;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Identity {

    private URI id;
    private String name;
    private NameType nameType;
    private String orcId;
    private String arpId;

    public Identity() {
    }

    private Identity(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setNameType(builder.nameType);
        setOrcId(builder.orcId);
        setArpId(builder.arpId);
    }

    public URI getId() {
        return id;
    }

    public void setId(URI id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NameType getNameType() {
        return nameType;
    }

    public void setNameType(NameType nameType) {
        this.nameType = nameType;
    }

    public String getOrcId() {
        return orcId;
    }

    public void setOrcId(String orcId) {
        this.orcId = orcId;
    }

    public String getArpId() {
        return arpId;
    }

    public void setArpId(String arpId) {
        this.arpId = arpId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Identity identity = (Identity) o;
        return Objects.equals(getId(), identity.getId())
                && Objects.equals(getName(), identity.getName())
                && getNameType() == identity.getNameType()
                && Objects.equals(getOrcId(), identity.getOrcId())
                && Objects.equals(getArpId(), identity.getArpId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getNameType(), getOrcId(), getArpId());
    }

    public static final class Builder {
        private URI id;
        private String name;
        private NameType nameType;
        private String orcId;
        private String arpId;

        public Builder() {
        }

        public Builder withId(URI id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withNameType(NameType nameType) {
            this.nameType = nameType;
            return this;
        }

        public Builder withOrcId(String orcId) {
            this.orcId = orcId;
            return this;
        }

        public Builder withArpId(String arpId) {
            this.arpId = arpId;
            return this;
        }

        public Identity build() {
            return new Identity(this);
        }
    }
}

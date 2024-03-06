package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Organization extends Corporation {

    @JsonProperty("id")
    private URI id;

    @JsonProperty("topLevelOrg")
    private URI topLevelOrg;

    public Organization() {
        super();
    }

    private Organization(Builder builder) {
        super();
        setId(builder.id);
        setTopLevelOrg(builder.topLevelOrg);
    }

    public URI getId() {
        return id;
    }

    public void setId(URI id) {
        this.id = id;
    }

    public URI getTopLevelOrg() {
        return topLevelOrg;
    }

    public void setTopLevelOrg(URI topLevelOrg) {
        this.topLevelOrg = topLevelOrg;
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTopLevelOrg());
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Organization that = (Organization) o;
        return Objects.equals(getId(), that.getId())
               && Objects.equals(getTopLevelOrg(), that.getTopLevelOrg());
    }

    public static final class Builder {

        private URI id;
        private URI topLevelOrg;

        public Builder() {
        }

        public Builder withId(URI id) {
            this.id = id;
            return this;
        }

        public Builder withTopLevelOrg(URI topLevelOrg) {
            this.topLevelOrg = topLevelOrg;
            return this;
        }

        public Organization build() {
            return new Organization(this);
        }
    }
}

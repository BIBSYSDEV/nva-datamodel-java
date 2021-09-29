package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Contributor {

    private final Identity identity;
    private final List<Organization> affiliations;
    private final Role role;
    private final Integer sequence;
    private final boolean correspondingAuthor;

    @JsonCreator
    public Contributor(@JsonProperty("identity") Identity identity,
                       @JsonProperty("affiliations") List<Organization> affiliations,
                       @JsonProperty("role") Role role,
                       @JsonProperty("sequence") Integer sequence,
                       @JsonProperty("correspondingAuthor") boolean correspondingAuthor) {
        this.identity = identity;
        this.affiliations = affiliations;
        this.role = role;
        this.sequence = sequence;
        this.correspondingAuthor = correspondingAuthor;
    }

    private Contributor(Builder builder) {
        this(
                builder.identity,
                builder.affiliations,
                builder.role,
                builder.sequence,
                builder.correspondingAuthor
        );
    }

    public Identity getIdentity() {
        return identity;
    }

    public List<Organization> getAffiliations() {
        return affiliations;
    }

    public Integer getSequence() {
        return sequence;
    }

    public Role getRole() {
        return role;
    }

    public boolean isCorrespondingAuthor() {
        return correspondingAuthor;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contributor)) {
            return false;
        }
        Contributor that = (Contributor) o;
        return isCorrespondingAuthor() == that.isCorrespondingAuthor()
                && Objects.equals(getIdentity(), that.getIdentity())
                && Objects.equals(getAffiliations(), that.getAffiliations())
                && getRole() == that.getRole()
                && Objects.equals(getSequence(), that.getSequence());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getIdentity(), getAffiliations(), getRole(), getSequence(), isCorrespondingAuthor());
    }

    public static final class Builder {
        private Identity identity;
        private List<Organization> affiliations;
        private Role role;
        private Integer sequence;
        private boolean correspondingAuthor;

        public Builder() {
        }

        public Builder withIdentity(Identity identity) {
            this.identity = identity;
            return this;
        }

        public Builder withAffiliations(List<Organization> affiliations) {
            this.affiliations = affiliations;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder withSequence(Integer sequence) {
            this.sequence = sequence;
            return this;
        }

        public Builder withCorrespondingAuthor(boolean correspondingAuthor) {
            this.correspondingAuthor = correspondingAuthor;
            return this;
        }

        public Contributor build() {
            return new Contributor(this);
        }
    }
}

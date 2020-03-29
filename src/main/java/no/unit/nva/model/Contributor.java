package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Contributor {

    private Identity identity;
    private List<Organization> affiliations;
    private Role role;
    private Integer sequence;
    private boolean correspondingAuthor;

    public Contributor() {

    }

    private Contributor(Builder builder) {
        setIdentity(builder.identity);
        setAffiliations(builder.affiliations);
        setRole(builder.role);
        setSequence(builder.sequence);
        setCorrespondingAuthor(builder.correspondingAuthor);
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public List<Organization> getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(List<Organization> affiliations) {
        this.affiliations = affiliations;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isCorrespondingAuthor() {
        return correspondingAuthor;
    }

    public void setCorrespondingAuthor(boolean correspondingAuthor) {
        this.correspondingAuthor = correspondingAuthor;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(getIdentity(),
                getAffiliations(),
                getRole(),
                getSequence(),
                isCorrespondingAuthor());
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

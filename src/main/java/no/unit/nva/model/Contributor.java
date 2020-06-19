package no.unit.nva.model;

import static java.util.Objects.isNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
import java.util.Objects;
import no.unit.nva.model.exceptions.MalformedContributorException;
import nva.commons.utils.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Contributor {

    public static final String CORRESPONDING_AUTHOR_EMAIL_MISSING =
            "The Contributor is corresponding author, but no email for correspondence is set";
    private final Identity identity;
    private final List<Organization> affiliations;
    private final Role role;
    private final Integer sequence;
    private final boolean correspondingAuthor;
    private final String email;

    /**
     * Constructor designed to ensure valid data in the object, since we can only have a corresponding author
     * with an email.
     *
     * @param identity            The identity of the contributor
     * @param affiliations        The affiliation of the contributor
     * @param role                The role that the contributor played
     * @param sequence            The order of the contributor in the contributors listing
     * @param correspondingAuthor Whether the contributor was a corresponding author
     * @param email               Contact email for contributor, required if the contributor was a corresponding author
     * @throws MalformedContributorException If the contributor is corresponding author, but no email is present
     */
    @JsonCreator
    public Contributor(@JsonProperty("identity") Identity identity,
                       @JsonProperty("affiliations") List<Organization> affiliations,
                       @JsonProperty("role") Role role,
                       @JsonProperty("sequence") Integer sequence,
                       @JsonProperty("correspondingAuthor") boolean correspondingAuthor,
                       @JsonProperty("email") String email) throws MalformedContributorException {
        if (isCorrespondAuthorWithoutEmail(correspondingAuthor, email)) {
            throw new MalformedContributorException(CORRESPONDING_AUTHOR_EMAIL_MISSING);
        }

        this.identity = identity;
        this.affiliations = affiliations;
        this.role = role;
        this.sequence = sequence;
        this.correspondingAuthor = correspondingAuthor;
        this.email = email;
    }

    private Contributor(Builder builder) throws MalformedContributorException {
        this(
                builder.identity,
                builder.affiliations,
                builder.role,
                builder.sequence,
                builder.correspondingAuthor,
                builder.email
        );
    }

    private boolean isCorrespondAuthorWithoutEmail(boolean correspondingAuthor, String email) {
        return correspondingAuthor && (isNull(email) || email.isBlank());
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

    public String getEmail() {
        return email;
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
                && Objects.equals(getSequence(), that.getSequence())
                && Objects.equals(getEmail(), that.getEmail());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getIdentity(),
                getAffiliations(),
                getRole(),
                getSequence(),
                isCorrespondingAuthor(),
                getEmail());
    }

    public static final class Builder {
        private Identity identity;
        private List<Organization> affiliations;
        private Role role;
        private Integer sequence;
        private boolean correspondingAuthor;
        private String email;

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

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Contributor build() throws MalformedContributorException {
            return new Contributor(this);
        }
    }
}

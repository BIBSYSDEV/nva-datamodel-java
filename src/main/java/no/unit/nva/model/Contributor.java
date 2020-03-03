package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class Contributor {

    private Identity identity;
    private List<Organization> affiliation;
    private Integer sequence;

    public Contributor() {

    }

    private Contributor(Builder builder) {
        setIdentity(builder.identity);
        setAffiliation(builder.affiliation);
        setSequence(builder.sequence);
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public List<Organization> getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(List<Organization> affiliation) {
        this.affiliation = affiliation;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }


    public static final class Builder {
        private Identity identity;
        private List<Organization> affiliation;
        private Integer sequence;

        public Builder() {
        }

        public Builder withIdentity(Identity identity) {
            this.identity = identity;
            return this;
        }

        public Builder withAffiliation(List<Organization> affiliation) {
            this.affiliation = affiliation;
            return this;
        }

        public Builder withSequence(Integer sequence) {
            this.sequence = sequence;
            return this;
        }

        public Contributor build() {
            return new Contributor(this);
        }
    }
}

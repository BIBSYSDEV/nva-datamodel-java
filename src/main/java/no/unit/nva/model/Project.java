package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Project {
    private URI id;
    private String name;
    private List<Grant> grants;
    private List<Approval> approvals;

    public Project() {

    }

    private Project(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setGrants(builder.grants);
        setApprovals(builder.approvals);
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

    public List<Grant> getGrants() {
        return grants;
    }

    public void setGrants(List<Grant> grants) {
        this.grants = grants;
    }

    public List<Approval> getApprovals() {
        return approvals;
    }

    public void setApprovals(List<Approval> approvals) {
        this.approvals = approvals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(getId(), project.getId())
                && Objects.equals(getName(), project.getName())
                && Objects.equals(getGrants(), project.getGrants())
                && Objects.equals(getApprovals(), project.getApprovals());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getGrants(), getApprovals());
    }

    public static final class Builder {
        private URI id;
        private String name;
        private List<Grant> grants;
        private List<Approval> approvals;

        public Builder() {
        }

        public Builder withId(URI val) {
            id = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withGrants(List<Grant> val) {
            grants = val;
            return this;
        }

        public Builder withApprovals(List<Approval> val) {
            approvals = val;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }
}

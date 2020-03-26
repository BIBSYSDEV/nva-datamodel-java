package no.unit.nva.model;

import java.net.URI;
import java.util.Objects;

public class Project {
    private URI id;
    private String name;

    public Project() {
    }

    private Project(Builder builder) {
        setId(builder.id);
        setName(builder.name);
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
                && Objects.equals(getName(), project.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    public static final class Builder {
        private URI id;
        private String name;

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

        public Project build() {
            return new Project(this);
        }
    }
}

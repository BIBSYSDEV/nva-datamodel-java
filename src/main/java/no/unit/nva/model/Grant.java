package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Grant {
    private String source;
    private String id;

    public Grant() {

    }

    private Grant(Builder builder) {
        setSource(builder.source);
        setId(builder.id);
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Grant)) {
            return false;
        }
        Grant grant = (Grant) o;
        return Objects.equals(getSource(), grant.getSource())
                && Objects.equals(getId(), grant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSource(), getId());
    }

    public static final class Builder {
        private String source;
        private String id;

        public Builder() {
        }

        public Builder withSource(String source) {
            this.source = source;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Grant build() {
            return new Grant(this);
        }
    }
}

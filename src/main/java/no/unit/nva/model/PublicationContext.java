package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class PublicationContext {
    private String name;
    private Level level;
    private boolean openAccess;
    private boolean peerReviewed;

    public PublicationContext() {
    }

    private PublicationContext(Builder builder) {
        setName(builder.name);
        setLevel(builder.level);
        setOpenAccess(builder.openAccess);
        setPeerReviewed(builder.peerReviewed);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevelFromInteger(Integer level) {
        this.level = Level.getLevel(level);
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean isOpenAccess() {
        return openAccess;
    }

    public void setOpenAccess(boolean openAccess) {
        this.openAccess = openAccess;
    }

    public boolean isPeerReviewed() {
        return peerReviewed;
    }

    public void setPeerReviewed(boolean peerReviewed) {
        this.peerReviewed = peerReviewed;
    }

    public static class Builder {
        private String name;
        private Level level;
        public boolean openAccess;
        public boolean peerReviewed;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withLevel(Level level) {
            this.level = level;
            return this;
        }

        public Builder withOpenAccess(boolean openAccess) {
            this.openAccess = openAccess;
            return this;
        }

        public Builder withPeerReviewed(boolean peerReviewed) {
            this.peerReviewed = peerReviewed;
            return this;
        }

        public PublicationContext build() {
            return new PublicationContext(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PublicationContext)) {
            return false;
        }
        PublicationContext that = (PublicationContext) o;
        return isOpenAccess() == that.isOpenAccess()
                && isPeerReviewed() == that.isPeerReviewed()
                && Objects.equals(getName(), that.getName())
                && getLevel() == that.getLevel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLevel(), isOpenAccess(), isPeerReviewed());
    }
}

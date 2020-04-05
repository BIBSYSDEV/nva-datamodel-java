package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidNpiLevelException;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class PublicationContext {
    private String title;
    private Level level;
    private boolean openAccess;
    private boolean peerReviewed;

    public PublicationContext() {
    }

    private PublicationContext(Builder builder) {
        setTitle(builder.title);
        setLevel(builder.level);
        setOpenAccess(builder.openAccess);
        setPeerReviewed(builder.peerReviewed);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevelFromInteger(Integer level) throws InvalidNpiLevelException {
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
        private String title;
        private Level level;
        public boolean openAccess;
        public boolean peerReviewed;

        public Builder withTitle(String title) {
            this.title = title;
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
                && Objects.equals(getTitle(), that.getTitle())
                && getLevel() == that.getLevel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getLevel(), isOpenAccess(), isPeerReviewed());
    }
}

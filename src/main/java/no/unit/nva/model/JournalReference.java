package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class JournalReference {
    private PublicationContext publicationContext;
    private String doi;
    private PublicationInstance publicationInstance;

    public JournalReference() {

    }

    private JournalReference(Builder builder) {
        setPublicationContext(builder.publicationContext);
        setDoi(builder.doi);
        setPublicationInstance(builder.publicationInstance);
    }

    public PublicationContext getPublicationContext() {
        return publicationContext;
    }

    public void setPublicationContext(PublicationContext publicationContext) {
        this.publicationContext = publicationContext;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public PublicationInstance getPublicationInstance() {
        return publicationInstance;
    }

    public void setPublicationInstance(PublicationInstance publicationInstance) {
        this.publicationInstance = publicationInstance;
    }

    public static final class Builder {
        public PublicationInstance publicationInstance;
        private PublicationContext publicationContext;
        private String doi;

        public Builder withPublishingContext(PublicationContext publicationContext) {
            this.publicationContext = publicationContext;
            return this;
        }

        public Builder withDoi(String doi) {
            this.doi = doi;
            return this;
        }

        public Builder withPublicationInstance(PublicationInstance publicationInstance) {
            this.publicationInstance = publicationInstance;
            return this;
        }

        public JournalReference build() {
            return new JournalReference(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JournalReference)) {
            return false;
        }
        JournalReference that = (JournalReference) o;
        return Objects.equals(getPublicationContext(), that.getPublicationContext())
                && Objects.equals(getDoi(), that.getDoi())
                && Objects.equals(getPublicationInstance(), that.getPublicationInstance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPublicationContext(), getDoi(), getPublicationInstance());
    }
}

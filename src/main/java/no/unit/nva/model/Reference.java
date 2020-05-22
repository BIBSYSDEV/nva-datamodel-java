package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.contexttypes.PublicationContext;
import no.unit.nva.model.instancetypes.PublicationInstance;
import nva.commons.utils.JacocoGenerated;

import java.net.URI;
import java.util.Objects;
import no.unit.nva.model.instancetypes.PublicationInstance;
import nva.commons.utils.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Reference {
    private PublicationContext publicationContext;
    private URI doi;
    private PublicationInstance publicationInstance;

    public Reference() {

    }

    private Reference(Builder builder) {
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

    public URI getDoi() {
        return doi;
    }

    public void setDoi(URI doi) {
        this.doi = doi;
    }

    public PublicationInstance getPublicationInstance() {
        return publicationInstance;
    }

    public void setPublicationInstance(PublicationInstance publicationInstance) {
        this.publicationInstance = publicationInstance;
    }

    public static final class Builder {
        private PublicationInstance publicationInstance;
        private PublicationContext publicationContext;
        private URI doi;

        public Builder withPublishingContext(PublicationContext publicationContext) {
            this.publicationContext = publicationContext;
            return this;
        }

        public Builder withDoi(URI doi) {
            this.doi = doi;
            return this;
        }

        public Builder withPublicationInstance(PublicationInstance publicationInstance) {
            this.publicationInstance = publicationInstance;
            return this;
        }

        public Reference build() {
            return new Reference(this);
        }
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reference)) {
            return false;
        }
        Reference that = (Reference) o;
        return Objects.equals(getPublicationContext(), that.getPublicationContext())
                && Objects.equals(getDoi(), that.getDoi())
                && Objects.equals(getPublicationInstance(), that.getPublicationInstance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPublicationContext(), getDoi(), getPublicationInstance());
    }
}

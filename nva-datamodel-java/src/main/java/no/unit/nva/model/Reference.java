package no.unit.nva.model;

import static java.util.Objects.nonNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import java.util.Objects;
import no.unit.nva.model.contexttypes.PublicationContext;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.Pages;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Reference {

    public static final String PUBLICATION_CONTEXT = "publicationContext";
    public static final String PUBLICATION_INSTANCE = "publicationInstance";
    public static final String DOI = "doi";
    private static final URI EMPTY_DOI = null;
    @JsonProperty(PUBLICATION_CONTEXT)
    private PublicationContext publicationContext;
    @JsonProperty(DOI)
    private URI doi;
    @JsonProperty(PUBLICATION_INSTANCE)
    private PublicationInstance<? extends Pages> publicationInstance;

    public Reference() {
    }

    private Reference(Builder builder) {
        setPublicationContext(builder.publicationContext);
        setDoi(builder.doi);
        setPublicationInstance(builder.publicationInstance);
    }

    public static Reference emptyReference() {
        return new Builder()
            .withDoi(EMPTY_DOI)
            .withPublishingContext(PublicationContext.emptyContext())
            .withPublicationInstance(PublicationInstance.emptyPublicationInstance())
            .build();
    }

    public static Reference nonEmptyOrDefault(Reference reference) {
        return nonNull(reference) ? reference : emptyReference();
    }

    public PublicationContext getPublicationContext() {
        return PublicationContext.nonEmptyOrDefault(publicationContext);
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

    public PublicationInstance<? extends Pages> getPublicationInstance() {
        return PublicationInstance.nonEmptyOrDefault(publicationInstance);
    }

    public void setPublicationInstance(PublicationInstance<? extends Pages> publicationInstance) {
        this.publicationInstance = publicationInstance;
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPublicationContext(), getDoi(), getPublicationInstance());
    }

    @JacocoGenerated
    @Override
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

    public static final class Builder {

        private PublicationInstance<? extends Pages> publicationInstance;
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

        public Builder withPublicationInstance(PublicationInstance<? extends Pages> publicationInstance) {
            this.publicationInstance = publicationInstance;
            return this;
        }

        public Reference build() {
            return new Reference(this);
        }
    }
}

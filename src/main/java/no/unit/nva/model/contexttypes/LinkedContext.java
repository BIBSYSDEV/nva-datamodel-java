package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.net.MalformedURLException;
import java.net.URI;

/**
 * LinkedContexts are contexts that are expressed as a URI, allowing an existing (external) Publication
 * to be used as a PublicationContext. For example, a BookAnthology containing a Chapter, then the
 * Chapter will contain a linkedContext as a reference to the Book. A publication part of or included in another
 * publication will have an linkedContext referencing the containing publication
 * The linkedContext is always X isPartOf Y.
 */
public interface LinkedContext extends PublicationContext {

    @JsonIgnore
    String ERROR_TEMPLATE = "The URI <%s> is an invalid context";

    URI getLinkedContext();

    void setLinkedContext(URI linkedContext);

    default void validateContext(URI linkedContext) {
        try {
            linkedContext.toURL();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(String.format(getErrorTemplate(), linkedContext));
        }
    }

    @JsonIgnore
    default String getErrorTemplate() {
        return ERROR_TEMPLATE;
    }

}

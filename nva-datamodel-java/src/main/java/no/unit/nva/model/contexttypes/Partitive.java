package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.net.MalformedURLException;
import java.net.URI;

/**
 * Partitives ("part-of-s") are contexts that are expressed as a URI, allowing an existing (external) Publication
 * to be used as a PublicationContext. For example, a BookAnthology containing a Chapter, then the
 * Chapter will contain a partOf as a reference to the Book. A publication part of or included in another
 * publication will have a partOf referencing the containing publication
 */
public interface Partitive extends PublicationContext {

    @JsonIgnore
    String ERROR_TEMPLATE = "The URI <%s> is an invalid part-of relation";

    URI getPartOf();

    void setPartOf(URI partOf);

    default void validateUri(URI partOf) {
        try {
            //noinspection ResultOfMethodCallIgnored
            partOf.toURL();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(String.format(getErrorTemplate(), partOf));
        }
    }

    @JsonIgnore
    default String getErrorTemplate() {
        return ERROR_TEMPLATE;
    }
}

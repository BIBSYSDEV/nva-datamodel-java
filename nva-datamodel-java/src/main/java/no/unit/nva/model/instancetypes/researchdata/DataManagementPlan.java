package no.unit.nva.model.instancetypes.researchdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.NonPeerReviewedMonograph;
import no.unit.nva.model.pages.MonographPages;
import nva.commons.core.JacocoGenerated;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.nonNull;
import static no.unit.nva.model.instancetypes.PublicationInstance.Constants.PAGES_FIELD;

/**
 * A data management plan is a document that describes the administrative processes around data sets.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DataManagementPlan extends NonPeerReviewedMonograph {
    public static final String PUBLISHER_FIELD = "publisher";
    public static final String RELATED_FIELD = "related";
    @JsonProperty(PUBLISHER_FIELD)
    private final URI publisher;
    @JsonProperty(RELATED_FIELD)
    private final Set<URI> related;

    /**
     * Constructor for DataManagementPlan (DMP)
     * @param publisher The URI of the DMP publisher (may or may not be the same as the Publication publisher).
     * @param related A collection of URIs referencing things covered by the DMP
     */
    public DataManagementPlan(@JsonProperty(PUBLISHER_FIELD) URI publisher,
                              @JsonProperty(RELATED_FIELD) Collection<URI> related,
                              @JsonProperty(PAGES_FIELD) MonographPages pages) {
        super(pages);
        this.publisher = publisher;
        this.related = nonNull(related) ? new HashSet<>(related) : Collections.emptySet();
    }

    public URI getPublisher() {
        return publisher;
    }

    public Set<URI> getRelated() {
        return related;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataManagementPlan)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        DataManagementPlan that = (DataManagementPlan) o;
        return Objects.equals(getPublisher(), that.getPublisher())
                && Objects.equals(getRelated(), that.getRelated());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPublisher(), getRelated());
    }
}

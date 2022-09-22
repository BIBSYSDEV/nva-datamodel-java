package no.unit.nva.model.instancetypes.researchdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.NonPeerReviewedMonograph;
import no.unit.nva.model.pages.MonographPages;
import nva.commons.core.JacocoGenerated;

import java.net.URI;
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
    public static final String RELATED_FIELD = "related";
    @JsonProperty(RELATED_FIELD)
    private final Set<URI> related;

    /**
     * Constructor for DataManagementPlan (DMP).
     * @param related A collection of URIs referencing things covered by the DMP.
     * @param pages The pages description for the DMP document.
     */
    public DataManagementPlan(@JsonProperty(RELATED_FIELD) RelatedUris related,
                              @JsonProperty(PAGES_FIELD) MonographPages pages) {
        super(pages);
        this.related = nonNull(related) ? new HashSet<>(related) : Collections.emptySet();
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
        return Objects.equals(getRelated(), that.getRelated());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRelated());
    }
}

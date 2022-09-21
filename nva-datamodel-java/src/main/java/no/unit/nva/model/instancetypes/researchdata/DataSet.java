package no.unit.nva.model.instancetypes.researchdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.NonPeerReviewed;
import no.unit.nva.model.instancetypes.NonPeerReviewedMonograph;
import no.unit.nva.model.pages.NullPages;
import no.unit.nva.model.pages.Pages;
import nva.commons.core.JacocoGenerated;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Objects.nonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DataSet extends NonPeerReviewed<NullPages> {
    public static final String USER_AGREES_TO_TERMS_AND_CONDITIONS_FIELD = "userAgreesToTermsAndConditions";
    public static final String GEOGRAPHICAL_COVERAGE_FIELD = "geographicalCoverage";
    public static final String REFERENCED_BY_FIELD = "referencedBy";
    public static final String RELATED_FIELD = "related";
    public static final String COMPLIES_WITH_FIELD = "compliesWith";
    @JsonProperty(USER_AGREES_TO_TERMS_AND_CONDITIONS_FIELD) private final boolean userAgreesToTermsAndConditions;
    @JsonProperty(GEOGRAPHICAL_COVERAGE_FIELD) private final String geographicalCoverage;
    @JsonProperty(REFERENCED_BY_FIELD) private final Set<URI> referencedBy;
    @JsonProperty(RELATED_FIELD) private final Set<URI> related;
    @JsonProperty(COMPLIES_WITH_FIELD) private final Set<URI> compliesWith;

    public DataSet(@JsonProperty(USER_AGREES_TO_TERMS_AND_CONDITIONS_FIELD) boolean userAgreesToTermsAndConditions,
                   @JsonProperty(GEOGRAPHICAL_COVERAGE_FIELD) String geographicalCoverage,
                   @JsonProperty(REFERENCED_BY_FIELD) Collection<URI> referencedBy,
                   @JsonProperty(RELATED_FIELD) Collection<URI> related,
                   @JsonProperty(COMPLIES_WITH_FIELD) Collection<URI> compliesWith) {
        this.geographicalCoverage = geographicalCoverage;
        this.referencedBy = nonNull(referencedBy) ? new HashSet<>(referencedBy) : emptySet();
        this.related = nonNull(related) ? new HashSet<>(related) : emptySet();
        this.compliesWith = nonNull(compliesWith) ? new HashSet<>(compliesWith) : emptySet();
        this.userAgreesToTermsAndConditions = userAgreesToTermsAndConditions;
    }

    @Override
    public NullPages getPages() {
        return new NullPages();
    }

    @Override
    public void setPages(NullPages pages) {
        // NO-OP
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataSet)) {
            return false;
        }
        DataSet dataSet = (DataSet) o;
        return userAgreesToTermsAndConditions == dataSet.userAgreesToTermsAndConditions
                && Objects.equals(geographicalCoverage, dataSet.geographicalCoverage)
                && Objects.equals(referencedBy, dataSet.referencedBy)
                && Objects.equals(related, dataSet.related)
                && Objects.equals(compliesWith, dataSet.compliesWith);
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(userAgreesToTermsAndConditions, geographicalCoverage, referencedBy, related, compliesWith);
    }
}

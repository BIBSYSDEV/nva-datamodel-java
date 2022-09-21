package no.unit.nva.model.instancetypes.researchdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.NonPeerReviewed;
import no.unit.nva.model.pages.NullPages;
import nva.commons.core.JacocoGenerated;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
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
    @JsonProperty(GEOGRAPHICAL_COVERAGE_FIELD) private final GeographicalDescription geographicalCoverage;
    @JsonProperty(REFERENCED_BY_FIELD) private final Set<URI> referencedBy;
    @JsonProperty(RELATED_FIELD) private final Set<URI> related;
    @JsonProperty(COMPLIES_WITH_FIELD) private final Set<URI> compliesWith;

    public DataSet(@JsonProperty(USER_AGREES_TO_TERMS_AND_CONDITIONS_FIELD) boolean userAgreesToTermsAndConditions,
                   @JsonProperty(GEOGRAPHICAL_COVERAGE_FIELD) GeographicalDescription geographicalCoverage,
                   @JsonProperty(REFERENCED_BY_FIELD) Collection<URI> referencedBy,
                   @JsonProperty(RELATED_FIELD) Collection<URI> related,
                   @JsonProperty(COMPLIES_WITH_FIELD) Collection<URI> compliesWith) {
        super();
        this.geographicalCoverage = geographicalCoverage;
        this.referencedBy = nonNull(referencedBy) ? new HashSet<>(referencedBy) : emptySet();
        this.related = nonNull(related) ? new HashSet<>(related) : emptySet();
        this.compliesWith = nonNull(compliesWith) ? new HashSet<>(compliesWith) : emptySet();
        this.userAgreesToTermsAndConditions = userAgreesToTermsAndConditions;
    }

    public boolean isUserAgreesToTermsAndConditions() {
        return userAgreesToTermsAndConditions;
    }

    public GeographicalDescription getGeographicalCoverage() {
        return geographicalCoverage;
    }

    public Set<URI> getReferencedBy() {
        return referencedBy;
    }

    public Set<URI> getRelated() {
        return related;
    }

    public Set<URI> getCompliesWith() {
        return compliesWith;
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
        return isUserAgreesToTermsAndConditions() == dataSet.isUserAgreesToTermsAndConditions()
                && Objects.equals(getGeographicalCoverage(), dataSet.getGeographicalCoverage())
                && Objects.equals(getReferencedBy(), dataSet.getReferencedBy())
                && Objects.equals(getRelated(), dataSet.getRelated())
                && Objects.equals(getCompliesWith(), dataSet.getCompliesWith());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(isUserAgreesToTermsAndConditions(), getGeographicalCoverage(),
                getReferencedBy(), getRelated(), getCompliesWith());
    }
}

package no.unit.nva.model.instancetypes.researchdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Map;
import java.util.stream.Collectors;
import no.unit.nva.model.instancetypes.degree.ConfirmedDocument;
import no.unit.nva.model.instancetypes.degree.RelatedDocument;
import no.unit.nva.model.instancetypes.degree.UnconfirmedDocument;
import no.unit.nva.model.pages.NullPages;
import nva.commons.core.JacocoGenerated;

import java.net.URI;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Objects.nonNull;
import static nva.commons.core.attempt.Try.attempt;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DataSet implements no.unit.nva.model.instancetypes.PublicationInstance<NullPages> {
    public static final String USER_AGREES_TO_TERMS_AND_CONDITIONS_FIELD = "userAgreesToTermsAndConditions";
    public static final String GEOGRAPHICAL_COVERAGE_FIELD = "geographicalCoverage";
    public static final String REFERENCED_BY_FIELD = "referencedBy";
    public static final String RELATED_FIELD = "related";
    public static final String COMPLIES_WITH_FIELD = "compliesWith";
    @JsonProperty(USER_AGREES_TO_TERMS_AND_CONDITIONS_FIELD)
    private final boolean userAgreesToTermsAndConditions;
    @JsonProperty(GEOGRAPHICAL_COVERAGE_FIELD)
    private final GeographicalDescription geographicalCoverage;
    @JsonProperty(REFERENCED_BY_FIELD)
    private final Set<URI> referencedBy;
    @JsonProperty(RELATED_FIELD)
    private final Set<RelatedDocument> related;
    @JsonProperty(COMPLIES_WITH_FIELD)
    private final Set<URI> compliesWith;

    public DataSet(@JsonProperty(USER_AGREES_TO_TERMS_AND_CONDITIONS_FIELD) boolean userAgreesToTermsAndConditions,
                   @JsonProperty(GEOGRAPHICAL_COVERAGE_FIELD) GeographicalDescription geographicalCoverage,
                   @JsonProperty(REFERENCED_BY_FIELD) ReferencedByUris referencedByUris,
                   @JsonProperty(RELATED_FIELD) Set<Object> related,
                   @JsonProperty(COMPLIES_WITH_FIELD) CompliesWithUris compliesWith) {
        super();
        this.geographicalCoverage = geographicalCoverage;
        this.referencedBy = nonNull(referencedByUris) ? new HashSet<>(referencedByUris) : emptySet();
        this.related = related.stream().map(this::toRelatedDocument).collect(Collectors.toSet());
        this.compliesWith = nonNull(compliesWith) ? new HashSet<>(compliesWith) : emptySet();
        this.userAgreesToTermsAndConditions = userAgreesToTermsAndConditions;
    }

    private RelatedDocument toRelatedDocument(Object item) {
        if (item instanceof Map map) {
            String name = ConfirmedDocument.class.getSimpleName();
            return name.equals(map.get("type"))
                       ? new ConfirmedDocument(URI.create(map.get("identifier").toString()))
                       : new UnconfirmedDocument(map.get("identifier").toString());
        }
        var value = attempt(() -> URI.create(item.toString()))
                        .orElse(failure -> null);
        if (value != null) {
            return new ConfirmedDocument(value);
        } else {
            return new UnconfirmedDocument(item.toString());
        }
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

    public Set<RelatedDocument> getRelated() {
        return related;
    }

    public Set<URI> getCompliesWith() {
        return compliesWith;
    }

    @Override
    public NullPages getPages() {
        return NullPages.NULL_PAGES;
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

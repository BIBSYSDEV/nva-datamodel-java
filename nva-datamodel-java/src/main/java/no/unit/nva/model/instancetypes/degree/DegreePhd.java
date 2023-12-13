package no.unit.nva.model.instancetypes.degree;

import static no.unit.nva.model.instancetypes.PublicationInstance.Constants.PAGES_FIELD;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import java.util.Set;
import no.unit.nva.model.PublicationDate;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DegreePhd extends DegreeBase {

    public static final String RELATED_PUBLICATIONS_FIELD = "relatedPublications";
    public static final String RELATED_RESOURCES_FIELD = "relatedResources";
    private final Set<URI> relatedPublications;
    private final Set<String> relatedResources;

    public DegreePhd(@JsonProperty(PAGES_FIELD) MonographPages pages,
                     @JsonProperty(SUBMITTED_DATE_FIELD) PublicationDate submittedDate,
                     @JsonProperty(RELATED_PUBLICATIONS_FIELD) Set<URI> relatedPublications,
                     @JsonProperty(RELATED_RESOURCES_FIELD) Set<String> relatedResources) {
        super(pages, submittedDate);
        this.relatedPublications = relatedPublications;
        this.relatedResources = relatedResources;
    }

    public Set<URI> getRelatedPublications() {
        return relatedPublications;
    }

    public Set<String> getRelatedResources() {
        return relatedResources;
    }
}

package no.unit.nva.model.instancetypes.media;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.NullPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class MediaDocumentary extends AbstractMediaInstance {
    @JsonCreator
    public MediaDocumentary(@JsonProperty(CONTRIBUTION_TYPE) ContributionType contributionType) {
        super(contributionType);
    }

    @Override
    public void setPages(NullPages pages) {

    }
}

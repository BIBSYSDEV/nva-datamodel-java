package no.unit.nva.model.instancetypes.chapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.Range;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class PopularScienceChapter extends ChapterArticle {

    private static final boolean ORIGINAL_RESEARCH = false;

    public PopularScienceChapter(@JsonProperty(PAGES_FIELD) Range pages,
                                 @JsonProperty(PEER_REVIEWED_FIELD) boolean peerReviewed) {
        super(pages, peerReviewed, ORIGINAL_RESEARCH);
    }
}

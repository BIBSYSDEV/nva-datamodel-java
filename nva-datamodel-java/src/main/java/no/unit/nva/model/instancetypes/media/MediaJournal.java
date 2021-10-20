package no.unit.nva.model.instancetypes.media;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.NullPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class MediaJournal extends AbstractMediaInstance {
    @JsonCreator
    public MediaJournal() {
        super();
    }

    @Override
    public void setPages(NullPages pages) {

    }
}

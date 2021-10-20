package no.unit.nva.model.instancetypes.media;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.NullPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class MediaNewspaper extends AbstractMediaInstance {
    @JsonCreator
    public MediaNewspaper() {
        super();
    }

    @Override
    public void setPages(NullPages pages) {

    }
}

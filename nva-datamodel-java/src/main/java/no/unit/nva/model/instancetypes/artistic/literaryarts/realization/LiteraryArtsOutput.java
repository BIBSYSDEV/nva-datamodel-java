package no.unit.nva.model.instancetypes.artistic.literaryarts.realization;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME, include = As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(name = "PrintedMatter", value = PrintedMatter.class),
    @JsonSubTypes.Type(names = {"Audiobook", "RadioPlay", "ShortFilm", "Podcast", "Other"}, value = AudioVisual.class),
    @JsonSubTypes.Type(names = {"Reading","Play","Other"}, value = LivePerformance.class),
    @JsonSubTypes.Type(name = "Web", value = Web.class)
})
public interface LiteraryArtsOutput {
    String TYPE = "type";

}

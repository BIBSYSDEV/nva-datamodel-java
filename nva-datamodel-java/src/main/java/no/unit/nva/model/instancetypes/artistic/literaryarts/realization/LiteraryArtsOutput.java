package no.unit.nva.model.instancetypes.artistic.literaryarts.realization;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "PrintedMatter", value = PrintedMatter.class),
    @JsonSubTypes.Type(name = "AUDIOBOOK", value = AudioVisual.class),
    @JsonSubTypes.Type(name = "RADIO_PLAY", value = AudioVisual.class),
    @JsonSubTypes.Type(name = "SHORT_FILM", value = AudioVisual.class),
    @JsonSubTypes.Type(name = "PODCAST", value = AudioVisual.class),
    @JsonSubTypes.Type(name = "OTHER", value = AudioVisual.class),
    @JsonSubTypes.Type(name = "LivePerformance", value = LivePerformance.class),
    @JsonSubTypes.Type(name = "Web", value = Web.class)
})
public interface LiteraryArtsOutput {
}

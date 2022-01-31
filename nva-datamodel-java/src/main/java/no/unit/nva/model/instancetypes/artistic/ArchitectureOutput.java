package no.unit.nva.model.instancetypes.artistic;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.artistic.realization.Award;
import no.unit.nva.model.instancetypes.artistic.realization.Competition;
import no.unit.nva.model.instancetypes.artistic.realization.Exhibition;
import no.unit.nva.model.instancetypes.artistic.realization.MentionInPublication;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "Competition", value = Competition.class),
    @JsonSubTypes.Type(name = "MentionInPublication", value = MentionInPublication.class),
    @JsonSubTypes.Type(name = "Award", value = Award.class),
    @JsonSubTypes.Type(name = "Exhibition", value = Exhibition.class)
})
public interface ArchitectureOutput {
}

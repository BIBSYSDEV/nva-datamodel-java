package no.unit.nva.model.instancetypes.artistic;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.artistic.realization.Competition;
import no.unit.nva.model.instancetypes.artistic.realization.MentionOrPublication;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "Competition", value = Competition.class),
    @JsonSubTypes.Type(name = "MentionOrPublication", value = MentionOrPublication.class)
})
public interface ArchitectureOutput {
}

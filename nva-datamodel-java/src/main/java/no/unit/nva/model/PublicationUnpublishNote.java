package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("PublicationUnpublishNote")
public record PublicationUnpublishNote(String note) implements PublicationNoteBase {

}

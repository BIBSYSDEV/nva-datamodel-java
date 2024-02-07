package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("UnpublishingNote")
public record UnpublishingNote(String note) implements PublicationNoteBase {

}

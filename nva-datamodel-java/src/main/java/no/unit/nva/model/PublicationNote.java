package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Deprecated
@JsonTypeName("PublicationNote")
public record PublicationNote(String publicationNoteMessage) implements PublicationNoteBase {
}

package no.unit.nva.model;

public record CristinIdentifier(String sourceName, String value) implements AdditionalIdentifierBase {

    static final String TYPE = "CristinIdentifier";

}

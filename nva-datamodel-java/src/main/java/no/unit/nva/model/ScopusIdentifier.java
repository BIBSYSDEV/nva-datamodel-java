package no.unit.nva.model;


public record ScopusIdentifier(String sourceName, String value) implements AdditionalIdentifierBase {

    static final String TYPE = "ScopusIdentifier";

}

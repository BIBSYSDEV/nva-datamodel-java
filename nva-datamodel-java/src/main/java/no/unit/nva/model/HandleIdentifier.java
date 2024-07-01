package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;

public record HandleIdentifier(
    // on write/deserialization populate URI uri from value in json, on read/serialization use value() override
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY) URI uri,
    // on write/deserialization populate SourceName source from sourceName in json, on read/serialization use
    // sourceName() override
    @JsonProperty(value = "sourceName", access = JsonProperty.Access.WRITE_ONLY) SourceName source)
    implements AdditionalIdentifierBase {

    static final String TYPE = "HandleIdentifier";

    @Override
    public String value() {
        return uri.toString();
    }

    @Override
    public String sourceName() {
        return source.toString();
    }
}

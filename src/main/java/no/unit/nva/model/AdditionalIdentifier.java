package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class AdditionalIdentifier {

    @JsonProperty("source")
    private String source;
    @JsonProperty("identifier")
    private String identifier;

    @JacocoGenerated
    public AdditionalIdentifier() {

    }

    public AdditionalIdentifier(String source, String identifier) {
        this.identifier = identifier;
        this.source = source;
    }

    @JacocoGenerated
    public String getSource() {
        return source;
    }

    @JacocoGenerated
    public void setSource(String source) {
        this.source = source;
    }

    @JacocoGenerated
    public String getIdentifier() {
        return identifier;
    }

    @JacocoGenerated
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getSource(), getIdentifier());
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdditionalIdentifier)) {
            return false;
        }
        AdditionalIdentifier that = (AdditionalIdentifier) o;
        return Objects.equals(getSource(), that.getSource())
               && Objects.equals(getIdentifier(), that.getIdentifier());
    }
}

package no.unit.nva.model;

import static nva.commons.core.attempt.Try.attempt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;
import nva.commons.core.JsonUtils;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class AdditionalIdentifier {

    @JsonProperty("source")
    private String source;
    @JsonProperty("value")
    private String value;

    @JacocoGenerated
    public AdditionalIdentifier() {

    }

    public AdditionalIdentifier(String source, String value) {
        this.value = value;
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
    public String getValue() {
        return value;
    }

    @JacocoGenerated
    public void setValue(String value) {
        this.value = value;
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getSource(), getValue());
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
        return Objects.equals(getSource(), that.getSource()) && Objects.equals(getValue(),
                                                                               that.getValue());
    }

    @Override
    @JacocoGenerated
    public String toString() {
        return attempt(() -> JsonUtils.objectMapperWithEmpty.writeValueAsString(this)).orElseThrow();
    }
}

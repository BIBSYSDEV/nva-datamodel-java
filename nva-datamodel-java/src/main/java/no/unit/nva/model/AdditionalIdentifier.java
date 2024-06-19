package no.unit.nva.model;

import static no.unit.nva.DatamodelConfig.dataModelObjectMapper;
import static nva.commons.core.attempt.Try.attempt;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class AdditionalIdentifier {

    // TODO: Remove alias when data has been migrated
    @Deprecated
    @JsonAlias("importSource")
    @JsonProperty("sourceName")
    private String sourceName;
    @JsonProperty("value")
    private String value;

    @JacocoGenerated
    public AdditionalIdentifier() {

    }

    public AdditionalIdentifier(String sourceName, String value) {
        this.value = value;
        this.sourceName = sourceName;
    }

    @JacocoGenerated
    public String getSourceName() {
        return sourceName;
    }

    @JacocoGenerated
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
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
        return Objects.hash(getSourceName(), getValue());
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
        return Objects.equals(getSourceName(), that.getSourceName()) && Objects.equals(getValue(),
                                                                                       that.getValue());
    }

    @Override
    @JacocoGenerated
    public String toString() {
        return attempt(() -> dataModelObjectMapper.writeValueAsString(this)).orElseThrow();
    }
}

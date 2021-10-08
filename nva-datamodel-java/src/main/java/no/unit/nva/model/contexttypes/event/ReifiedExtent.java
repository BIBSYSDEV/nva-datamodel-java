package no.unit.nva.model.contexttypes.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ReifiedExtent {

    public static final String VALUE = "value";
    public static final String DATATYPE = "datatype";
    @JsonProperty(VALUE)
    private final String value;
    @JsonProperty(DATATYPE)
    private final String datatype;

    public ReifiedExtent(@JsonProperty(VALUE) String value, @JsonProperty(DATATYPE) String datatype) {
        this.value = value;
        this.datatype = datatype;
    }

    public String getValue() {
        return value;
    }

    public String getDatatype() {
        return datatype;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReifiedExtent)) {
            return false;
        }
        ReifiedExtent that = (ReifiedExtent) o;
        return Objects.equals(getValue(), that.getValue())
               && Objects.equals(getDatatype(), that.getDatatype());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getDatatype());
    }
}

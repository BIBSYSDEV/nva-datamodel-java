package no.unit.nva.model.contexttypes.event;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ReifiedExtent {
    private final String value;
    private final String datatype;

    public ReifiedExtent(String value, String datatype) {
        this.value = value;
        this.datatype = datatype;
    }

    public String getValue() {
        return value;
    }

    public String getDatatype() {
        return datatype;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getDatatype());
    }
}

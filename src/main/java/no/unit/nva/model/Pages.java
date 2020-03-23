package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Pages {
    private String begin;
    private String end;

    public Pages() {
    }

    private Pages(Builder builder) {
        setBegin(builder.begin);
        setEnd(builder.end);
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pages)) {
            return false;
        }
        Pages that = (Pages) o;
        return Objects.equals(getBegin(), that.getBegin())
                && Objects.equals(getEnd(), that.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBegin(), getEnd());
    }

    public static final class Builder {
        private String begin;
        private String end;

        public Builder() {
        }

        public Builder withBegins(String begins) {
            this.begin = begins;
            return this;
        }

        public Builder withEnds(String ends) {
            this.end = ends;
            return this;
        }

        public Pages build() {
            return new Pages(this);
        }

    }
}

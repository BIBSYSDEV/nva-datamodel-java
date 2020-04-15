package no.unit.nva.model.pages;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Range implements Pages {
    private String begin;
    private String end;

    public Range() {
    }

    private Range(Builder builder) {
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


    public static final class Builder {
        private String begin;
        private String end;

        public Builder() {
        }

        public Builder withBegin(String begin) {
            this.begin = begin;
            return this;
        }

        public Builder withEnd(String end) {
            this.end = end;
            return this;
        }

        public Range build() {
            return new Range(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Range)) {
            return false;
        }
        Range range = (Range) o;
        return Objects.equals(getBegin(), range.getBegin())
                && Objects.equals(getEnd(), range.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBegin(), getEnd());
    }
}

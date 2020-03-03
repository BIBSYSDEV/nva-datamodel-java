package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class PublicationDate {

    private String year;
    private String month;
    private String day;

    public PublicationDate() {

    }

    private PublicationDate(Builder builder) {
        setYear(builder.year);
        setMonth(builder.month);
        setDay(builder.day);
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public static final class Builder {
        private String year;
        private String month;
        private String day;

        public Builder() {
        }

        public Builder withYear(String year) {
            this.year = year;
            return this;
        }

        public Builder withMonth(String month) {
            this.month = month;
            return this;
        }

        public Builder withDay(String day) {
            this.day = day;
            return this;
        }

        public PublicationDate build() {
            return new PublicationDate(this);
        }
    }
}

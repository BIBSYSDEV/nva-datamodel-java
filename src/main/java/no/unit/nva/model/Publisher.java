package no.unit.nva.model;

import java.util.Map;
import java.util.UUID;

public class Publisher {

    private Map<String,String> titles;
    private UUID identifier;

    public Publisher() {

    }

    private Publisher(Builder builder) {
        setTitles(builder.titles);
        setIdentifier(builder.identifier);
    }

    public Map<String, String> getTitles() {
        return titles;
    }

    public void setTitles(Map<String, String> titles) {
        this.titles = titles;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public static final class Builder {
        private Map<String, String> titles;
        private UUID identifier;

        public Builder() {
        }

        public Builder withTitles(Map<String, String> titles) {
            this.titles = titles;
            return this;
        }

        public Builder withIdentifier(UUID identifier) {
            this.identifier = identifier;
            return this;
        }

        public Publisher build() {
            return new Publisher(this);
        }
    }
}

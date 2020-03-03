package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URI;
import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class License {

    private String identifier;
    private Map<String, String> labels;
    private URI link;

    public License() {

    }

    private License(Builder builder) {
        setIdentifier(builder.identifier);
        setLabels(builder.labels);
        setLink(builder.link);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }


    public Map<String,String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String,String> labels) {
        this.labels = labels;
    }

    public URI getLink() {
        return link;
    }

    public void setLink(URI link) {
        this.link = link;
    }


    public static final class Builder {
        private String identifier;
        private Map<String,String> labels;
        private URI link;

        public Builder() {
        }

        public Builder withIdentifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder withLabels(Map<String,String> labels) {
            this.labels = labels;
            return this;
        }

        public Builder withLink(URI link) {
            this.link = link;
            return this;
        }

        public License build() {
            return new License(this);
        }
    }
}

package no.unit.nva.model;

import java.net.URI;

public class License {

    private String title;
    private String identifier;
    private URI link;

    public License() {

    }

    private License(Builder builder) {
        title = builder.title;
        setIdentifier(builder.identifier);
        setLink(builder.link);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public URI getLink() {
        return link;
    }

    public void setLink(URI link) {
        this.link = link;
    }


    public static final class Builder {
        private String title;
        private String identifier;
        private URI link;

        public Builder() {
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withIdentifier(String identifier) {
            this.identifier = identifier;
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

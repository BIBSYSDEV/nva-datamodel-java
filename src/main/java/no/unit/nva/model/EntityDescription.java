package no.unit.nva.model;

import java.util.List;
import java.util.Map;

public class EntityDescription {

    private PublicationType type;
    private String mainTitle;
    private Map<String,String> alternativeTitles;
    private String language;
    private PublicationDate date;
    private List<Contributor> contributors;

    public EntityDescription() {

    }

    private EntityDescription(Builder builder) {
        setType(builder.type);
        setMainTitle(builder.mainTitle);
        setDate(builder.date);
        setContributors(builder.contributors);
    }

    public PublicationType getType() {
        return type;
    }

    public void setType(PublicationType type) {
        this.type = type;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public PublicationDate getDate() {
        return date;
    }

    public void setDate(PublicationDate date) {
        this.date = date;
    }

    public List<Contributor> getContributors() {
        return contributors;
    }

    public void setContributors(List<Contributor> contributors) {
        this.contributors = contributors;
    }

    public static final class Builder {
        private PublicationType type;
        private String mainTitle;
        private PublicationDate date;
        private List<Contributor> contributors;

        public Builder() {
        }

        public Builder withType(PublicationType type) {
            this.type = type;
            return this;
        }

        public Builder withMainTitle(String mainTitle) {
            this.mainTitle = mainTitle;
            return this;
        }

        public Builder withDate(PublicationDate date) {
            this.date = date;
            return this;
        }

        public Builder withContributors(List<Contributor> contributors) {
            this.contributors = contributors;
            return this;
        }

        public EntityDescription build() {
            return new EntityDescription(this);
        }
    }
}

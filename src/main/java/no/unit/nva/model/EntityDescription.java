package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URI;
import java.util.List;
import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class EntityDescription {

    private String publicationType;
    private String mainTitle;
    private Map<String,String> alternativeTitles;
    private URI language;
    private PublicationDate date;
    private List<Contributor> contributors;

    public EntityDescription() {

    }

    private EntityDescription(Builder builder) {
        setPublicationType(builder.publicationType);
        setMainTitle(builder.mainTitle);
        setAlternativeTitles(builder.alternativeTitles);
        setLanguage(builder.language);
        setDate(builder.date);
        setContributors(builder.contributors);
    }

    public String getPublicationType() {
        return publicationType;
    }

    public void setPublicationType(String publicationType) {
        this.publicationType = publicationType;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public Map<String,String> getAlternativeTitles() {
        return alternativeTitles;
    }

    public void setAlternativeTitles(Map<String,String> alternativeTitles) {
        this.alternativeTitles = alternativeTitles;
    }

    public URI getLanguage() {
        return language;
    }

    public void setLanguage(URI language) {
        this.language = language;
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
        private String publicationType;
        private String mainTitle;
        private Map<String,String> alternativeTitles;
        private URI language;
        private PublicationDate date;
        private List<Contributor> contributors;

        public Builder() {
        }

        public Builder withPublicationType(String type) {
            this.publicationType = type;
            return this;
        }

        public Builder withMainTitle(String mainTitle) {
            this.mainTitle = mainTitle;
            return this;
        }

        public Builder withAlternativeTitles(Map<String,String> alternativeTitles) {
            this.alternativeTitles = alternativeTitles;
            return this;
        }

        public Builder withLanguage(URI language) {
            this.language = language;
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

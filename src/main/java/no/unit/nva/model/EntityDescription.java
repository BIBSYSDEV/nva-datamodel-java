package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class EntityDescription {

    private String mainTitle;
    private Map<String, String> alternativeTitles;
    private URI language;
    private PublicationDate date;
    private List<Contributor> contributors;
    @JsonSetter("abstract")
    private String mainLanguageAbstract;
    private String npiSubjectHeading;
    private List<String> tags;
    private String description;
    private Reference reference;
    private URI metadataSource;

    public EntityDescription() {

    }

    private EntityDescription(Builder builder) {
        setMainTitle(builder.mainTitle);
        setAlternativeTitles(builder.alternativeTitles);
        setLanguage(builder.language);
        setDate(builder.date);
        setContributors(builder.contributors);
        setAbstract(builder.mainLanguageAbstract);
        setNpiSubjectHeading(builder.npiSubjectHeading);
        setTags(builder.tags);
        setDescription(builder.description);
        setReference(builder.reference);
        setMetadataSource(builder.metadataSource);
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public Map<String, String> getAlternativeTitles() {
        return alternativeTitles;
    }

    public void setAlternativeTitles(Map<String, String> alternativeTitles) {
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

    public void setMetadataSource(URI metadataSource) {
        this.metadataSource = metadataSource;
    }

    public URI getMetadataSource() {
        return metadataSource;
    }

    public String getAbstract() {
        return mainLanguageAbstract;
    }

    public void setAbstract(String mainLanguageAbstract) {
        this.mainLanguageAbstract = mainLanguageAbstract;
    }

    public String getNpiSubjectHeading() {
        return npiSubjectHeading;
    }

    public void setNpiSubjectHeading(String npiSubjectHeading) {
        this.npiSubjectHeading = npiSubjectHeading;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityDescription)) {
            return false;
        }
        EntityDescription that = (EntityDescription) o;
        return Objects.equals(getMainTitle(), that.getMainTitle())
                && Objects.equals(getAlternativeTitles(), that.getAlternativeTitles())
                && Objects.equals(getLanguage(), that.getLanguage())
                && Objects.equals(getDate(), that.getDate())
                && Objects.equals(getContributors(), that.getContributors())
                && Objects.equals(getAbstract(), that.getAbstract())
                && Objects.equals(getNpiSubjectHeading(), that.getNpiSubjectHeading())
                && Objects.equals(getTags(), that.getTags())
                && Objects.equals(getDescription(), that.getDescription())
                && Objects.equals(getReference(), that.getReference())
                && Objects.equals(getMetadataSource(), that.getMetadataSource());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMainTitle(),
                getAlternativeTitles(),
                getLanguage(),
                getDate(),
                getContributors(),
                getAbstract(),
                getNpiSubjectHeading(),
                getTags(),
                getDescription(),
                getReference(),
                getMetadataSource());
    }

    public static final class Builder {
        private String mainTitle;
        private Map<String, String> alternativeTitles;
        private URI language;
        private PublicationDate date;
        private List<Contributor> contributors;
        private String mainLanguageAbstract;
        private String npiSubjectHeading;
        private List<String> tags;
        private String description;
        private Reference reference;
        private URI metadataSource;

        public Builder() {
        }

        public Builder withMainTitle(String mainTitle) {
            this.mainTitle = mainTitle;
            return this;
        }

        public Builder withAlternativeTitles(Map<String, String> alternativeTitles) {
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

        public Builder withAbstract(String mainLanguageAbstract) {
            this.mainLanguageAbstract = mainLanguageAbstract;
            return this;
        }

        public Builder withNpiSubjectHeading(String npiSubjectHeading) {
            this.npiSubjectHeading = npiSubjectHeading;
            return this;
        }

        public Builder withTags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withReference(Reference reference) {
            this.reference = reference;
            return this;
        }

        public Builder withMetadataSource(URI metadataSource) {
            this.metadataSource = metadataSource;
            return this;
        }

        public EntityDescription build() {
            return new EntityDescription(this);
        }
    }
}

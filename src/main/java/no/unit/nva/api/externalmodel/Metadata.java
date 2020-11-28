package no.unit.nva.api.externalmodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.model.Contributor;
import nva.commons.utils.JacocoGenerated;

import java.net.URI;
import java.util.List;
import java.util.Map;

@SuppressWarnings("PMD.ExcessiveParameterList")
public class Metadata {
    private final String mainTitle;
    private final Map<String, String> alternativeTitle;
    private final String abstrakt;
    private final String description;
    private final URI language;
    private final String npiSubjectHeading;
    private final List<String> tag;
    private final List<Contributor> contributors;
    private final URI metadataSource;
    private final Reference reference;
    private final boolean textbook;

    @JacocoGenerated
    @JsonCreator
    protected Metadata(
            @JsonProperty("abstract") String abstrakt,
            @JsonProperty("String> alternativeTitle") Map<String, String> alternativeTitle,
            @JsonProperty("contributors") List<Contributor> contributors,
            @JsonProperty("description") String description,
            @JsonProperty("language") URI language,
            @JsonProperty("mainTitle") String mainTitle,
            @JsonProperty("metadataSource") URI metadataSource,
            @JsonProperty("npiSubjectHeading") String npiSubjectHeading,
            @JsonProperty("reference") Reference reference,
            @JsonProperty("tag") List<String> tag,
            @JsonProperty("textbook") boolean textbook) {
        this.mainTitle = mainTitle;
        this.alternativeTitle = alternativeTitle;
        this.abstrakt = abstrakt;
        this.description = description;
        this.language = language;
        this.npiSubjectHeading = npiSubjectHeading;
        this.tag = tag;
        this.contributors = contributors;
        this.metadataSource = metadataSource;
        this.textbook = textbook;
        this.reference = reference;
    }

    private Metadata(Builder builder) {
        mainTitle = builder.mainTitle;
        alternativeTitle = builder.alternativeTitle;
        abstrakt = builder.abstrakt;
        description = builder.description;
        language = builder.language;
        npiSubjectHeading = builder.npiSubjectHeading;
        tag = builder.tag;
        contributors = builder.contributors;
        metadataSource = builder.metadataSource;
        reference = builder.reference;
        textbook = builder.textbook;
    }


    public String getMainTitle() {
        return mainTitle;
    }

    public Map<String, String> getAlternativeTitle() {
        return alternativeTitle;
    }

    @JsonProperty("abstract")
    public String getAbstract() {
        return abstrakt;
    }

    public String getDescription() {
        return description;
    }

    public URI getLanguage() {
        return language;
    }

    public String getNpiSubjectHeading() {
        return npiSubjectHeading;
    }

    public List<String> getTag() {
        return tag;
    }

    public List<Contributor> getContributors() {
        return contributors;
    }

    public URI getMetadataSource() {
        return metadataSource;
    }

    public boolean isTextbook() {
        return textbook;
    }

    public Reference getReference() {
        return reference;
    }

    public static final class Builder {
        private String mainTitle;
        private Map<String, String> alternativeTitle;
        private String abstrakt;
        private String description;
        private URI language;
        private String npiSubjectHeading;
        private List<String> tag;
        private List<Contributor> contributors;
        private URI metadataSource;
        private Reference reference;
        private boolean textbook;

        public Builder() {
        }

        public Builder withMainTitle(String mainTitle) {
            this.mainTitle = mainTitle;
            return this;
        }

        public Builder withAlternativeTitle(Map<String, String> alternativeTitle) {
            this.alternativeTitle = alternativeTitle;
            return this;
        }

        public Builder withAbstrakt(String abstrakt) {
            this.abstrakt = abstrakt;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withLanguage(URI language) {
            this.language = language;
            return this;
        }

        public Builder withNpiSubjectHeading(String npiSubjectHeading) {
            this.npiSubjectHeading = npiSubjectHeading;
            return this;
        }

        public Builder withTag(List<String> tag) {
            this.tag = tag;
            return this;
        }

        public Builder withContributors(List<Contributor> contributors) {
            this.contributors = contributors;
            return this;
        }

        public Builder withMetadataSource(URI metadataSource) {
            this.metadataSource = metadataSource;
            return this;
        }

        public Builder withReference(Reference reference) {
            this.reference = reference;
            return this;
        }

        public Builder withTextbook(boolean textbook) {
            this.textbook = textbook;
            return this;
        }

        public Metadata build() {
            return new Metadata(this);
        }
    }
}

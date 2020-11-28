package no.unit.nva.api.externalmodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.model.PublicationDate;
import no.unit.nva.model.pages.Pages;

import java.net.URI;
import java.util.List;

@SuppressWarnings("PMD.ExcessiveParameterList")
public class Reference {

    private final String articleNumber;
    private final PublicationDate date;
    private final String ismn;
    private final URI linkedContext;
    private final List<String> isbn;
    private final String issue;
    private final URI originalDoi;
    private final Pages pages;
    private final boolean peerReviewed;
    private final OriginalPublishingChannelDto publisher;
    private final SeriesDto series;
    private final String volume;

    @JsonCreator
    protected Reference(@JsonProperty("articleNumber") String articleNumber,
                     @JsonProperty("date") PublicationDate date,
                     @JsonProperty("ismn") String ismn,
                     @JsonProperty("isbn")List<String> isbn,
                     @JsonProperty("issue") String issue,
                     @JsonProperty("linkedContext") URI linkedContext,
                     @JsonProperty("originalDoi") URI originalDoi,
                     @JsonProperty("pages") Pages pages,
                     @JsonProperty("peerReviewed") boolean peerReviewed,
                     @JsonProperty("publisher") OriginalPublishingChannelDto publisher,
                     @JsonProperty("series") SeriesDto series,
                     @JsonProperty("volume") String volume) {
        this.articleNumber = articleNumber;
        this.date = date;
        this.ismn = ismn;
        this.isbn = isbn;
        this.issue = issue;
        this.linkedContext = linkedContext;
        this.originalDoi = originalDoi;
        this.pages = pages;
        this.peerReviewed = peerReviewed;
        this.publisher = publisher;
        this.series = series;
        this.volume = volume;
    }

    private Reference(Builder builder) {
        articleNumber = builder.articleNumber;
        date = builder.date;
        ismn = builder.ismn;
        isbn = builder.isbn;
        issue = builder.issue;
        linkedContext = builder.linkedContext;
        originalDoi = builder.originalDoi;
        pages = builder.pages;
        peerReviewed = builder.peerReviewed;
        publisher = builder.publisher;
        series = builder.series;
        volume = builder.volume;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public PublicationDate getDate() {
        return date;
    }

    public String getIsmn() {
        return ismn;
    }

    public List<String> getIsbn() {
        return isbn;
    }

    public String getIssue() {
        return issue;
    }

    public URI getLinkedContext() {
        return linkedContext;
    }

    public URI getOriginalDoi() {
        return originalDoi;
    }

    public Pages getPages() {
        return pages;
    }

    public boolean isPeerReviewed() {
        return peerReviewed;
    }

    public OriginalPublishingChannelDto getPublisher() {
        return publisher;
    }

    public SeriesDto getSeries() {
        return series;
    }

    public String getVolume() {
        return volume;
    }

    public static final class Builder {
        private String articleNumber;
        private PublicationDate date;
        private String ismn;
        private List<String> isbn;
        private String issue;
        private URI linkedContext;
        private URI originalDoi;
        private Pages pages;
        private boolean peerReviewed;
        private OriginalPublishingChannelDto publisher;
        private SeriesDto series;
        private String volume;

        public Builder() {
        }

        public Builder withArticleNumber(String articleNumber) {
            this.articleNumber = articleNumber;
            return this;
        }

        public Builder withDate(PublicationDate date) {
            this.date = date;
            return this;
        }

        public Builder withIsmn(String ismn) {
            this.ismn = ismn;
            return this;
        }

        public Builder withIsbn(List<String> isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder withIssue(String issue) {
            this.issue = issue;
            return this;
        }

        public Builder withLinkedContext(URI linkedContext) {
            this.linkedContext = linkedContext;
            return this;
        }

        public Builder withOriginalDoi(URI originalDoi) {
            this.originalDoi = originalDoi;
            return this;
        }

        public Builder withPages(Pages pages) {
            this.pages = pages;
            return this;
        }

        public Builder withPeerReviewed(boolean peerReviewed) {
            this.peerReviewed = peerReviewed;
            return this;
        }

        public Builder withPublisher(OriginalPublishingChannelDto publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder withSeries(SeriesDto series) {
            this.series = series;
            return this;
        }

        public Builder withVolume(String volume) {
            this.volume = volume;
            return this;
        }

        public Reference build() {
            return new Reference(this);
        }
    }
}

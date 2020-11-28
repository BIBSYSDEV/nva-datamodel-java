package no.unit.nva.api.externalmodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.model.Level;
import no.unit.nva.model.Organization;
import no.unit.nva.model.contexttypes.Book;
import no.unit.nva.model.contexttypes.Journal;
import no.unit.nva.model.contexttypes.PublicationContext;
import no.unit.nva.model.contexttypes.Report;
import nva.commons.utils.JacocoGenerated;

import java.net.URL;

public class OriginalPublishingChannelDto extends Organization {
    private final String name;
    private final Level level;
    private final boolean openAccess;
    private final boolean peerReviewed;
    private final String printIssn;
    private final String onlineIssn;
    private final URL url;

    @JacocoGenerated
    @JsonCreator
    protected OriginalPublishingChannelDto(
            @JsonProperty("name") String name,
            @JsonProperty("level") Level level,
            @JsonProperty("openAccess") boolean openAccess,
            @JsonProperty("peerReviewed") boolean peerReviewed,
            @JsonProperty("printIssn") String printIssn,
            @JsonProperty("onlineIssn") String onlineIssn,
            @JsonProperty("url") URL url) {
        super();
        this.name = name;
        this.level = level;
        this.openAccess = openAccess;
        this.peerReviewed = peerReviewed;
        this.printIssn = printIssn;
        this.onlineIssn = onlineIssn;
        this.url = url;
    }

    @SuppressWarnings("PMD.NullAssignment")
    protected OriginalPublishingChannelDto(PublicationContext publicationContext) {
        super();
        if (publicationContext instanceof Report) {
            var report = (Report) publicationContext;
            this.printIssn = report.getPrintIssn();
            this.onlineIssn = report.getOnlineIssn();
        } else if (publicationContext instanceof Journal) {
            var journal = (Journal) publicationContext;
            this.printIssn = journal.getPrintIssn();
            this.onlineIssn = journal.getOnlineIssn();
        } else {
            this.printIssn = null;
            this.onlineIssn = null;
        }

        if (publicationContext instanceof Book) {
            var book = (Book) publicationContext;
            this.name = book.getPublisher();
            this.level = book.getLevel();
            this.openAccess = book.isOpenAccess();
            this.peerReviewed = book.isPeerReviewed();
            this.url = book.getUrl();
        } else if (publicationContext instanceof Journal) {
            var journal = (Journal) publicationContext;
            this.name = journal.getTitle();
            this.level = journal.getLevel();
            this.openAccess = journal.isOpenAccess();
            this.peerReviewed = journal.isOpenAccess();
            this.url = journal.getUrl();
        } else {
            throw new RuntimeException("No mapping for: " + publicationContext.getClass().getSimpleName());
        }
    }

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }

    public boolean isOpenAccess() {
        return openAccess;
    }

    public boolean isPeerReviewed() {
        return peerReviewed;
    }

    public String getPrintIssn() {
        return printIssn;
    }

    public String getOnlineIssn() {
        return onlineIssn;
    }

    public URL getUrl() {
        return url;
    }
}

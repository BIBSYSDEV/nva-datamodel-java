package no.unit.nva.model.instancetypes;

import no.unit.nva.model.pages.MonographPages;
import nva.commons.utils.JacocoGenerated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public abstract class ReportContent implements PublicationInstance<MonographPages> {
    public static final String PEER_REVIEWED_FALSE =
            "peerReviewed is always false as {} is assumed to not be peer-reviewed";
    protected static final Logger logger = LoggerFactory.getLogger(ReportContent.class);

    private MonographPages pages;

    /**
     * Constructor for Report Content.
     *
     * @param pages the Pages of the PublicationInstance.
     */
    @SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
    public ReportContent(MonographPages pages) {
        super();
        setPages(pages);
    }

    @Override
    public MonographPages getPages() {
        return pages;
    }

    @Override
    public void setPages(MonographPages pages) {
        this.pages = pages;
    }

    @Override
    public void setPeerReviewed(boolean peerReviewed) {
        if (peerReviewed) {
            logger.warn(PEER_REVIEWED_FALSE, ReportContent.class.getSimpleName());
        }
    }

    @Override
    public boolean isPeerReviewed() {
        return false;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReportContent)) {
            return false;
        }
        ReportContent that = (ReportContent) o;
        return isPeerReviewed() == that.isPeerReviewed()
                && Objects.equals(getPages(), that.getPages());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPages(), isPeerReviewed());
    }
}

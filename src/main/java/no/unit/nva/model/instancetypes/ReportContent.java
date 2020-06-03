package no.unit.nva.model.instancetypes;

import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JacocoGenerated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static java.util.Objects.nonNull;

public abstract class ReportContent implements PublicationInstance {
    public static final String PEER_REVIEWED_FALSE =
            "peerReviewed is always false as {} is assumed to not be peer-reviewed";
    protected static final Logger logger = LoggerFactory.getLogger(ReportContent.class);

    private Pages pages;
    private boolean peerReviewed;

    /**
     * This constructor ensures that the peerReviewed value is always false.
     *
     * @param pages         the Pages of the PublicationInstance.
     * @param peerReviewed  the value is always ignored.
     * @throws InvalidPageTypeException if the type of Pages is incompatible with the PublicationInstance type.
     */
    @SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
    public ReportContent(Pages pages, boolean peerReviewed) throws InvalidPageTypeException {
        super();
        setPages(pages);
        setPeerReviewed(peerReviewed);
    }

    @Override
    public Pages getPages() {
        return pages;
    }

    @Override
    public void setPages(Pages pages) throws InvalidPageTypeException {
        if (nonNull(pages) && !(pages instanceof MonographPages)) {
            throw new InvalidPageTypeException(ReportContent.class, Range.class, pages.getClass());
        }
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
        return peerReviewed;
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

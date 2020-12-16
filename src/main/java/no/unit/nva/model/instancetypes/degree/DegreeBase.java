package no.unit.nva.model.instancetypes.degree;

import no.unit.nva.model.PublicationDate;
import no.unit.nva.model.instancetypes.NonPeerReviewedMonograph;
import no.unit.nva.model.pages.MonographPages;

public class DegreeBase extends NonPeerReviewedMonograph {
    private PublicationDate submittedDate;

    public DegreeBase() {
        super();
    }

    public DegreeBase(MonographPages pages, PublicationDate submittedDate) {
        super(pages);
        this.submittedDate = submittedDate;
    }

    public PublicationDate getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(PublicationDate submittedDate) {
        this.submittedDate = submittedDate;
    }
}

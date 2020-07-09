package no.unit.nva.model.instancetypes;

import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.rmi.UnexpectedException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class NonPeerReviewedTest {

    @SuppressWarnings("ConstantConditions")
    @Test
    public void nonPeerReviewedMonographPagesSettingPeerReviewedTrueThrowsUnexpectedException() {
        NonPeerReviewed<MonographPages> nonPeerReviewed = new NonPeerReviewedMonograph();
        Executable executable = () -> nonPeerReviewed.setPeerReviewed(true);
        assertThrows(UnexpectedException.class, executable);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void nonPeerReviewedRangeSettingPeerReviewedTrueThrowsUnexpectedException() {
        NonPeerReviewed<Range> nonPeerReviewed = new NonPeerReviewedPaper();
        Executable executable = () -> nonPeerReviewed.setPeerReviewed(true);
        assertThrows(UnexpectedException.class, executable);
    }
}
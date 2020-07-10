package no.unit.nva.model.instancetypes;

import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static no.unit.nva.model.instancetypes.NonPeerReviewed.PEER_REVIEWED_ERROR_TEMPLATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NonPeerReviewedTest {

    @SuppressWarnings("ConstantConditions")
    @Test
    public void nonPeerReviewedMonographPagesSettingPeerReviewedTrueThrowsUnexpectedException() {
        NonPeerReviewed<MonographPages> nonPeerReviewed = new NonPeerReviewedMonograph();
        Executable executable = () -> nonPeerReviewed.setPeerReviewed(true);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        String expectedMessage = String.format(PEER_REVIEWED_ERROR_TEMPLATE,
                NonPeerReviewedMonograph.class.getSimpleName());
        assertThat(exception.getMessage(), is(equalTo(expectedMessage)));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void nonPeerReviewedRangeSettingPeerReviewedTrueThrowsUnexpectedException() {
        NonPeerReviewed<Range> nonPeerReviewed = new NonPeerReviewedPaper();
        Executable executable = () -> nonPeerReviewed.setPeerReviewed(true);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        String expectedMessage = String.format(PEER_REVIEWED_ERROR_TEMPLATE,
                NonPeerReviewedPaper.class.getSimpleName());
        assertThat(exception.getMessage(), is(equalTo(expectedMessage)));
    }
}
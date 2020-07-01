package no.unit.nva.model.instancetypes.journal;


import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.instancetypes.JournalTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class JournalArticleTest {

    @DisplayName("Journal articles can be either peer-reviewed or non-peer-reviewed")
    @ParameterizedTest(name = "Journal articles can be created when peerReviewed is {0}")
    @ValueSource(booleans = {true, false})
    void journalArticleReturnsObjectWhenPeerReviewIsTrueOrFalse(boolean peerReviewed) throws InvalidPageRangeException {
        JournalTestData testData = new JournalTestData(peerReviewed);
        assertDoesNotThrow(() -> generateJournalArticle(testData));
    }

    private JournalArticle generateJournalArticle(JournalTestData testData) {
        return new JournalArticle.Builder()
                .withVolume(testData.getVolume())
                .withIssue(testData.getIssue())
                .withPages(testData.getPages())
                .withArticleNumber(testData.getArticleNumber())
                .withPeerReviewed(testData.isPeerReviewed())
                .build();
    }

}
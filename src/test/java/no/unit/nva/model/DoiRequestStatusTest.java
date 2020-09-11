package no.unit.nva.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DoiRequestStatusTest {

    @ParameterizedTest
    // ExistingState , RequestedChange, ExpectedState
    @CsvSource({
        "REQUESTED,APPROVED,APPROVED",
        "REQUESTED,REJECTED,REJECTED",
        "REQUESTED,REQUESTED,REQUESTED",

        "APPROVED,REQUESTED,APPROVED",
        "APPROVED,APPROVED,APPROVED",
        "APPROVED,REJECTED,APPROVED",

        "REJECTED,REJECTED,REJECTED",
        "REJECTED,APPROVED,APPROVED",
        "REJECTED,REQUESTED,REJECTED"
    })
    @DisplayName("Should follow business rules for valid transition changes on DoiRequestStatus")
    void transitionExpectations(DoiRequestStatus existingState,
                                DoiRequestStatus requestedChange,
                                DoiRequestStatus expectedState) {
        assertThat(existingState.transition(requestedChange), is(equalTo(expectedState)));
    }
}
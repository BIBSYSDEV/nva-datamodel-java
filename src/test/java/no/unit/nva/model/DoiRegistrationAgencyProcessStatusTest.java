package no.unit.nva.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DoiRegistrationAgencyProcessStatusTest {

    @ParameterizedTest
    // ExistingState , RequestedChange, ExpectedState
    @CsvSource({
        "NOT_STARTED,IN_PROGRESS_DATACITE,IN_PROGRESS_DATACITE",
        "IN_PROGRESS_DATACITE,DRAFT,DRAFT",
        "IN_PROGRESS_DATACITE,FINDABLE,FINDABLE",
        "IN_PROGRESS_DATACITE,ARCHIVED,ARCHIVED",
        "DRAFT,IN_PROGRESS_DATACITE,IN_PROGRESS_DATACITE",
        "FINDABLE,IN_PROGRESS_DATACITE,IN_PROGRESS_DATACITE",
        "ARCHIVED,IN_PROGRESS_DATACITE,IN_PROGRESS_DATACITE",
    })
    @DisplayName("Should follow business rules for valid status changes on DoiRequestStatus")
    void validStatusChanges(DoiRegistrationAgencyProcessStatus existingState,
                            DoiRegistrationAgencyProcessStatus requestedChange,
                            DoiRegistrationAgencyProcessStatus expectedState) {
        assertThat(existingState.changeStatus(requestedChange), is(equalTo(expectedState)));
    }

    @ParameterizedTest
    // ExistingState, RequestedChange
    @CsvSource({
        "NOT_STARTED,NOT_STARTED",
        "NOT_STARTED,DRAFT",
        "NOT_STARTED,FINDABLE",
        "NOT_STARTED,ARCHIVED",
        "DRAFT,DRAFT",
        "DRAFT,FINDABLE",
        "DRAFT,ARCHIVED",
        "FINDABLE,FINDABLE",
        "FINDABLE,DRAFT",
        "FINDABLE,ARCHIVED",
        "ARCHIVED,ARCHIVED",
        "ARCHIVED,DRAFT",
        "ARCHIVED,FINDABLE"
    })
    void invalidStatusChanges(DoiRegistrationAgencyProcessStatus existingState,
                              DoiRegistrationAgencyProcessStatus requestedChange) {
        var actualException = assertThrows(IllegalArgumentException.class,
            () -> existingState.changeStatus(requestedChange));
        assertThat(actualException.getMessage(), is(equalTo(
            existingState.getErrorMessageForNotAllowedStatusChange(requestedChange))));
    }
}
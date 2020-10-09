package no.unit.nva.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DoiRegistrationAgencyProcessStatusTest {

    @ParameterizedTest
    @CsvSource({
        "DRAFT,DRAFT",
        "FINDABLE,FINDABLE",
        "ARCHIVED,REGISTERED"
    })
    @DisplayName("Convert registration agency process status ${0} to datacite status implementation name ${1}")
    void registrationAgencyProcessStatusNameToDataciteStatusName(DoiRegistrationAgencyProcessStatus raProcessStatus,
                                                                 String expectedDataciteStatusName) {
        assertThat(raProcessStatus.toDataciteStatusName(), is(equalTo(expectedDataciteStatusName)));
    }

    @ParameterizedTest
    // ExistingState , RequestedChange, ExpectedState
    @CsvSource({
        "NOT_STARTED,DRAFT,DRAFT",
        "NOT_STARTED,FINDABLE,FINDABLE",
        "DRAFT,FINDABLE,FINDABLE",
        "DRAFT,ARCHIVED,ARCHIVED",
        "FINDABLE,ARCHIVED,ARCHIVED",
        // TODO: Missing some known use cases around ARCHIVED. (Archived private meta data,  Archived public meta data)
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
        "NOT_STARTED,ARCHIVED",
        "DRAFT,DRAFT",
        "FINDABLE,DRAFT",
        "ARCHIVED,ARCHIVED",
    })
    void invalidStatusChanges(DoiRegistrationAgencyProcessStatus existingState,
                              DoiRegistrationAgencyProcessStatus requestedChange) {
        var actualException = assertThrows(IllegalArgumentException.class,
            () -> existingState.changeStatus(requestedChange));
        assertThat(actualException.getMessage(), is(equalTo(
            existingState.getErrorMessageForNotAllowedStatusChange(requestedChange))));
    }
}
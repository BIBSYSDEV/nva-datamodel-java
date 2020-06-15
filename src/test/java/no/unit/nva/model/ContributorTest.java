package no.unit.nva.model;

import no.unit.nva.model.exceptions.MalformedContributorException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.net.URI;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContributorTest {

    public static final String EXAMPLE_EMAIL = "ks@exmaple.org";
    public static final int FIRST = 1;

    @DisplayName("Test the contributor default constructor exists")
    @Test
    void contributorDefaultConstructorExists() throws MalformedContributorException {
        new Contributor(getIdentity(),
                Collections.singletonList(getOrganization()),
                Role.CREATOR,
                0,
                true,
                "jj@example.org");
    }

    @DisplayName("The Contributor inner builder exists")
    @Test
    void builderExists() {
        new Contributor.Builder();
    }

    @DisplayName("Contributor builder constructs a valid object")
    @Test
    void contributorBuilderReturnsValidContributorWhenInputIsValid() throws MalformedContributorException {
        Identity identity = getIdentity();
        Organization organization = getOrganization();
        Contributor contributor = new Contributor.Builder()
                .withIdentity(identity)
                .withAffiliations(Collections.singletonList(organization))
                .withRole(Role.CREATOR)
                .withSequence(FIRST)
                .withCorrespondingAuthor(true)
                .withEmail(EXAMPLE_EMAIL)
                .build();
        assertEquals(identity, contributor.getIdentity());
        assertEquals(Collections.singletonList(organization), contributor.getAffiliations());
        assertEquals(Role.CREATOR, contributor.getRole());
        assertEquals(FIRST, contributor.getSequence());
        assertEquals(EXAMPLE_EMAIL, contributor.getEmail());
        assertTrue(contributor.isCorrespondingAuthor());
    }

    private static Organization getOrganization() {
        return new Organization.Builder()
                .withId(URI.create("https:/example.org/unit/123.0.0.1"))
                .withLabels(Collections.singletonMap("en", "Some name"))
                .build();
    }

    private static Identity getIdentity() {
        return new Identity.Builder().withName("Smith, Kim").build();
    }

    @DisplayName("Contributor corresponding author with blank/null email throw MalformedContributorException")
    @ParameterizedTest
    @CsvSource(value = {
            "null,true",
            "' ',true",
            "'',true"
        }, nullValues = "null")
    void contributorThrowsErrorWhenCorrespondingAuthorAndEmailIsEmptyOrBlank(String email, boolean corresponding) {
        Executable executable = () -> new Contributor.Builder()
                .withIdentity(getIdentity())
                .withAffiliations(Collections.singletonList(getOrganization()))
                .withRole(Role.CREATOR)
                .withSequence(FIRST)
                .withCorrespondingAuthor(corresponding)
                .withEmail(email)
                .build();
        MalformedContributorException exception = assertThrows(MalformedContributorException.class, executable);
        assertEquals(Contributor.CORRESPONDING_AUTHOR_EMAIL_MISSING, exception.getMessage());
    }

    @DisplayName("Contributor non-corresponding author, blank/null email does not throw MalformedContributorException")
    @ParameterizedTest
    @CsvSource(value = {
            "null,false",
            "' ',false",
            "'',false"
        }, nullValues = "null")
    void contributorDoesNotThrowErrorWhenNotCorrespondingAuthorAndEmailIsEmptyOrBlank(String email,
                                                                                      boolean corresponding) {
        Executable executable = () -> new Contributor.Builder()
                .withIdentity(getIdentity())
                .withAffiliations(Collections.singletonList(getOrganization()))
                .withRole(Role.CREATOR)
                .withSequence(FIRST)
                .withCorrespondingAuthor(corresponding)
                .withEmail(email)
                .build();
        assertDoesNotThrow(executable);
    }
}
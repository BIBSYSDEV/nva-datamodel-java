package no.unit.nva.model;

import no.unit.nva.model.exceptions.MalformedContributorException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContributorTest extends ModelTest {

    public static final String EXAMPLE_EMAIL = "ks@exmaple.org";
    public static final int FIRST = 1;

    @DisplayName("Test the contributor default constructor exists")
    @Test
    void contributorDefaultConstructorExists() throws MalformedContributorException {
        new Contributor(generateIdentity(),
                Collections.singletonList(generateOrganization()),
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
        Identity identity = generateIdentity();
        Organization organization = generateOrganization();
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

    @DisplayName("Contributor throws MalformedContributorException when corresponding author, but no email is set")
    @Test
    void contributorThrowsExceptionWhenCorrespondingAuthorNoEmail() {
        MalformedContributorException exception = assertThrows(MalformedContributorException.class, () ->
                new Contributor.Builder()
                    .withIdentity(generateIdentity())
                    .withAffiliations(Collections.singletonList(generateOrganization()))
                    .withRole(Role.CREATOR)
                    .withSequence(FIRST)
                    .withCorrespondingAuthor(true)
                    .build());
        assertEquals(Contributor.CORRESPONDING_AUTHOR_EMAIL_MISSING, exception.getMessage());
    }

    @DisplayName("Contributor throws MalformedContributorException when corresponding author, but email is empty")
    @Test
    void contributorThrowsExceptionWhenCorrespondingAuthorEmptyEmail() {
        MalformedContributorException exception = assertThrows(MalformedContributorException.class, () ->
                new Contributor.Builder()
                        .withIdentity(generateIdentity())
                        .withAffiliations(Collections.singletonList(generateOrganization()))
                        .withRole(Role.CREATOR)
                        .withSequence(FIRST)
                        .withCorrespondingAuthor(true)
                        .withEmail(EMPTY_STRING)
                        .build());
        assertEquals(Contributor.CORRESPONDING_AUTHOR_EMAIL_MISSING, exception.getMessage());
    }
}
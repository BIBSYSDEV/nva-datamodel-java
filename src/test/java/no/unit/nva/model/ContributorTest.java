package no.unit.nva.model;

import no.unit.nva.model.exceptions.MalformedContributorException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContributorTest {

    public static final String EXAMPLE_EMAIL = "ks@exmaple.org";
    public static final int FIRST = 1;
    public static final String EMPTY_STRING = "";

    @DisplayName("Test the contributor default constructor exists")
    @Test
    void contributorDefaultConstructorExists() {
        new Contributor();
    }

    @DisplayName("The Contributor inner builder exists")
    @Test
    void builderExists() {
        new Contributor.Builder();
    }

    @DisplayName("Contributor builder constructs a valid object")
    @Test
    void contributorBuilderReturnsValidContributorWhenInputIsValid() {
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

    @DisplayName("Contributor throws MalformedContributorException when corresponding author, but no email is set")
    @Test
    void contributorThrowsExceptionWhenCorrespondingAuthorNoEmail() {
        MalformedContributorException exception = assertThrows(MalformedContributorException.class, () ->
                new Contributor.Builder()
                    .withIdentity(getIdentity())
                    .withAffiliations(Collections.singletonList(getOrganization()))
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
                        .withIdentity(getIdentity())
                        .withAffiliations(Collections.singletonList(getOrganization()))
                        .withRole(Role.CREATOR)
                        .withSequence(FIRST)
                        .withCorrespondingAuthor(true)
                        .withEmail(EMPTY_STRING)
                        .build());
        assertEquals(Contributor.CORRESPONDING_AUTHOR_EMAIL_MISSING, exception.getMessage());
    }
}
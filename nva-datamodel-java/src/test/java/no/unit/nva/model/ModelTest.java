package no.unit.nva.model;

import no.unit.nva.JsonHandlingTest;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Range;

import java.net.URI;
import java.util.Collections;

public class ModelTest implements JsonHandlingTest {

    protected static MonographPages generateMonographPages(String introductionBegin,
                                                           String introductionEnd,
                                                           String pages,
                                                           boolean illustrated) {
        return new MonographPages.Builder()
            .withPages(pages)
            .withIllustrated(illustrated)
            .withIntroduction(generateRange(introductionBegin, introductionEnd))
            .build();
    }

    protected static MonographPages generateMonographPages() {
        return generateMonographPages("i", "ix", "321", true);
    }

    protected static Range generateRange(String begin, String end) {
        return new Range.Builder()
            .withBegin(begin)
            .withEnd(end)
            .build();
    }

    protected static Contributor generateContributor() {
        return new Contributor.Builder()
            .withSequence(0)
            .withRole(Role.CREATOR)
            .withAffiliations(Collections.singletonList(generateOrganization()))
            .withIdentity(generateIdentity())
            .withCorrespondingAuthor(true)
            .build();
    }

    protected static Identity generateIdentity() {
        return new Identity.Builder()
                   .withId(URI.create("https://example.org/person/123"))
                   .withOrcId("orc123")
                   .withName("Navnesen, Navn")
                   .withNameType(NameType.PERSONAL)
                   .build();
    }

    protected static Organization generateOrganization() {
        return new Organization.Builder()
            .withId(URI.create("https://example.org/org/123"))
            .withLabels(Collections.singletonMap("no", "Eksempelforlaget"))
            .build();
    }
}

package no.unit.nva.model.testing;

import static no.unit.nva.model.testing.PublicationContextBuilder.randomPublicationContext;
import static no.unit.nva.model.testing.PublicationInstanceBuilder.randomPublicationInstance;
import static no.unit.nva.model.testing.RandomUtils.randomLabels;

import static no.unit.nva.model.testing.RandomUtils.randomPublicationDate;
import static no.unit.nva.testutils.RandomDataGenerator.randomElement;
import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static no.unit.nva.testutils.RandomDataGenerator.randomUri;
import java.util.List;
import java.util.Map;
import no.unit.nva.model.Contributor;
import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.Identity;
import no.unit.nva.model.NameType;
import no.unit.nva.model.Organization;
import no.unit.nva.model.Reference;
import no.unit.nva.model.Role;
import nva.commons.core.JacocoGenerated;

@JacocoGenerated
public final class EntityDescriptionBuilder {

    private EntityDescriptionBuilder() {
        // NO-OP
    }

    public static EntityDescription randomEntityDescription(Class<?> publicationInstanceClass) {
        return new EntityDescription.Builder()
            .withReference(randomReference(publicationInstanceClass))
            .withNpiSubjectHeading(randomNpiSubjectHeading())
            .withDescription(randomString())
            .withMainTitle(randomString())
            .withLanguage(RandomLanguageUtil.randomLexvoUri())
            .withTags(randomTags())
            .withMetadataSource(randomUri())
            .withDate(randomPublicationDate())
            .withContributors(randomContributors())
            .withAlternativeTitles(randomAlternativeTitles())
            .withAbstract(randomString())
            .build();
    }

    private static Map<String, String> randomAlternativeTitles() {
        return Map.of(RandomLanguageUtil.randomBcp47CompatibleLanguage(), randomString());
    }

    private static List<Contributor> randomContributors() {
        return List.of(randomContributor(), randomContributor());
    }

    private static Contributor randomContributor() {
        return new Contributor.Builder()
            .withAffiliations(randomOrganizations())
            .withSequence(randomInteger(10))
            .withRole(randomRole())
            .withIdentity(randomIdentity())
            .build();
    }

    private static Identity randomIdentity() {
        return new Identity.Builder()
                   .withId(randomUri())
                   .withName(randomString())
                   .withOrcId(randomString())
                   .withNameType(randomNameType())
                   .build();
    }

    private static NameType randomNameType() {
        return randomElement(NameType.values());
    }

    private static Role randomRole() {
        return randomElement(Role.values());
    }

    private static List<Organization> randomOrganizations() {
        return List.of(randomOrganization());
    }

    private static Organization randomOrganization() {
        return new Organization.Builder()
            .withLabels(randomLabels())
            .withId(randomUri())
            .build();
    }

    private static List<String> randomTags() {
        return List.of(randomString());
    }

    private static String randomNpiSubjectHeading() {
        return randomString();
    }

    private static Reference randomReference(Class<?> publicationInstanceClass) {
        return new Reference.Builder()
            .withPublicationInstance(randomPublicationInstance(publicationInstanceClass))
            .withPublishingContext(randomPublicationContext(publicationInstanceClass))
            .withDoi(randomUri())
            .build();
    }
}

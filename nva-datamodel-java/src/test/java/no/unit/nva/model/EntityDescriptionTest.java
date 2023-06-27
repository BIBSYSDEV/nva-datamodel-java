package no.unit.nva.model;

import static no.unit.nva.DatamodelConfig.dataModelObjectMapper;
import static no.unit.nva.model.testing.PublicationGenerator.randomEntityDescription;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import no.unit.nva.model.instancetypes.journal.JournalReview;
import no.unit.nva.model.testing.EntityDescriptionBuilder;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.junit.jupiter.api.Test;

class EntityDescriptionTest {

    public static final Javers JAVERS = JaversBuilder.javers().build();

    @Test
    void shouldCopyEntityDescriptionWithoutDataLoss() {

        var entityDescription = randomEntityDescription(JournalReview.class);
        var copy = entityDescription.copy().build();

        var diff = compareAsObjectNodes(entityDescription, copy);
        assertThat(diff.prettyPrint(), copy, is(equalTo(entityDescription)));
        assertThat(copy, is(not(sameInstance(entityDescription))));
    }

    @Test
    void shouldSortContributorsWhenItsSet() {
        var entityDescription = randomEntityDescription(JournalReview.class);

        var contributor1 = EntityDescriptionBuilder.randomContributorWithSequence(2);
        var contributor2 = EntityDescriptionBuilder.randomContributorWithSequence(1);
        entityDescription.setContributors(List.of(contributor1, contributor2));

        assertThat(entityDescription.getContributors().get(0) , is(equalTo(contributor2)));
    }

    @Test
    void shouldSortContributorsInTheSameOrderIgnoringTheInputedOrderWhenSequencesAreSame() {
        var entityDescription1 = randomEntityDescription(JournalReview.class);
        var entityDescription2 = randomEntityDescription(JournalReview.class);

        var contributor1 = EntityDescriptionBuilder.randomContributorWithSequence(1);
        var contributor2 = EntityDescriptionBuilder.randomContributorWithSequence(1);
        var contributor3 = EntityDescriptionBuilder.randomContributorWithSequence(1);

        entityDescription1.setContributors(List.of(contributor1, contributor2, contributor3));
        entityDescription2.setContributors(List.of(contributor3, contributor2, contributor1));

        assertThat(entityDescription1.getContributors() , is(equalTo(entityDescription2.getContributors())));
    }

    private Diff compareAsObjectNodes(EntityDescription original, EntityDescription copy) {
        var originalObjectNode = dataModelObjectMapper.convertValue(original, ObjectNode.class);
        var copyObjectNode = dataModelObjectMapper.convertValue(copy, ObjectNode.class);
        return JAVERS.compare(originalObjectNode, copyObjectNode);
    }
}
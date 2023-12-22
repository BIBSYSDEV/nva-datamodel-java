package no.unit.nva.model.testing.associatedartifacts.util;

import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import java.util.Random;
import no.unit.nva.model.associatedartifacts.CustomerRightsRetentionStrategy;
import no.unit.nva.model.associatedartifacts.FunderRightsRetentionStrategy;
import no.unit.nva.model.associatedartifacts.NullRightsRetentionStrategy;
import no.unit.nva.model.associatedartifacts.OverriddenRightsRetentionStrategy;
import no.unit.nva.model.associatedartifacts.RightsRetentionStrategy;

public class RightsRetentionStrategyGenerator {

    /**
     * Generates and returns a random RightsRetentionStrategy.
     * The method randomly selects one of the three implemented types of
     * RightsRetentionStrategy: CustomerRightsRetentionStrategy,
     * OverriddenRightsRetentionStrategy, or NullRightsRetentionStrategy.
     *
     * @return A randomly selected RightsRetentionStrategy object.
     */
    public static RightsRetentionStrategy randomRightsRetentionStrategy() {
        RightsRetentionStrategy[] strategies = {
            CustomerRightsRetentionStrategy.create(),
            FunderRightsRetentionStrategy.create(),
            OverriddenRightsRetentionStrategy.create(randomString()),
            NullRightsRetentionStrategy.create(randomString())
        };

        return strategies[new Random().nextInt(strategies.length)];
    }
}
package no.unit.nva.model.testing.associatedartifacts.util;

import java.util.Random;
import no.unit.nva.model.associatedartifacts.CustomerRightsRetentionStrategy;
import no.unit.nva.model.associatedartifacts.NullRightsRetentionStrategy;
import no.unit.nva.model.associatedartifacts.OverriddenRightsRetentionStrategy;
import no.unit.nva.model.associatedartifacts.RightsRetentionStrategy;

public class RightsRetentionStrategyGenerator {

    public static RightsRetentionStrategy randomRightsRetentionStrategy() {
        RightsRetentionStrategy[] strategies = {
            new CustomerRightsRetentionStrategy(),
            new OverriddenRightsRetentionStrategy(),
            new NullRightsRetentionStrategy(null)
        };

        return strategies[new Random().nextInt(strategies.length)];
    }
}
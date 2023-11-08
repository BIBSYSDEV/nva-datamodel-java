package no.unit.nva.model.testing.associatedartifacts.util;

import java.util.Random;
import no.unit.nva.model.associatedartifacts.CustomerRightsRetentionStrategy;
import no.unit.nva.model.associatedartifacts.NullRightsRetentionStrategy;
import no.unit.nva.model.associatedartifacts.OverriddenRightsRetentionStrategy;
import no.unit.nva.model.associatedartifacts.RightsRetentionStrategy;

public class RightsRetentionStrategyGenerator {

    static final int STRATEGY_COUNT = 3;

    public static RightsRetentionStrategy randomRightsRetentionStrategy() {
        switch (new Random().nextInt(STRATEGY_COUNT)) {
            case 0:
                return new CustomerRightsRetentionStrategy();
            case 1:
                return new OverriddenRightsRetentionStrategy();
            default:
                return new NullRightsRetentionStrategy(null);
        }
    }
}
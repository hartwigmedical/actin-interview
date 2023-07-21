package datamodel.clinical;

import org.immutables.value.Value;

@Value.Immutable
public interface CypInteraction {

    enum Type {
        INDUCER,
        INHIBITOR,
        SUBSTRATE
    }

    enum Strength {
        NONE,
        STRONG,
        MODERATE,
        WEAK,
        SENSITIVE,
        MODERATE_SENSITIVE
    }

    Type type();

    Strength strength();

    String cyp();
}

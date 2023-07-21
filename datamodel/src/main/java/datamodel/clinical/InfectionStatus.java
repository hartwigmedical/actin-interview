package datamodel.clinical;

import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@Value.Style(passAnnotations = { NotNull.class, Nullable.class })
public abstract class InfectionStatus {

    public abstract boolean hasActiveInfection();

    @Nullable
    public abstract String description();
}

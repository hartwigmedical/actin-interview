package datamodel.clinical;

import java.util.Set;

import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@Value.Style(passAnnotations = { NotNull.class, Nullable.class })
public abstract class PriorSecondPrimary {

    @NotNull
    public abstract String tumorLocation();

    @NotNull
    public abstract String tumorSubLocation();

    @NotNull
    public abstract String tumorType();

    @NotNull
    public abstract String tumorSubType();

    @NotNull
    public abstract Set<String> doids();

    @Nullable
    public abstract Integer diagnosedYear();

    @Nullable
    public abstract Integer diagnosedMonth();

    @NotNull
    public abstract String treatmentHistory();

    @Nullable
    public abstract Integer lastTreatmentYear();

    @Nullable
    public abstract Integer lastTreatmentMonth();

    public abstract boolean isActive();

}

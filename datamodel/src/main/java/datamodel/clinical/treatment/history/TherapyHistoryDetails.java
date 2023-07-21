package datamodel.clinical.treatment.history;

import java.time.LocalDate;
import java.util.Set;

import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import datamodel.clinical.BodyLocationCategory;
import datamodel.clinical.ObservedToxicity;

@Value.Immutable
@Value.Style(passAnnotations = { NotNull.class, Nullable.class })
public abstract class TherapyHistoryDetails {

    @Nullable
    public abstract Integer stopYear();

    @Nullable
    public abstract Integer stopMonth();

    @Nullable
    public abstract LocalDate ongoingAsOf();

    @Nullable
    public abstract Integer cycles();

    @Nullable
    public abstract TreatmentResponse bestResponse();

    @Nullable
    public abstract StopReason stopReason();

    @Nullable
    public abstract String stopReasonDetail();

    @Nullable
    public abstract Set<ObservedToxicity> toxicities();

    @Nullable
    public abstract Set<BodyLocationCategory> bodyLocationCategories();

    @Nullable
    public abstract Set<String> bodyLocations();
}

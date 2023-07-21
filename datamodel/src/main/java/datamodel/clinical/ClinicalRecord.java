package datamodel.clinical;

import java.util.List;

import datamodel.clinical.treatment.history.TreatmentHistoryEntry;

import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import datamodel.clinical.treatment.PriorTumorTreatment;

@Value.Immutable
@Value.Style(passAnnotations = { NotNull.class, Nullable.class })
public abstract class ClinicalRecord {

    @NotNull
    public abstract String patientId();

    @NotNull
    public abstract PatientDetails patient();

    @NotNull
    public abstract TumorDetails tumor();

    @NotNull
    public abstract ClinicalStatus clinicalStatus();

    @NotNull
    public abstract List<TreatmentHistoryEntry> treatmentHistory();

    @NotNull
    public abstract List<PriorTumorTreatment> priorTumorTreatments();

    @NotNull
    public abstract List<PriorSecondPrimary> priorSecondPrimaries();

    @NotNull
    public abstract List<PriorOtherCondition> priorOtherConditions();

    @NotNull
    public abstract List<PriorMolecularTest> priorMolecularTests();

    @Nullable
    public abstract List<Complication> complications();

    @NotNull
    public abstract List<LabValue> labValues();

    @Nullable
    public abstract List<ToxicityEvaluation> toxicityEvaluations();

    @NotNull
    public abstract List<Toxicity> toxicities();

    @NotNull
    public abstract List<Intolerance> intolerances();

    @NotNull
    public abstract List<Surgery> surgeries();

    @NotNull
    public abstract List<BodyWeight> bodyWeights();

    @NotNull
    public abstract List<VitalFunction> vitalFunctions();

    @NotNull
    public abstract List<BloodTransfusion> bloodTransfusions();

    @NotNull
    public abstract List<Medication> medications();

}

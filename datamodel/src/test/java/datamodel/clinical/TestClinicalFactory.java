package datamodel.clinical;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import datamodel.clinical.treatment.Drug;
import datamodel.clinical.treatment.DrugClass;
import datamodel.clinical.treatment.DrugTherapy;
import datamodel.clinical.treatment.ImmutableDrug;
import datamodel.clinical.treatment.ImmutableDrugTherapy;
import datamodel.clinical.treatment.ImmutableOtherTreatment;
import datamodel.clinical.treatment.ImmutablePriorTumorTreatment;
import datamodel.clinical.treatment.ImmutableRadiotherapy;
import datamodel.clinical.treatment.OtherTreatment;
import datamodel.clinical.treatment.PriorTumorTreatment;
import datamodel.clinical.treatment.Radiotherapy;
import datamodel.clinical.treatment.Therapy;
import datamodel.clinical.treatment.TreatmentCategory;
import datamodel.clinical.treatment.history.ImmutableTherapyHistoryDetails;
import datamodel.clinical.treatment.history.ImmutableTreatmentHistoryEntry;
import datamodel.clinical.treatment.history.Intent;
import datamodel.clinical.treatment.history.TreatmentHistoryEntry;
import datamodel.clinical.treatment.history.TreatmentResponse;

import org.jetbrains.annotations.NotNull;

public final class TestClinicalFactory {

    private static final LocalDate TODAY = LocalDate.now();

    private static final int DAYS_SINCE_QUESTIONNAIRE = 10;
    private static final int DAYS_SINCE_REGISTRATION = 15;
    private static final int DAYS_SINCE_LAB_MEASUREMENT_1 = 30;
    private static final int DAYS_SINCE_LAB_MEASUREMENT_2 = 20;
    private static final int DAYS_SINCE_LAB_MEASUREMENT_3 = 10;
    private static final int DAYS_SINCE_TOXICITIES = 30;
    private static final int DAYS_SINCE_SURGERY = 30;
    private static final int DAYS_SINCE_BODY_WEIGHT_1 = 12;
    private static final int DAYS_SINCE_BODY_WEIGHT_2 = 18;
    private static final int DAYS_SINCE_BLOOD_PRESSURE = 15;
    private static final int DAYS_SINCE_BLOOD_TRANSFUSION = 15;
    private static final int DAYS_SINCE_MEDICATION_START = 30;
    private static final int DAYS_UNTIL_MEDICATION_END = 15;
    private static final int YEARS_SINCE_TREATMENT_LINE_1 = 2;
    private static final int YEARS_SINCE_TREATMENT_LINE_2 = 1;
    private static final int YEARS_SINCE_TREATMENT_LINE_3 = 0;
    private static final int YEARS_SINCE_SECOND_PRIMARY_DIAGNOSIS = 3;

    private TestClinicalFactory() {
    }

    @NotNull
    public static ClinicalRecord createMinimalTestClinicalRecord() {
        return ImmutableClinicalRecord.builder()
                .patientId("ACTN01029999")
                .patient(createTestPatientDetails())
                .tumor(ImmutableTumorDetails.builder().build())
                .clinicalStatus(ImmutableClinicalStatus.builder().build())
                .build();
    }

    @NotNull
    public static ClinicalRecord createProperTestClinicalRecord() {
        return ImmutableClinicalRecord.builder()
                .from(createMinimalTestClinicalRecord())
                .tumor(createTestTumorDetails())
                .clinicalStatus(createTestClinicalStatus())
                .treatmentHistory(createTreatmentHistory())
                .priorTumorTreatments(createTestPriorTumorTreatments())
                .priorSecondPrimaries(createTestPriorSecondPrimaries())
                .priorOtherConditions(createTestPriorOtherConditions())
                .priorMolecularTests(createTestPriorMolecularTests())
                .complications(createTestComplications())
                .labValues(createTestLabValues())
                .toxicityEvaluations(createTestToxicityEvaluations())
                .toxicities(createTestToxicities())
                .intolerances(createTestIntolerances())
                .surgeries(createTestSurgeries())
                .bodyWeights(createTestBodyWeights())
                .vitalFunctions(createTestVitalFunctions())
                .bloodTransfusions(createTestBloodTransfusions())
                .medications(createTestMedications())
                .build();
    }

    @NotNull
    private static PatientDetails createTestPatientDetails() {
        return ImmutablePatientDetails.builder()
                .gender(Gender.MALE)
                .birthYear(1950)
                .registrationDate(TODAY.minusDays(DAYS_SINCE_REGISTRATION))
                .questionnaireDate(TODAY.minusDays(DAYS_SINCE_QUESTIONNAIRE))
                .build();
    }

    @NotNull
    private static TumorDetails createTestTumorDetails() {
        return ImmutableTumorDetails.builder()
                .primaryTumorLocation("Skin")
                .primaryTumorSubLocation("")
                .primaryTumorType("Melanoma")
                .primaryTumorSubType("")
                .primaryTumorExtraDetails("")
                .addDoids("8923")
                .stage(TumorStage.IV)
                .hasMeasurableDisease(true)
                .hasBrainLesions(false)
                .hasActiveBrainLesions(false)
                .hasCnsLesions(true)
                .hasActiveCnsLesions(true)
                .hasBoneLesions(null)
                .hasLiverLesions(true)
                .hasLungLesions(false)
                .hasLymphNodeLesions(true)
                .addOtherLesions("Lymph nodes")
                .biopsyLocation("Liver")
                .build();
    }

    @NotNull
    private static ClinicalStatus createTestClinicalStatus() {
        ECG ecg = ImmutableECG.builder().hasSigAberrationLatestECG(false).build();

        InfectionStatus infectionStatus = ImmutableInfectionStatus.builder().hasActiveInfection(false).build();

        return ImmutableClinicalStatus.builder().who(1).infectionStatus(infectionStatus).ecg(ecg).build();
    }

    @NotNull
    private static Drug drug(@NotNull String name, @NotNull DrugClass drugClass, @NotNull TreatmentCategory category) {
        return ImmutableDrug.builder().name(name).addDrugClasses(drugClass).category(category).build();
    }

    @NotNull
    private static TreatmentHistoryEntry therapyHistoryEntry(Set<Therapy> therapies, int startYear, Intent intent) {
        return ImmutableTreatmentHistoryEntry.builder()
                .treatments(therapies)
                .startYear(startYear)
                .addIntents(intent)
                .therapyHistoryDetails(ImmutableTherapyHistoryDetails.builder().bestResponse(TreatmentResponse.PARTIAL_RESPONSE).build())
                .build();
    }

    @NotNull
    private static List<TreatmentHistoryEntry> createTreatmentHistory() {
        Drug oxaliplatin = drug("Oxaliplatin", DrugClass.PLATINUM_COMPOUND, TreatmentCategory.CHEMOTHERAPY);
        Drug fluorouracil = drug("5-FU", DrugClass.PYRIMIDINE_ANTAGONIST, TreatmentCategory.CHEMOTHERAPY);
        Drug irinotecan = drug("Irinotecan", DrugClass.TOPO1_INHIBITOR, TreatmentCategory.CHEMOTHERAPY);

        DrugTherapy folfirinox = ImmutableDrugTherapy.builder()
                .name("FOLFIRINOX")
                .isSystemic(true)
                .addDrugs(oxaliplatin, fluorouracil, irinotecan)
                .maxCycles(8)
                .build();

        Radiotherapy radioFolfirinox =
                ImmutableRadiotherapy.builder().name("FOLFIRINOX+radiotherapy").addAllDrugs(folfirinox.drugs()).isSystemic(true).build();

        Drug pembrolizumab = drug("Pembrolizumab", DrugClass.MONOCLONAL_ANTIBODY, TreatmentCategory.IMMUNOTHERAPY);

        DrugTherapy folfirinoxAndPembrolizumab = ImmutableDrugTherapy.builder()
                .name("FOLFIRINOX + pembrolizumab")
                .addAllDrugs(folfirinox.drugs())
                .addDrugs(pembrolizumab)
                .isSystemic(true)
                .build();

        DrugTherapy folfirinoxLocoRegional =
                ImmutableDrugTherapy.copyOf(folfirinox).withName("FOLFIRINOX loco-regional").withIsSystemic(false);

        OtherTreatment colectomy =
                ImmutableOtherTreatment.builder().name("Colectomy").addCategories(TreatmentCategory.SURGERY).isSystemic(true).build();

        TreatmentHistoryEntry surgeryHistoryEntry =
                ImmutableTreatmentHistoryEntry.builder().addTreatments(colectomy).startYear(2021).addIntents(Intent.MAINTENANCE).build();

        return List.of(therapyHistoryEntry(Set.of(folfirinox), 2020, Intent.NEOADJUVANT),
                surgeryHistoryEntry,
                therapyHistoryEntry(Set.of(radioFolfirinox, folfirinoxLocoRegional), 2022, Intent.ADJUVANT),
                therapyHistoryEntry(Set.of(folfirinoxAndPembrolizumab), 2023, Intent.PALLIATIVE));
    }

    @NotNull
    private static List<PriorTumorTreatment> createTestPriorTumorTreatments() {
        List<PriorTumorTreatment> priorTumorTreatments = new ArrayList<>();

        priorTumorTreatments.add(ImmutablePriorTumorTreatment.builder()
                .name("Resection")
                .startYear(TODAY.getYear() - YEARS_SINCE_TREATMENT_LINE_1)
                .addCategories(TreatmentCategory.SURGERY)
                .isSystemic(false)
                .build());

        priorTumorTreatments.add(ImmutablePriorTumorTreatment.builder()
                .name("Vemurafenib")
                .startYear(TODAY.getYear() - YEARS_SINCE_TREATMENT_LINE_2)
                .startMonth(TODAY.getMonthValue())
                .stopYear(TODAY.getYear() - YEARS_SINCE_TREATMENT_LINE_2 + 1)
                .addCategories(TreatmentCategory.TARGETED_THERAPY)
                .isSystemic(true)
                .targetedType("BRAF inhibitor")
                .build());

        priorTumorTreatments.add(ImmutablePriorTumorTreatment.builder()
                .name("Ipilimumab")
                .startYear(TODAY.getYear() - YEARS_SINCE_TREATMENT_LINE_3)
                .addCategories(TreatmentCategory.IMMUNOTHERAPY)
                .isSystemic(true)
                .immunoType("Anti-CTLA-4")
                .build());

        return priorTumorTreatments;
    }

    @NotNull
    private static List<PriorSecondPrimary> createTestPriorSecondPrimaries() {
        List<PriorSecondPrimary> priorSecondPrimaries = new ArrayList<>();

        priorSecondPrimaries.add(ImmutablePriorSecondPrimary.builder()
                .tumorLocation("Lung")
                .tumorSubLocation("")
                .tumorType("carcinoma")
                .tumorSubType("")
                .addDoids("3905")
                .diagnosedYear(TODAY.getYear() - YEARS_SINCE_SECOND_PRIMARY_DIAGNOSIS)
                .diagnosedMonth(TODAY.getMonthValue())
                .treatmentHistory("Surgery")
                .isActive(false)
                .build());

        return priorSecondPrimaries;
    }

    @NotNull
    private static List<PriorOtherCondition> createTestPriorOtherConditions() {
        List<PriorOtherCondition> priorOtherConditions = new ArrayList<>();

        priorOtherConditions.add(ImmutablePriorOtherCondition.builder()
                .name("Pancreatitis")
                .addDoids("4989")
                .category("Pancreas disease")
                .isContraindicationForTherapy(true)
                .build());

        return priorOtherConditions;
    }

    @NotNull
    private static List<PriorMolecularTest> createTestPriorMolecularTests() {
        List<PriorMolecularTest> priorMolecularTests = new ArrayList<>();

        priorMolecularTests.add(ImmutablePriorMolecularTest.builder()
                .test("Panel NGS")
                .item("BRAF")
                .measure(null)
                .scoreText("V600E positive")
                .scoreValuePrefix(null)
                .scoreValue(null)
                .scoreValueUnit(null)
                .impliesPotentialIndeterminateStatus(false)
                .build());

        return priorMolecularTests;
    }

    @NotNull
    private static List<Complication> createTestComplications() {
        List<Complication> complications = new ArrayList<>();

        complications.add(ImmutableComplication.builder().name("Ascites").addCategories("Ascites").build());

        return complications;
    }

    @NotNull
    private static List<LabValue> createTestLabValues() {
        return new ArrayList<>();
    }

    @NotNull
    private static List<Toxicity> createTestToxicities() {
        return List.of(ImmutableToxicity.builder()
                        .name("Nausea")
                        .addCategories("Nausea")
                        .evaluatedDate(TODAY.minusDays(DAYS_SINCE_TOXICITIES))
                        .source(ToxicitySource.EHR)
                        .grade(1)
                        .build(),
                ImmutableToxicity.builder()
                        .name("Fatigue")
                        .addCategories("Fatigue")
                        .evaluatedDate(TODAY.minusDays(DAYS_SINCE_TOXICITIES))
                        .source(ToxicitySource.QUESTIONNAIRE)
                        .grade(2)
                        .build());
    }

    @NotNull
    private static List<ToxicityEvaluation> createTestToxicityEvaluations() {
        return List.of(ImmutableToxicityEvaluation.builder()
                        .evaluatedDate(TODAY.minusDays(DAYS_SINCE_TOXICITIES))
                        .source(ToxicitySource.EHR)
                        .toxicities(Set.of(ImmutableObservedToxicity.builder().name("Nausea").addCategories("Nausea").grade(1).build()))
                        .build(),
                ImmutableToxicityEvaluation.builder()
                        .evaluatedDate(TODAY.minusDays(DAYS_SINCE_TOXICITIES))
                        .source(ToxicitySource.QUESTIONNAIRE)
                        .toxicities(Set.of(ImmutableObservedToxicity.builder().name("Fatigue").addCategories("Fatigue").grade(2).build()))
                        .build());
    }

    @NotNull
    private static List<Intolerance> createTestIntolerances() {
        List<Intolerance> intolerances = new ArrayList<>();

        intolerances.add(ImmutableIntolerance.builder()
                .name("Wasps")
                .category("Environment")
                .type("Allergy")
                .clinicalStatus("Active")
                .verificationStatus("Confirmed")
                .criticality("Unable-to-assess")
                .build());

        return intolerances;
    }

    @NotNull
    private static List<Surgery> createTestSurgeries() {
        return Collections.singletonList(ImmutableSurgery.builder()
                .endDate(TODAY.minusDays(DAYS_SINCE_SURGERY))
                .status(SurgeryStatus.FINISHED)
                .build());
    }

    @NotNull
    private static List<BodyWeight> createTestBodyWeights() {
        List<BodyWeight> bodyWeights = new ArrayList<>();

        bodyWeights.add(ImmutableBodyWeight.builder().date(TODAY.minusDays(DAYS_SINCE_BODY_WEIGHT_1)).value(70D).unit("Kilogram").build());
        bodyWeights.add(ImmutableBodyWeight.builder().date(TODAY.minusDays(DAYS_SINCE_BODY_WEIGHT_2)).value(68D).unit("Kilogram").build());

        return bodyWeights;
    }

    @NotNull
    private static List<VitalFunction> createTestVitalFunctions() {
        List<VitalFunction> vitalFunctions = new ArrayList<>();

        vitalFunctions.add(ImmutableVitalFunction.builder()
                .date(TODAY.minusDays(DAYS_SINCE_BLOOD_PRESSURE))
                .category(VitalFunctionCategory.NON_INVASIVE_BLOOD_PRESSURE)
                .subcategory("Mean blood pressure")
                .value(99)
                .unit("mm[Hg]")
                .build());

        return vitalFunctions;
    }

    @NotNull
    private static List<BloodTransfusion> createTestBloodTransfusions() {
        List<BloodTransfusion> bloodTransfusions = new ArrayList<>();

        bloodTransfusions.add(ImmutableBloodTransfusion.builder()
                .date(TODAY.minusDays(DAYS_SINCE_BLOOD_TRANSFUSION))
                .product("Thrombocyte concentrate")
                .build());

        return bloodTransfusions;
    }

    @NotNull
    private static List<Medication> createTestMedications() {
        List<Medication> medications = new ArrayList<>();

        return medications;
    }
}

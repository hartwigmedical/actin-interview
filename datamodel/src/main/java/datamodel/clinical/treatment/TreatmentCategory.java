package datamodel.clinical.treatment;

import org.jetbrains.annotations.NotNull;

public enum TreatmentCategory {
    CHEMOTHERAPY(true),
    RADIOTHERAPY(true),
    CHEMORADIOTHERAPY(false),
    TARGETED_THERAPY(true),
    IMMUNOTHERAPY(true),
    HORMONE_THERAPY(true),
    ANTIVIRAL_THERAPY(false),
    SUPPORTIVE_TREATMENT(true),
    SURGERY(false),
    TRANSPLANTATION(true),
    TRIAL(true),
    CAR_T(true),
    TCR_T(false),
    GENE_THERAPY(false),
    PROPHYLACTIC_TREATMENT(false),
    ABLATION(true);

    private final boolean hasType;

    TreatmentCategory(final boolean hasType) {
        this.hasType = hasType;
    }

    public boolean hasType() {
        return hasType;
    }
}

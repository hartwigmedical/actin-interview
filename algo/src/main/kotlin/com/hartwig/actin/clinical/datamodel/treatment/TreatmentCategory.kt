package com.hartwig.actin.clinical.datamodel.treatment

import com.hartwig.actin.Displayable

enum class TreatmentCategory : Displayable {
    CHEMOTHERAPY,
    TARGETED_THERAPY,
    IMMUNOTHERAPY,
    HORMONE_THERAPY,
    SURGERY,
    TRANSPLANTATION,
    RADIOTHERAPY,
    ABLATION,
    SUPPORTIVE_TREATMENT;

    override fun display(): String {
        return this.toString().lowercase()
    }
}

package com.hartwig.actin.com.hartwig.actin.datamodel.clinical

import com.hartwig.actin.datamodel.clinical.Dosage
import com.hartwig.actin.datamodel.clinical.Medication
import com.hartwig.actin.datamodel.clinical.QTProlongatingRisk

object TestMedicationFactory {

    fun createMinimal(): Medication {
        return Medication(
            name = "",
            qtProlongatingRisk = QTProlongatingRisk.NONE,
            dosage = Dosage(),
            isSelfCare = false,
            isTrialMedication = false
        )
    }
}

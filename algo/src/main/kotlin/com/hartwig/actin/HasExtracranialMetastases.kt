package com.hartwig.actin

import com.hartwig.actin.clinical.datamodel.ClinicalRecord
import com.hartwig.actin.clinical.datamodel.TumorDetails

/// @brief Checks whether the given ClinicalRecord contains lesion(s) outside of the brain region
class HasExtracranialMetastases : EvaluationFunction {

    /// @return an Evaluation with EvaluationResult.PASS if the ClinicalRecord contains lesion(s) outside of the brain region
    override fun evaluate(record: ClinicalRecord): Evaluation {
        if (record.tumor == TumorDetails()) {
            return EvaluationFactory.undetermined("HAS_EXTRACRANIAL_METASTASES: Not enough tumor data was given to verify extracranial metastases are present")
        }
        else if (record.tumor.hasCnsLesions != true
            && record.tumor.hasActiveCnsLesions != true
            && record.tumor.hasBoneLesions != true
            && record.tumor.hasLiverLesions != true
            && record.tumor.hasLungLesions != true
            && record.tumor.hasLymphNodeLesions != true) {
                if (record.tumor.otherLesions.isNullOrEmpty())
                    return EvaluationFactory.fail("HAS_EXTRACRANIAL_METASTASES: No extracranial metastases are present")
                else
                    return EvaluationFactory.warn("HAS_EXTRACRANIAL_METASTASES: Other lesions are defined as free text, a manual check is required to verify extracranial metastases are present")
            }

        return EvaluationFactory.pass("HAS_EXTRACRANIAL_METASTASES: Extracranial metastases are present")
    }
}
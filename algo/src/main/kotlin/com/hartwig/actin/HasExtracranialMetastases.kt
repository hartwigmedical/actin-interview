package com.hartwig.actin

import com.hartwig.actin.clinical.datamodel.ClinicalRecord

//TODO: Update according to README
class HasExtracranialMetastases : EvaluationFunction {
    
    override fun evaluate(record: ClinicalRecord): Evaluation {
        return EvaluationFactory.undetermined(
            "Currently it not determined if there are extracranial metastases",
            "Undetermined extracranial metastases"
        )
    }
}
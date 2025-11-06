package com.hartwig.actin

import com.hartwig.actin.algo.evaluation.Evaluation
import com.hartwig.actin.algo.evaluation.EvaluationFactory
import com.hartwig.actin.algo.evaluation.EvaluationFunction
import com.hartwig.actin.clinical.datamodel.ClinicalRecord

//TODO: Update according to README
class HasExtracranialMetastases : EvaluationFunction {
    
    override fun evaluate(record: ClinicalRecord): Evaluation {
        return EvaluationFactory.undetermined("Undetermined if patient has extracranial metastases")
    }
}
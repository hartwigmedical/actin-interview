package com.hartwig.actin.algo.evaluation.molecular

import com.hartwig.actin.algo.evaluation.EvaluationFactory
import com.hartwig.actin.algo.evaluation.EvaluationFunction
import com.hartwig.actin.datamodel.PatientRecord
import com.hartwig.actin.algo.evaluation.Evaluation

class HasAvailablePDL1Status : EvaluationFunction {

    override fun evaluate(record: PatientRecord): Evaluation {
        return if (IhcTestFilter.allPDL1Tests(record.ihcTests).isNotEmpty()) {
            EvaluationFactory.pass("PD-L1 status available", recoverable = true)
        } else {
            EvaluationFactory.fail("PD-L1 status not available", recoverable = true)
        }
    }
}
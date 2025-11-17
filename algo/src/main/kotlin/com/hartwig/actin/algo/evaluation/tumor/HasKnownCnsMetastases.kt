package com.hartwig.actin.algo.evaluation.tumor

import com.hartwig.actin.algo.evaluation.EvaluationFactory
import com.hartwig.actin.algo.evaluation.EvaluationFunction
import com.hartwig.actin.datamodel.PatientRecord
import com.hartwig.actin.algo.evaluation.Evaluation

class HasKnownCnsMetastases : EvaluationFunction {

    override fun evaluate(record: PatientRecord): Evaluation {
        with(record.tumor) {

            return when {
                hasCnsLesions == true -> {
                    EvaluationFactory.pass("Has CNS metastases")
                }

                hasBrainLesions == true -> {
                    EvaluationFactory.pass("Has brain metastases")
                }

                hasCnsLesions == null || hasBrainLesions == null -> {
                    val message = "Undetermined if CNS metastases present (data missing)"
                    EvaluationFactory.undetermined(message)
                }

                else -> EvaluationFactory.fail("No known CNS metastases present")
            }
        }
    }
}
package com.hartwig.actin.algo.evaluation.tumor

import com.hartwig.actin.algo.evaluation.EvaluationFactory
import com.hartwig.actin.algo.evaluation.EvaluationFunction
import com.hartwig.actin.datamodel.PatientRecord
import com.hartwig.actin.algo.evaluation.Evaluation

class HasKnownActiveBrainMetastases : EvaluationFunction {

    override fun evaluate(record: PatientRecord): Evaluation {
        with(record.tumor) {
            val unknownIfActive = hasActiveBrainLesions == null

            return when {
                unknownIfActive && hasBrainLesions == true -> {
                    EvaluationFactory.undetermined("Brain metastases present but unknown if active (data missing)")
                }

                unknownIfActive && hasBrainLesions == null -> {
                    EvaluationFactory.undetermined("Undetermined if active brain metastases present (brain lesions data missing)")
                }

                hasActiveBrainLesions == true -> EvaluationFactory.pass("Has active brain metastases")

                else -> EvaluationFactory.fail("No known active brain metastases present")
            }
        }
    }

}
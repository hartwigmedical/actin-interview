package com.hartwig.actin.algo.evaluation.tumor

import com.hartwig.actin.algo.evaluation.EvaluationFactory
import com.hartwig.actin.algo.evaluation.EvaluationFunction
import com.hartwig.actin.datamodel.PatientRecord
import com.hartwig.actin.algo.evaluation.Evaluation

class HasUnresectablePeritonealMetastases : EvaluationFunction {

    override fun evaluate(record: PatientRecord): Evaluation {
        val certainPeritonealMetastasesEvaluation = TumorEvaluationFunctions.hasPeritonealMetastases(record.tumor)

        return when {
            certainPeritonealMetastasesEvaluation == null -> {
                EvaluationFactory.undetermined("Unresectable peritoneal metastases undetermined (metastases data missing)")
            }

            certainPeritonealMetastasesEvaluation -> {
                EvaluationFactory.warn("Undetermined if peritoneal metastases are unresectable")
            }

            else -> {
                EvaluationFactory.fail("No unresectable peritoneal metastases")
            }
        }
    }
}
package com.hartwig.actin.algo.evaluation.tumor

import com.hartwig.actin.algo.evaluation.EvaluationFactory
import com.hartwig.actin.algo.evaluation.Evaluation

internal object TumorMetastasisEvaluator {

    fun evaluate(hasLesions: Boolean?, metastasisType: String): Evaluation {
        return when (hasLesions) {
            true -> {
                EvaluationFactory.pass("Has $metastasisType metastases")
            }
            null -> EvaluationFactory.undetermined("Undetermined if patient has $metastasisType metastases (missing lesion data)")
            else -> {
                EvaluationFactory.fail("No $metastasisType metastases")
            }
        }
    }
}
package com.hartwig.actin.algo.evaluation.molecular

import com.hartwig.actin.algo.evaluation.EvaluationFactory
import com.hartwig.actin.algo.evaluation.EvaluationFunction
import com.hartwig.actin.datamodel.PatientRecord
import com.hartwig.actin.algo.evaluation.Evaluation

class HasAvailableProteinExpression(private val protein: String) : EvaluationFunction {

    override fun evaluate(record: PatientRecord): Evaluation {
        return if (IhcTestFilter.mostRecentAndUnknownDateIhcTestsForItem(record.ihcTests, protein).isNotEmpty()) {
            EvaluationFactory.pass("$protein expression available by IHC", recoverable = true)
        } else {
            EvaluationFactory.fail("$protein expression not available by IHC", recoverable = true)
        }
    }
}
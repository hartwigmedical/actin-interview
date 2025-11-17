package com.hartwig.actin.algo.evaluation.tumor

import com.hartwig.actin.algo.evaluation.EvaluationFactory
import com.hartwig.actin.algo.evaluation.EvaluationFunction
import com.hartwig.actin.datamodel.PatientRecord
import com.hartwig.actin.algo.evaluation.Evaluation

class HasMeasurableDisease : EvaluationFunction {

    override fun evaluate(record: PatientRecord): Evaluation {
        val hasMeasurableDisease = record.tumor.hasMeasurableDisease
            ?: return EvaluationFactory.undetermined("Measurable disease undetermined (data missing)")
        return if (hasMeasurableDisease) {
            EvaluationFactory.pass("Has measurable disease")
        } else {
            EvaluationFactory.fail("No measurable disease")
        }
    }
}
package com.hartwig.actin.algo.evaluation.molecular

import com.hartwig.actin.algo.evaluation.Evaluation
import com.hartwig.actin.algo.evaluation.EvaluationFactory
import com.hartwig.actin.algo.evaluation.EvaluationFunction
import com.hartwig.actin.algo.evaluation.util.Format
import com.hartwig.actin.datamodel.PatientRecord

class AnyGeneFromSetIsNotExpressed(private val genes: Set<String>) : EvaluationFunction {

    override fun evaluate(record: PatientRecord): Evaluation {
        return EvaluationFactory.undetermined(
            "Non-expression of ${Format.concat(genes)} in RNA undetermined",
            isMissingMolecularResultForEvaluation = true
        )
    }
}
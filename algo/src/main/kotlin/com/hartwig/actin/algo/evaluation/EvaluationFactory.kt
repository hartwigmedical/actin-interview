package com.hartwig.actin.algo.evaluation

object EvaluationFactory {

    fun pass(message: String, recoverable: Boolean = false, inclusionEvents: Set<String> = emptySet()): Evaluation {
        return Evaluation(EvaluationResult.PASS, recoverable, passMessages = setOf(message), inclusionMolecularEvents = inclusionEvents)
    }

    fun fail(message: String, recoverable: Boolean = false, isMissingMolecularResultForEvaluation: Boolean = false): Evaluation {
        return Evaluation(
            EvaluationResult.FAIL,
            recoverable,
            failMessages = setOf(message),
            isMissingMolecularResultForEvaluation = isMissingMolecularResultForEvaluation
        )
    }

    fun undetermined(message: String, recoverable: Boolean = false, isMissingMolecularResultForEvaluation: Boolean = false): Evaluation {
        return Evaluation(
            EvaluationResult.UNDETERMINED,
            recoverable,
            undeterminedMessages = setOf(message),
            isMissingMolecularResultForEvaluation = isMissingMolecularResultForEvaluation
        )
    }

    fun warn(
        message: String,
        recoverable: Boolean = false,
        isMissingMolecularResultForEvaluation: Boolean = false,
        inclusionEvents: Set<String> = emptySet()
    ): Evaluation {
        return Evaluation(
            EvaluationResult.WARN,
            recoverable,
            warnMessages = setOf(message),
            isMissingMolecularResultForEvaluation = isMissingMolecularResultForEvaluation,
            inclusionMolecularEvents = inclusionEvents
        )
    }
}
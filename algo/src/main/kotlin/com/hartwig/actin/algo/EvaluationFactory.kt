package com.hartwig.actin

object EvaluationFactory {

    fun pass(message: String, recoverable: Boolean = false, inclusionEvents: Set<String> = emptySet()): Evaluation {
        return Evaluation(EvaluationResult.PASS, recoverable, passMessages = setOf(message), inclusionMolecularEvents = inclusionEvents)
    }

    fun fail(message: String, recoverable: Boolean = false): Evaluation {
        return Evaluation(EvaluationResult.FAIL, recoverable, setOf(message))
    }

    fun undetermined(message: String, recoverable: Boolean = false): Evaluation {
        return Evaluation(EvaluationResult.UNDETERMINED, recoverable, setOf(message))
    }

    fun warn(message: String, recoverable: Boolean = false): Evaluation {
        return Evaluation(EvaluationResult.WARN, recoverable, setOf(message))
    }
}
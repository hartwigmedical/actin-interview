package com.hartwig.actin

object EvaluationFactory {

    fun pass(
        specificMessage: String, generalMessage: String? = null, recoverable: Boolean = false, inclusionEvents: Set<String> = emptySet()
    ): Evaluation {
        return Evaluation(
            recoverable = recoverable,
            result = EvaluationResult.PASS,
            passSpecificMessages = setOf(specificMessage),
            passGeneralMessages = setOf(generalMessage ?: specificMessage),
            inclusionMolecularEvents = inclusionEvents
        )
    }

    fun fail(specificMessage: String, generalMessage: String? = null, recoverable: Boolean = false): Evaluation {
        return createFail(recoverable, specificMessage, setOf(generalMessage ?: specificMessage))
    }

    fun undetermined(specificMessage: String, generalMessage: String? = null, recoverable: Boolean = false): Evaluation {
        return createUndetermined(recoverable, specificMessage, setOf(generalMessage ?: specificMessage))
    }

    private fun createFail(recoverable: Boolean, specificMessage: String, generalMessages: Set<String>) = Evaluation(
        recoverable = recoverable,
        result = EvaluationResult.FAIL,
        failSpecificMessages = setOf(specificMessage),
        failGeneralMessages = generalMessages
    )

    private fun createUndetermined(recoverable: Boolean, specificMessage: String, generalMessages: Set<String>) = Evaluation(
        recoverable = recoverable,
        result = EvaluationResult.UNDETERMINED,
        undeterminedSpecificMessages = setOf(specificMessage),
        undeterminedGeneralMessages = generalMessages
    )
}
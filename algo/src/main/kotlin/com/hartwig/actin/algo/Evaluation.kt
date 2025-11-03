package com.hartwig.actin

data class Evaluation(
    val result: EvaluationResult,
    val recoverable: Boolean,
    val inclusionMolecularEvents: Set<String> = emptySet(),
    val exclusionMolecularEvents: Set<String> = emptySet(),
    val passMessages: Set<String> = emptySet(),
    val warnMessages: Set<String> = emptySet(),
    val undeterminedMessages: Set<String> = emptySet(),
    val failMessages: Set<String> = emptySet(),
    val isMissingMolecularResultForEvaluation: Boolean = false
) {
    fun passMessagesStrings() = passMessages.map { it.toString() }.toSet()
    fun warnMessagesStrings() = warnMessages.map { it.toString() }.toSet()
    fun undeterminedMessagesStrings() = undeterminedMessages.map { it.toString() }.toSet()
    fun failMessagesStrings() = failMessages.map { it.toString() }.toSet()

    fun addMessagesAndEvents(other: Evaluation): Evaluation {
        return Evaluation(
            result = result,
            recoverable = recoverable,
            inclusionMolecularEvents = inclusionMolecularEvents + other.inclusionMolecularEvents,
            exclusionMolecularEvents = exclusionMolecularEvents + other.exclusionMolecularEvents,
            passMessages = passMessages + other.passMessages,
            warnMessages = warnMessages + other.warnMessages,
            undeterminedMessages = undeterminedMessages + other.undeterminedMessages,
            failMessages = failMessages + other.failMessages,
            isMissingMolecularResultForEvaluation = if (result == EvaluationResult.PASS) false else isMissingMolecularResultForEvaluation || other.isMissingMolecularResultForEvaluation
        )
    }
}

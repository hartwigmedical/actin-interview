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
)

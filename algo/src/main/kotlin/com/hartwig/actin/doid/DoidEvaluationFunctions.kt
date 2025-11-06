package com.hartwig.actin.doid

import com.hartwig.actin.algo.evaluation.EvaluationResult
import com.hartwig.actin.algo.evaluation.util.ValueComparison.stringCaseInsensitivelyMatchesQueryCollection
import kotlin.collections.any
import kotlin.collections.flatMap
import kotlin.collections.intersect
import kotlin.collections.isNotEmpty
import kotlin.collections.isNullOrEmpty
import kotlin.collections.map
import kotlin.collections.reduce
import kotlin.collections.toSet

object DoidEvaluationFunctions {

    fun hasConfiguredDoids(tumorDoids: Set<String>?): Boolean {
        return !tumorDoids.isNullOrEmpty()
    }

    fun isOfDoidType(doidModel: DoidModel, tumorDoids: Set<String>?, doidToMatch: String): Boolean {
        return isOfAtLeastOneDoidType(doidModel, tumorDoids, setOf(doidToMatch))
    }

    fun isOfAtLeastOneDoidType(doidModel: DoidModel, tumorDoids: Set<String>?, doidsToMatch: Set<String>): Boolean {
        return setsIntersect(createFullExpandedDoidTree(doidModel, tumorDoids), doidsToMatch)
    }

    fun isOfAtLeastOneDoidTerm(doidModel: DoidModel, tumorDoids: Set<String>?, doidTermsToMatch: Set<String>): Boolean {
        return createFullExpandedDoidTree(doidModel, tumorDoids).any { doid ->
            val term = doidModel.resolveTermForDoid(doid)
            if (term == null) {
                false
            } else {
                stringCaseInsensitivelyMatchesQueryCollection(term, doidTermsToMatch)
            }
        }
    }

    fun isOfExactDoid(tumorDoids: Set<String>?, doidToMatch: String): Boolean {
        return tumorDoids == setOf(doidToMatch)
    }

    fun isOfDoidCombinationType(tumorDoids: Set<String>?, validDoidCombination: Set<String>): Boolean {
        return hasAtLeastOneCombinationOfDoids(tumorDoids, setOf(validDoidCombination))
    }

    fun isOfExclusiveDoidType(doidModel: DoidModel, tumorDoids: Set<String>?, doidToMatch: String): Boolean {
        return evaluateAllDoidsMatchWithFailAndWarns(
            doidModel, tumorDoids, setOf(doidToMatch), emptySet(), emptySet()
        ) == EvaluationResult.PASS
    }

    fun evaluateAllDoidsMatchWithFailAndWarns(
        doidModel: DoidModel, tumorDoids: Set<String>?, doidsToMatch: Set<String>, failDoids: Set<String>, warnDoids: Set<String>
    ): EvaluationResult {
        if (tumorDoids == null) {
            return EvaluationResult.FAIL
        }
        val (allDoidsMatch, hasFailDoid, hasWarnDoid) = tumorDoids.map { doid ->
            val expandedDoids = doidModel.doidWithParents(doid)
            Triple(
                setsIntersect(expandedDoids, doidsToMatch),
                setsIntersect(expandedDoids, failDoids),
                setsIntersect(expandedDoids, warnDoids)
            )
        }.reduce { acc, triple -> Triple(acc.first && triple.first, acc.second || triple.second, acc.third || triple.third) }

        return when {
            !allDoidsMatch || hasFailDoid -> EvaluationResult.FAIL
            hasWarnDoid -> EvaluationResult.WARN
            else -> EvaluationResult.PASS
        }
    }

    fun hasAtLeastOneCombinationOfDoids(tumorDoids: Set<String>?, validDoidCombinations: Set<Set<String>>): Boolean {
        return tumorDoids != null && validDoidCombinations.any(tumorDoids::containsAll)
    }

    fun createFullExpandedDoidTree(doidModel: DoidModel, doidsToExpand: Set<String>?): Set<String> {
        return doidsToExpand?.flatMap(doidModel::doidWithParents)?.toSet() ?: emptySet()
    }

    private fun <T> setsIntersect(setA: Set<T>, setB: Set<T>): Boolean {
        return setA.intersect(setB).isNotEmpty()
    }
}
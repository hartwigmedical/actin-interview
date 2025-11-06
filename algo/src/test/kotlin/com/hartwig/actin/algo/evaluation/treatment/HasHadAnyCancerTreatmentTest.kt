package com.hartwig.actin.algo.evaluation.treatment

import com.hartwig.actin.algo.evaluation.EvaluationAssert.assertEvaluation
import com.hartwig.actin.algo.evaluation.EvaluationResult
import com.hartwig.actin.datamodel.clinical.AtcLevel
import com.hartwig.actin.datamodel.clinical.TreatmentTestFactory
import com.hartwig.actin.datamodel.clinical.treatment.TreatmentCategory
import org.junit.Test

private val ATC_LEVELS = AtcLevel(code = "category to find", name = "")

class HasHadAnyCancerTreatmentTest {

    private val functionWithoutCategoriesToIgnore = HasHadAnyCancerTreatment(emptySet(), setOf(ATC_LEVELS))
    private val functionWithCategoriesToIgnore = HasHadAnyCancerTreatment(setOf(TreatmentCategory.CHEMOTHERAPY, TreatmentCategory.HORMONE_THERAPY), setOf(ATC_LEVELS))

    @Test
    fun `Should fail when treatment history is empty`() {
        assertEvaluation(
            EvaluationResult.FAIL,
            functionWithoutCategoriesToIgnore.evaluate(TreatmentTestFactory.withTreatmentHistory(emptyList()))
        )
        assertEvaluation(
            EvaluationResult.FAIL,
            functionWithCategoriesToIgnore.evaluate(TreatmentTestFactory.withTreatmentHistory(emptyList()))
        )
    }

    @Test
    fun `Should pass if treatment history is not empty and contains treatments which should not be ignored`() {
        val treatments = TreatmentTestFactory.treatment("Radiotherapy", false, setOf(TreatmentCategory.RADIOTHERAPY))
        val treatmentHistory = listOf(TreatmentTestFactory.treatmentHistoryEntry(setOf(treatments)))
        assertEvaluation(
            EvaluationResult.PASS,
            functionWithoutCategoriesToIgnore.evaluate(TreatmentTestFactory.withTreatmentHistory(treatmentHistory))
        )
        assertEvaluation(
            EvaluationResult.PASS,
            functionWithCategoriesToIgnore.evaluate(TreatmentTestFactory.withTreatmentHistory(treatmentHistory))
        )
    }

    @Test
    fun `Should pass if treatment history is not empty and contains treatment that should not be ignored`() {
        val treatment1 = TreatmentTestFactory.treatment("Radiotherapy", false, setOf(TreatmentCategory.RADIOTHERAPY))
        val treatment2 = TreatmentTestFactory.treatment("Chemotherapy", true, setOf(TreatmentCategory.CHEMOTHERAPY))
        val treatmentHistory = listOf(TreatmentTestFactory.treatmentHistoryEntry(setOf(treatment1, treatment2)))
        assertEvaluation(
            EvaluationResult.PASS,
            functionWithoutCategoriesToIgnore.evaluate(TreatmentTestFactory.withTreatmentHistory(treatmentHistory))
        )
        assertEvaluation(
            EvaluationResult.PASS,
            functionWithCategoriesToIgnore.evaluate(TreatmentTestFactory.withTreatmentHistory(treatmentHistory))
        )
    }

    @Test
    fun `Should fail if treatment history contains only treatments which should be ignored`() {
        val treatment1 = TreatmentTestFactory.treatment("Chemotherapy", true, setOf(TreatmentCategory.CHEMOTHERAPY))
        val treatment2 = TreatmentTestFactory.treatment("Chemotherapy", true, setOf(TreatmentCategory.HORMONE_THERAPY))
        val treatmentHistory = listOf(TreatmentTestFactory.treatmentHistoryEntry(setOf(treatment1, treatment2)))
        assertEvaluation(
            EvaluationResult.FAIL,
            functionWithCategoriesToIgnore.evaluate(TreatmentTestFactory.withTreatmentHistory(treatmentHistory))
        )
    }
}
package com.hartwig.actin.algo.evaluation.tumor

import com.hartwig.actin.algo.evaluation.EvaluationAssert.assertEvaluation
import com.hartwig.actin.datamodel.TestPatientFactory
import com.hartwig.actin.algo.evaluation.EvaluationResult
import org.junit.Test

class HasDocumentationOfTumorTypeTest {
    @Test
    fun canEvaluate() {
        val function = HasDocumentationOfTumorType("Cytological")
        assertEvaluation(EvaluationResult.PASS, function.evaluate(TestPatientFactory.createMinimalTestWGSPatientRecord()))
    }
}
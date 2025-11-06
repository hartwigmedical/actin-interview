package com.hartwig.actin.algo.evaluation.molecular

import com.hartwig.actin.algo.evaluation.EvaluationFactory
import com.hartwig.actin.algo.evaluation.EvaluationFunction
import com.hartwig.actin.datamodel.PatientRecord
import com.hartwig.actin.algo.evaluation.Evaluation
import com.hartwig.actin.datamodel.clinical.treatment.IhcTest
import com.hartwig.actin.datamodel.molecular.ExperimentType
import com.hartwig.actin.datamodel.molecular.MolecularHistory
import com.hartwig.actin.datamodel.molecular.driver.VirusType

class HasKnownHPVStatus : EvaluationFunction {

    override fun evaluate(record: PatientRecord): Evaluation {
        val (indeterminateIhcTestsForHpv, determinateIhcTestsForHpv) = record.ihcTests
            .filter { (it.item.contains("HPV") || it.item.contains("Human papillomavirus")) }
            .partition(IhcTest::impliesPotentialIndeterminateStatus)
        val molecularRecords = MolecularHistory(record.molecularTests).allOrangeMolecularRecords()

        return when {
            molecularRecords.any { it.experimentType == ExperimentType.HARTWIG_WHOLE_GENOME && it.hasSufficientQuality } -> {
                EvaluationFactory.pass("HPV status known (by WGS test)")
            }

            record.molecularTests.any { it.drivers.viruses.any { it.type == VirusType.HPV } } -> EvaluationFactory.pass("HPV status known")

            determinateIhcTestsForHpv.isNotEmpty() -> EvaluationFactory.pass("HPV status known (by IHC test)")

            indeterminateIhcTestsForHpv.isNotEmpty() -> EvaluationFactory.warn("HPV tested before but indeterminate status")

            molecularRecords.any { it.experimentType == ExperimentType.HARTWIG_WHOLE_GENOME } -> {
                EvaluationFactory.fail("HPV status undetermined (WGS contained no tumor cells)")
            }

            else -> EvaluationFactory.fail("HPV status not known")
        }
    }
}
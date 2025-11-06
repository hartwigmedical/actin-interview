package com.hartwig.actin.algo.evaluation.treatment

import com.hartwig.actin.algo.evaluation.EvaluationFactory
import com.hartwig.actin.algo.evaluation.EvaluationFunction
import com.hartwig.actin.datamodel.PatientRecord
import com.hartwig.actin.algo.evaluation.Evaluation
import java.time.LocalDate

class IsNotParticipatingInAnotherInterventionalTrial(
    private val minStopDate: LocalDate
) : EvaluationFunction {

    override fun evaluate(record: PatientRecord): Evaluation {
        val hadRecentTrialTreatment =
            record.oncologicalHistory.any { it.isTrial && TreatmentVersusDateFunctions.treatmentSinceMinDate(it, minStopDate, false) }

        return when {
            hadRecentTrialTreatment -> {
                EvaluationFactory.warn("Recent trial treatment - undetermined if patient is participating in another interventional trial")
            }

            else -> EvaluationFactory.pass("Assumed that patient is not participating in another interventional trial")
        }
    }
}
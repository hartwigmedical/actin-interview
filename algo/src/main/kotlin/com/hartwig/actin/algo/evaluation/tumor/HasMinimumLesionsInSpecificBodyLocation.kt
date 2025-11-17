package com.hartwig.actin.algo.evaluation.tumor

import com.hartwig.actin.algo.evaluation.EvaluationFactory
import com.hartwig.actin.algo.evaluation.EvaluationFunction
import com.hartwig.actin.datamodel.PatientRecord
import com.hartwig.actin.algo.evaluation.Evaluation
import com.hartwig.actin.datamodel.clinical.BodyLocationCategory

class HasMinimumLesionsInSpecificBodyLocation(
    private val minLesions: Int, private val bodyLocation: BodyLocationCategory
) : EvaluationFunction {

    override fun evaluate(record: PatientRecord): Evaluation {
        val messageEnding = "at least $minLesions lesions in ${bodyLocation.display()}"

        val hasLesions = with(record.tumor) {
            when (bodyLocation) {
                BodyLocationCategory.BONE -> hasBoneLesions
                BodyLocationCategory.BRAIN -> hasBrainLesions
                BodyLocationCategory.CNS -> hasCnsLesions
                BodyLocationCategory.LIVER -> hasLiverLesions
                BodyLocationCategory.LUNG -> hasLungLesions
                BodyLocationCategory.LYMPH_NODE -> hasLymphNodeLesions
                else -> return EvaluationFactory.undetermined("Undetermined if patient has $messageEnding")
            }
        }

        return when {
            minLesions <= 1 && hasLesions == true -> {
                EvaluationFactory.pass("Patient has $messageEnding")
            }

            hasLesions != false -> {
                EvaluationFactory.undetermined("Undetermined if patient has $messageEnding")
            }

            else -> EvaluationFactory.fail("Does not have $messageEnding")
        }
    }
}
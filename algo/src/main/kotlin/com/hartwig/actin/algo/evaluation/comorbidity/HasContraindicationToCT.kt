package com.hartwig.actin.algo.evaluation.comorbidity

import com.hartwig.actin.algo.evaluation.Evaluation
import com.hartwig.actin.algo.evaluation.EvaluationFactory
import com.hartwig.actin.algo.evaluation.EvaluationFunction
import com.hartwig.actin.algo.evaluation.util.Format
import com.hartwig.actin.algo.evaluation.util.ValueComparison.stringCaseInsensitivelyMatchesQueryCollection
import com.hartwig.actin.datamodel.IcdConstants
import com.hartwig.actin.datamodel.PatientRecord
import com.hartwig.actin.datamodel.clinical.IcdCode
import com.hartwig.actin.icd.IcdModel

class HasContraindicationToCT(private val icdModel: IcdModel) : EvaluationFunction {

    override fun evaluate(record: PatientRecord): Evaluation {
        val targetIcdCode = setOf(IcdCode(IcdConstants.KIDNEY_FAILURE_BLOCK))

        val matchingComorbidities = icdModel.findInstancesMatchingAnyIcdCode(record.comorbidities, targetIcdCode).fullMatches

        val comorbiditiesMatchingString = record.comorbidities.filter { comorbidity ->
            comorbidity.name?.let {
                stringCaseInsensitivelyMatchesQueryCollection(it, COMORBIDITIES_THAT_ARE_CONTRAINDICATIONS_TO_CT)
            } == true
        }

        val conditionString = Format.concatItemsWithAnd(matchingComorbidities)
        val messageStart = "Potential CT contraindication: "

        return when {
            matchingComorbidities.isNotEmpty() -> EvaluationFactory.pass(messageStart + conditionString, recoverable = true)

            comorbiditiesMatchingString.isNotEmpty() -> {
                EvaluationFactory.pass(messageStart + Format.concatItemsWithAnd(comorbiditiesMatchingString), recoverable = true)
            }

            else -> EvaluationFactory.fail("No potential contraindications to CT")
        }
    }

    companion object {
        val COMORBIDITIES_THAT_ARE_CONTRAINDICATIONS_TO_CT = setOf("claustrophobia", "contrast agent")
    }
}
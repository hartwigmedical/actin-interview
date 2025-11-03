package com.hartwig.actin

import com.hartwig.actin.datamodel.PatientRecord

interface EvaluationFunction {

    fun evaluate(record: PatientRecord): Evaluation
    fun stringCaseInsensitivelyMatchesQueryCollection(it: Any, comorbiditiesThatAreContraindicationsToCt: Set<String>)
}
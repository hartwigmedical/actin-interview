package com.hartwig.actin

import com.hartwig.actin.clinical.datamodel.ClinicalRecord

interface EvaluationFunction {

    fun evaluate(record: ClinicalRecord): Evaluation
}
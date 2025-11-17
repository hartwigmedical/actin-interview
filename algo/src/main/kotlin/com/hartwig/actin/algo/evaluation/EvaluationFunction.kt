package com.hartwig.actin.algo.evaluation

import com.hartwig.actin.datamodel.PatientRecord

interface EvaluationFunction {

    fun evaluate(record: PatientRecord): Evaluation
}
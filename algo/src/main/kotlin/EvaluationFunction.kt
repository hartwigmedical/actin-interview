import datamodel.Evaluation
import datamodel.clinical.ClinicalRecord

interface EvaluationFunction {

    fun evaluate(record: ClinicalRecord): Evaluation
}
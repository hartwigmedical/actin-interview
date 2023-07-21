import datamodel.Evaluation
import datamodel.clinical.ClinicalRecord

//TODO: Update according to README
class HasExtracranialMetastases internal constructor() : EvaluationFunction {
    override fun evaluate(record: ClinicalRecord): Evaluation {
        return EvaluationFactory.undetermined(
            "Currently it not determined if there are extracranial metastases",
            "Undetermined extracranial metastases"
        )
    }
}
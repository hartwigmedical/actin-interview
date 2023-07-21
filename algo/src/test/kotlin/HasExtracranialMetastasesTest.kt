import datamodel.EvaluationResult
import datamodel.clinical.ClinicalRecord
import datamodel.clinical.Gender
import datamodel.clinical.ImmutableClinicalRecord
import datamodel.clinical.ImmutableClinicalStatus
import datamodel.clinical.ImmutablePatientDetails
import datamodel.clinical.ImmutableTumorDetails
import datamodel.clinical.PatientDetails
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class HasExtracranialMetastasesTest {
    
    @Test
    fun shouldFailIfPatientHasNoLesions() {
        val record = createMinimalTestClinicalRecord()
        val evaluation = FUNCTION.evaluate(record)
        assertEquals(EvaluationResult.FAIL, evaluation.result())
        assertTrue(evaluation.failGeneralMessages().isNotEmpty())
    }
    
    companion object {
        private val FUNCTION = HasExtracranialMetastases()
        
        private fun createMinimalTestClinicalRecord(): ClinicalRecord {
            return ImmutableClinicalRecord.builder()
                .patientId("ACTN01029999")
                .patient(createTestPatientDetails())
                .tumor(ImmutableTumorDetails.builder().build())
                .clinicalStatus(ImmutableClinicalStatus.builder().build())
                .build()
        }

        private fun createTestPatientDetails(): PatientDetails {
            return ImmutablePatientDetails.builder()
                .gender(Gender.MALE)
                .birthYear(1950)
                .registrationDate(LocalDate.now())
                .questionnaireDate(LocalDate.now())
                .build()
        }
    }
}
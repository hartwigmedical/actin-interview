package com.hartwig.actin

import com.hartwig.actin.clinical.datamodel.ClinicalRecord
import com.hartwig.actin.clinical.datamodel.ClinicalStatus
import com.hartwig.actin.clinical.datamodel.Gender
import com.hartwig.actin.clinical.datamodel.PatientDetails
import com.hartwig.actin.clinical.datamodel.TumorDetails
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime

class HasExtracranialMetastasesTest {
    
    @Test
    fun `Should be undetermined if patient lesion details are not provided`() {
        val record = createMinimalTestClinicalRecord()
        val evaluation = FUNCTION.evaluate(record)
        assertEquals(EvaluationResult.UNDETERMINED, evaluation.result)
    }

    @Test
    fun `Should fail if patient has no lesions`() {
        val record = createMinimalTestClinicalRecord().copy(
            tumor = TumorDetails(
                hasBoneLesions = false,
                hasBrainLesions = false,
                hasActiveCnsLesions = false,
                hasCnsLesions = false,
                hasLiverLesions = false,
                hasLungLesions = false,
                hasLymphNodeLesions = false,
                otherLesions = emptyList()
            )
        )
        val evaluation = FUNCTION.evaluate(record)
        assertEquals(EvaluationResult.FAIL, evaluation.result)
    }
    
    @Test
    fun `Should pass if patient has extracranial lesions`() {
        val record = createMinimalTestClinicalRecord().copy(
            tumor = TumorDetails(
                hasBoneLesions = true
            )
        )
        val evaluation = FUNCTION.evaluate(record)
        assertEquals(EvaluationResult.PASS, evaluation.result)
    }
    
    companion object {
        private const val TEST_PATIENT = "ACTN01029999"
        private val TODAY = LocalDateTime.now().toLocalDate()
        private const val DAYS_SINCE_QUESTIONNAIRE = 10
        private const val DAYS_SINCE_REGISTRATION = 15
        private val FUNCTION = HasExtracranialMetastases()

        fun createMinimalTestClinicalRecord(): ClinicalRecord {
            return ClinicalRecord(
                patientId = TEST_PATIENT,
                patient = createTestPatientDetails(),
                tumor = TumorDetails(),
                clinicalStatus = ClinicalStatus(),
                oncologicalHistory = emptyList(),
                priorSecondPrimaries = emptyList(),
                priorOtherConditions = emptyList(),
                priorMolecularTests = emptyList(),
                complications = null,
                labValues = emptyList(),
                toxicities = emptyList(),
                intolerances = emptyList(),
                surgeries = emptyList(),
                bodyWeights = emptyList(),
                vitalFunctions = emptyList(),
                bloodTransfusions = emptyList(),
                medications = emptyList()
            )
        }

        private fun createTestPatientDetails(): PatientDetails {
            return PatientDetails(
                gender = Gender.MALE,
                birthYear = 1950,
                registrationDate = TODAY.minusDays(DAYS_SINCE_REGISTRATION.toLong()),
                questionnaireDate = TODAY.minusDays(DAYS_SINCE_QUESTIONNAIRE.toLong()),
                otherMolecularPatientId = null
            )
        }
    }
}
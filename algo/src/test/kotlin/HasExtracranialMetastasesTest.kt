package com.hartwig.actin

import com.hartwig.actin.clinical.datamodel.ClinicalRecord
import com.hartwig.actin.clinical.datamodel.ClinicalStatus
import com.hartwig.actin.clinical.datamodel.Gender
import com.hartwig.actin.clinical.datamodel.PatientDetails
import com.hartwig.actin.clinical.datamodel.TumorDetails
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime

private const val TEST_PATIENT = "ACTN01029999"
private const val DAYS_SINCE_QUESTIONNAIRE = 10
private const val DAYS_SINCE_REGISTRATION = 15

class HasExtracranialMetastasesTest {
    private val today = LocalDateTime.now().toLocalDate()
    private val function = HasExtracranialMetastases()
    private val tumorWithNoLesions = TumorDetails(
        hasBoneLesions = false,
        hasBrainLesions = false,
        hasActiveCnsLesions = false,
        hasCnsLesions = false,
        hasLiverLesions = false,
        hasLungLesions = false,
        hasLymphNodeLesions = false,
        otherLesions = emptyList()
    )

    @Test
    fun `Should be undetermined if any patient lesion details are not provided`() {
        listOf(
            TumorDetails(),
            tumorWithNoLesions.copy(hasBoneLesions = null),
            tumorWithNoLesions.copy(hasLiverLesions = null),
            tumorWithNoLesions.copy(hasLungLesions = null),
            tumorWithNoLesions.copy(hasLymphNodeLesions = null),
            tumorWithNoLesions.copy(otherLesions = null)
        )
            .map { tumor -> createMinimalTestClinicalRecord(tumor) }
            .forEach { record ->
                val evaluation = function.evaluate(record)
                assertEquals(EvaluationResult.UNDETERMINED, evaluation.result)
            }
    }

    @Test
    fun `Should fail if patient has no lesions`() {
        val record = createMinimalTestClinicalRecord(tumorWithNoLesions)
        val evaluation = function.evaluate(record)
        assertEquals(EvaluationResult.FAIL, evaluation.result)
    }
    
    @Test
    fun `Should pass if patient has any extracranial lesions`() {
        listOf(
            TumorDetails(hasBoneLesions = true),
            TumorDetails(hasLiverLesions = true),
            TumorDetails(hasLungLesions = true),
            TumorDetails(hasLymphNodeLesions = true),
            TumorDetails(otherLesions = listOf("adrenal"))
        )
            .map { tumor -> createMinimalTestClinicalRecord().copy(tumor = tumor) }
            .forEach { record ->
                val evaluation = function.evaluate(record)
                assertEquals(EvaluationResult.PASS, evaluation.result)
            }
    }
    
    fun createMinimalTestClinicalRecord(tumor: TumorDetails = TumorDetails()): ClinicalRecord {
        return ClinicalRecord(
            patientId = TEST_PATIENT,
            patient = createTestPatientDetails(),
            tumor = tumor,
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
            registrationDate = today.minusDays(DAYS_SINCE_REGISTRATION.toLong()),
            questionnaireDate = today.minusDays(DAYS_SINCE_QUESTIONNAIRE.toLong()),
            otherMolecularPatientId = null
        )
    }
}
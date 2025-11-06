package com.hartwig.actin.molecular.interpretation

import com.hartwig.actin.datamodel.molecular.MolecularTest
import com.hartwig.actin.datamodel.molecular.characteristics.HomologousRecombination
import com.hartwig.actin.datamodel.molecular.evidence.Actionable
import com.hartwig.actin.datamodel.molecular.evidence.ClinicalEvidence
import com.hartwig.actin.datamodel.molecular.evidence.ExternalTrial
import com.hartwig.actin.datamodel.molecular.evidence.TreatmentEvidence
import kotlin.collections.filter
import kotlin.collections.isNotEmpty
import kotlin.collections.map
import kotlin.collections.plus
import kotlin.to

object ActionableAndEvidenceFactory {

    fun createTreatmentEvidences(
        molecular: MolecularTest,
        filter: Function1<Actionable, Boolean> = defaultFilter()
    ): List<Pair<Actionable, Set<TreatmentEvidence>>> {
        return actionableAndEvidences(actionableList(molecular, filter)) { evidence -> evidence.treatmentEvidence }
    }

    fun createTrialEvidences(
        molecular: MolecularTest,
        filter: Function1<Actionable, Boolean> = defaultFilter()
    ): List<Pair<Actionable, Set<ExternalTrial>>> {
        return actionableAndEvidences(actionableList(molecular, filter)) { evidence -> evidence.eligibleTrials }
    }

    fun defaultFilter(): Function1<Actionable, Boolean> {
        return { a ->
            if (a is HomologousRecombination) {
                a.isDeficient
            } else {
                true
            }
        }
    }

    private fun actionableList(molecular: MolecularTest, filter: Function1<Actionable, Boolean>): List<Actionable> {
        return if (!molecular.hasSufficientQuality) {
            emptyList()
        } else {
            val drivers = molecular.drivers
            val characteristics = molecular.characteristics
            return (drivers.variants + drivers.copyNumbers + drivers.homozygousDisruptions + drivers.disruptions + drivers.fusions + drivers.viruses +
                    listOfNotNull(
                        characteristics.microsatelliteStability,
                        characteristics.homologousRecombination,
                        characteristics.tumorMutationalBurden,
                        characteristics.tumorMutationalLoad
                    )).filter(filter)
        }
    }

    private fun <E> actionableAndEvidences(
        actionableList: List<Actionable>, valueFunction: (ClinicalEvidence) -> Set<E>
    ): List<Pair<Actionable, Set<E>>> {
        return actionableList.map { it to valueFunction(it.evidence) }.filter { it.second.isNotEmpty() }
    }
}
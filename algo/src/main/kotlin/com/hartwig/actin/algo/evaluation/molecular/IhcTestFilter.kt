package com.hartwig.actin.algo.evaluation.molecular

import com.hartwig.actin.datamodel.clinical.treatment.IhcTest
import kotlin.collections.all
import kotlin.collections.filter
import kotlin.collections.flatMap
import kotlin.collections.groupBy
import kotlin.collections.maxOf
import kotlin.collections.partition
import kotlin.collections.plus
import kotlin.collections.toSet
import kotlin.text.equals
import kotlin.to

object IhcTestFilter {

    private const val PD_L1 = "PD-L1"

    // For lung cancer the measurement type for PD-L1 is assumed to be TPS if not otherwise specified
    fun allPDL1Tests(ihcTests: List<IhcTest>, measureToFind: String? = null, isLungCancer: Boolean? = null): List<IhcTest> {
        val allPDL1Tests = mostRecentAndUnknownDateIhcTests(ihcTests).filter { test -> test.item == PD_L1 }
        return if (measureToFind == null || (measureToFind == "TPS" && isLungCancer == true && allPDL1Tests.all { it.measure == null })) {
            allPDL1Tests
        } else {
            allPDL1Tests.filter { measureToFind == it.measure }
        }
    }

    fun mostRecentAndUnknownDateIhcTestsForItem(ihcTests: List<IhcTest>, item: String): Set<IhcTest> {
        return mostRecentAndUnknownDateIhcTests(ihcTests).filter { it.item.equals(item, ignoreCase = true) }.toSet()
    }

    fun mostRecentAndUnknownDateIhcTests(ihcTests: List<IhcTest>): Set<IhcTest> {
        val (withDate, withoutDate) = ihcTests.partition { it.measureDate != null }
        return withDate
            .groupBy { it.item to it.reportHash }
            .flatMap { (_, tests) ->
                val mostRecentDate = tests.maxOf { it.measureDate!! }
                tests.filter { it.measureDate == mostRecentDate }
            }.toSet() + withoutDate.toSet()
    }
}
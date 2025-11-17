package com.hartwig.actin.datamodel.clinical

data class PriorMolecularTest(
    val test: String,
    val item: String,
    val measure: String? = null,
    val scoreText: String? = null,
    val scoreValuePrefix: String? = null,
    val scoreValue: Double? = null,
    val scoreValueUnit: String? = null,
    val impliesPotentialIndeterminateStatus: Boolean
)

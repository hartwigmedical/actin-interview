package com.hartwig.actin.clinical.datamodel

import java.time.LocalDate

data class Toxicity(
    val name: String,
    val categories: Set<String>,
    val evaluatedDate: LocalDate,
    val source: ToxicitySource,
    val grade: Int?
)
package com.hartwig.actin.datamodel.clinical

data class TumorDetails(
    val name: String = "",
    val doids: Set<String>? = null,
    val stage: TumorStage? = null,
    val derivedStages: Set<TumorStage>? = null,
    val hasMeasurableDisease: Boolean? = null,
    val hasBrainLesions: Boolean? = null,
    val hasActiveBrainLesions: Boolean? = null,
    val hasCnsLesions: Boolean? = null,
    val hasActiveCnsLesions: Boolean? = null,
    val hasBoneLesions: Boolean? = null,
    val hasLiverLesions: Boolean? = null,
    val hasLungLesions: Boolean? = null,
    val hasLymphNodeLesions: Boolean? = null,
    val otherLesions: List<String>? = null,
    val biopsyLocation: String? = null
)

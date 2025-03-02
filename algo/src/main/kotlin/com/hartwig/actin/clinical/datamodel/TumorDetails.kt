package com.hartwig.actin.clinical.datamodel

data class TumorDetails(
    val primaryTumorLocation: String? = null,
    val primaryTumorSubLocation: String? = null,
    val primaryTumorType: String? = null,
    val primaryTumorSubType: String? = null,
    val primaryTumorExtraDetails: String? = null,
    val doids: Set<String>? = null,
    val stage: TumorStage? = null,
    val hasMeasurableDisease: Boolean? = null,

    // Indicate which part of the body has lesions
    val lesionLocations: Set<BodyLocationCategory>? = null,

    // Specific locations can have active lesions (to move to a container if this may be extended in the future)
    val hasActiveBrainLesions: Boolean? = null,
    val hasActiveCnsLesions: Boolean? = null,

    // Kept for possible free text entries (if there is a use case for this)
    val otherLesions: List<String>? = null,

    val biopsyLocation: String? = null
)

package com.hartwig.actin.datamodel.clinical.treatment

import com.hartwig.actin.Displayable

interface TreatmentType : Displayable {
    val category: TreatmentCategory
}

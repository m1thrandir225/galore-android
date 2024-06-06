package com.sebastijanzindl.galore.data.network.response

import com.sebastijanzindl.galore.domain.models.Section
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetDailyHomeSectionsResponse(
    @SerialName("sections")
    val sections: List<Section>
)

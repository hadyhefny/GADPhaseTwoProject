package com.hefny.hady.gadphasetwoproject.api.responses

import com.google.gson.annotations.SerializedName

data class Leader(
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("hours")
    val hours: Int? = null,
    @field:SerializedName("score")
    val score: Int? = null,
    @field:SerializedName("country")
    val country: String? = null,
    @field:SerializedName("badgeUrl")
    val badgeUrl: String? = null
)
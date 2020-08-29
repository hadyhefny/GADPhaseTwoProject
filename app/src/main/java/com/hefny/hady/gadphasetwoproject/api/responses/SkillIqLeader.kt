package com.hefny.hady.gadphasetwoproject.api.responses

import com.google.gson.annotations.SerializedName

data class SkillIqLeader(
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("score")
    val score: Int? = null,
    @field:SerializedName("country")
    val country: String? = null,
    @field:SerializedName("badgeUrl")
    val badgeUrl: String? = null
)
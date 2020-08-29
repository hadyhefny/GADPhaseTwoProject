package com.hefny.hady.gadphasetwoproject.api

import com.hefny.hady.gadphasetwoproject.api.responses.LearningLeader
import com.hefny.hady.gadphasetwoproject.api.responses.SkillIqLeader
import retrofit2.Call
import retrofit2.http.GET

interface GadsApi {
    @GET("/api/hours")
    fun getLearningLeaders(): Call<ArrayList<LearningLeader>>

    @GET("/api/skilliq")
    fun getSkillIqLeaders(): Call<ArrayList<SkillIqLeader>>
}
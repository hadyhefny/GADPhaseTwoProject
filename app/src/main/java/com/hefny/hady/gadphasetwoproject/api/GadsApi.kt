package com.hefny.hady.gadphasetwoproject.api

import com.hefny.hady.gadphasetwoproject.api.responses.Leader
import retrofit2.Call
import retrofit2.http.GET

interface GadsApi {
    @GET("/api/hours")
    fun getLearningLeaders(): Call<ArrayList<Leader>>

    @GET("/api/skilliq")
    fun getSkillIqLeaders(): Call<ArrayList<Leader>>
}
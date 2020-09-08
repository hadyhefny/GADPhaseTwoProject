package com.hefny.hady.gadphasetwoproject.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hefny.hady.gadphasetwoproject.api.ServiceGenerator
import com.hefny.hady.gadphasetwoproject.api.responses.Leader
import com.hefny.hady.gadphasetwoproject.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeadersViewModel : ViewModel() {
    private var _learningLeadersMutableLiveData =
        MutableLiveData<Resource<ArrayList<Leader>>>()
    val learningLeadersLiveData: LiveData<Resource<ArrayList<Leader>>>
        get() = _learningLeadersMutableLiveData

    private var _skillIqLeadersMutableLiveData =
        MutableLiveData<Resource<ArrayList<Leader>>>()
    val skillIqLeadersLiveData: LiveData<Resource<ArrayList<Leader>>>
        get() = _skillIqLeadersMutableLiveData

    init {
        getLearningLeaders()
        getSkillIqLeaders()
    }

    private fun getLearningLeaders() {
        _learningLeadersMutableLiveData.value = Resource.Loading()
        ServiceGenerator.getGadsApi().getLearningLeaders()
            .enqueue(object : Callback<ArrayList<Leader>> {
                override fun onFailure(call: Call<ArrayList<Leader>>, t: Throwable) {
                    _learningLeadersMutableLiveData.value = Resource.Error()
                }

                override fun onResponse(
                    call: Call<ArrayList<Leader>>,
                    response: Response<ArrayList<Leader>>
                ) {
                    if (response.isSuccessful) {
                        _learningLeadersMutableLiveData.value = Resource.Success(response.body())
                    } else {
                        _learningLeadersMutableLiveData.value = Resource.Error()
                    }
                }
            })
    }

    private fun getSkillIqLeaders() {
        _skillIqLeadersMutableLiveData.value = Resource.Loading()
        ServiceGenerator.getGadsApi().getSkillIqLeaders()
            .enqueue(object : Callback<ArrayList<Leader>> {
                override fun onFailure(call: Call<ArrayList<Leader>>, t: Throwable) {
                    _skillIqLeadersMutableLiveData.value = Resource.Error()
                }

                override fun onResponse(
                    call: Call<ArrayList<Leader>>,
                    response: Response<ArrayList<Leader>>
                ) {
                    if (response.isSuccessful) {
                        _skillIqLeadersMutableLiveData.value = Resource.Success(response.body())
                    } else {
                        _skillIqLeadersMutableLiveData.value = Resource.Error()
                    }
                }
            })
    }
}
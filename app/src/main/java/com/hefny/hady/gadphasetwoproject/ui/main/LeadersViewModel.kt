package com.hefny.hady.gadphasetwoproject.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.hefny.hady.gadphasetwoproject.R
import com.hefny.hady.gadphasetwoproject.api.ServiceGenerator
import com.hefny.hady.gadphasetwoproject.api.responses.Leader
import com.hefny.hady.gadphasetwoproject.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class LeadersViewModel(private val context: Application) : AndroidViewModel(context) {
    private val TAG = "AppDebug"
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
                    Log.d(TAG, "onFailure: $t")
                    var errorMessage = context.getString(R.string.general_error_message)
                    when (t) {
                        is UnknownHostException, is IOException, is SocketTimeoutException -> {
                            errorMessage = context.getString(R.string.internet_connection_error)
                        }
                        is HttpException -> {
                            errorMessage = t.message()
                        }
                    }
                    _learningLeadersMutableLiveData.value = Resource.Error(errorMessage)
                }

                override fun onResponse(
                    call: Call<ArrayList<Leader>>,
                    response: Response<ArrayList<Leader>>
                ) {
                    _learningLeadersMutableLiveData.value = Resource.Success(response.body())
                }
            })
    }

    private fun getSkillIqLeaders() {
        _skillIqLeadersMutableLiveData.value = Resource.Loading()
        ServiceGenerator.getGadsApi().getSkillIqLeaders()
            .enqueue(object : Callback<ArrayList<Leader>> {
                override fun onFailure(call: Call<ArrayList<Leader>>, t: Throwable) {
                    var errorMessage = context.getString(R.string.general_error_message)
                    when (t) {
                        is UnknownHostException, is IOException, is SocketTimeoutException -> {
                            errorMessage = context.getString(R.string.internet_connection_error)
                        }
                        is HttpException -> {
                            errorMessage = t.message()
                        }
                    }
                    _skillIqLeadersMutableLiveData.value = Resource.Error(errorMessage)
                }

                override fun onResponse(
                    call: Call<ArrayList<Leader>>,
                    response: Response<ArrayList<Leader>>
                ) {
                    _skillIqLeadersMutableLiveData.value = Resource.Success(response.body())
                }
            })
    }
}
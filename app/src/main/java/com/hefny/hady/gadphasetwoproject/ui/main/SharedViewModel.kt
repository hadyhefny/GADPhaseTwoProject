package com.hefny.hady.gadphasetwoproject.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hefny.hady.gadphasetwoproject.R
import com.hefny.hady.gadphasetwoproject.api.ServiceGenerator
import com.hefny.hady.gadphasetwoproject.api.responses.LearningLeader
import com.hefny.hady.gadphasetwoproject.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class SharedViewModel(private val context: Application) : AndroidViewModel(context) {
    private var _learningLeadersMutableLiveData =
        MutableLiveData<Resource<ArrayList<LearningLeader>>>()
    val learningLeadersLiveData: LiveData<Resource<ArrayList<LearningLeader>>>
        get() = _learningLeadersMutableLiveData

    init {
        getLearningLeaders()
    }

    private fun getLearningLeaders() {
        _learningLeadersMutableLiveData.value = Resource.Loading()
        ServiceGenerator.getGadsApi().getLearningLeaders()
            .enqueue(object : Callback<ArrayList<LearningLeader>> {
                override fun onFailure(call: Call<ArrayList<LearningLeader>>, t: Throwable) {
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
                    call: Call<ArrayList<LearningLeader>>,
                    response: Response<ArrayList<LearningLeader>>
                ) {
                    _learningLeadersMutableLiveData.value = Resource.Success(response.body())
                }
            })
    }
}
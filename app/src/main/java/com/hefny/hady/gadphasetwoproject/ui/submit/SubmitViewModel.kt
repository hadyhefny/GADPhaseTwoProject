package com.hefny.hady.gadphasetwoproject.ui.submit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hefny.hady.gadphasetwoproject.api.ServiceGenerator
import com.hefny.hady.gadphasetwoproject.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubmitViewModel : ViewModel() {
    private val _submitProjectMutableLiveData = MutableLiveData<Resource<Unit>>()
    val submitProjectLiveData: LiveData<Resource<Unit>>
        get() = _submitProjectMutableLiveData

    fun submitProject(
        firstName: String,
        lastName: String,
        email: String,
        github: String
    ) {
        _submitProjectMutableLiveData.value = Resource.Loading()
        ServiceGenerator.getSubmitApi().submitProject(
            ServiceGenerator.TEST_BASE_URL,
            firstName, lastName, email, github
        ).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                _submitProjectMutableLiveData.value = Resource.Error()
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                _submitProjectMutableLiveData.value = Resource.Success()
            }
        })
    }
}
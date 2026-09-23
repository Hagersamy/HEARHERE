package com.example.hearhere.screen.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummytask.generic.RequestHandler
import com.example.hearhere.models.RegisterModel
import com.example.hearhere.repository.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel: ViewModel() {
    private val liveLoading = MutableLiveData<Boolean>()
    private val liveErrorMessage = MutableLiveData<String>()

    private val liveStatus = MutableLiveData<String>()
    fun register(registerModel: RegisterModel) {
        viewModelScope.launch(Dispatchers.IO) {
            liveLoading.postValue(true)
            try {
                Log.i("TAG", "data from viewModel before send : $registerModel")

                RequestHandler().makeRequest(
                    deferred = RetrofitInstance.ApiClient.apiService.register(registerModel),
                    onSuccess = { data ->
                        liveStatus.postValue(data!!.status.toString())
                        Log.i("TAG", "data from viewModel : $data")

                    },
                    onError = { message ->
                        Log.i("TAG", message)
                        liveErrorMessage.postValue(message)
                        liveStatus.postValue("field")
                    }
                )
            } catch (e: HttpException) {
                Log.e("TAG", "Error fetching Home Data: ${e.code()}")
            }
            liveLoading.postValue(false)
        }
    }

    fun getLoading(): MutableLiveData<Boolean> {
        return liveLoading
    }
    fun getErrorMessage(): MutableLiveData<String> {
        return liveErrorMessage
    }
    fun getStatus(): MutableLiveData<String> {
        return liveStatus
    }
}
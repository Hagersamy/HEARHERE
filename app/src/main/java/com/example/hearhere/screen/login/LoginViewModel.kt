package com.example.hearhere.screen.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummytask.generic.RequestHandler
import com.example.hearhere.models.LoginModel
import com.example.hearhere.models.LoginResponse
import com.example.hearhere.repository.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel : ViewModel() {
    private val _loginData: MutableLiveData<LoginResponse> = MutableLiveData()
    val loginData: LiveData<LoginResponse>
        get() = _loginData
    private val liveLoading = MutableLiveData<Boolean>()
    private val liveErrorMessage = MutableLiveData<String>()
    fun login(loginModel: LoginModel) {
        viewModelScope.launch(Dispatchers.IO) {
            liveLoading.postValue(true)
            try {
                Log.i("TAG", "data from viewModel before send : $loginModel")
                RequestHandler().makeRequest(
                    deferred = RetrofitInstance.ApiClient.apiService.login(loginModel),
                    onSuccess = { data ->
                        Log.i("TAG", "data from viewModel : $data")
                        _loginData.postValue(data)
                    },
                    onError = { message ->
                        Log.i("TAG", message)
                        liveErrorMessage.postValue(message)
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
}
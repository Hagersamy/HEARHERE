package com.example.hearhere.screen.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummytask.generic.RequestHandler
import com.example.hearhere.repository.remote.RetrofitInstance
import com.example.hearhere.models.HomeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeViewModel : ViewModel() {
    private val _homeData: MutableLiveData<HomeModel> = MutableLiveData()
    val homeData: LiveData<HomeModel>
        get() = _homeData
    private val liveLoading = MutableLiveData<Boolean>()

    fun getHomeData() {
        viewModelScope.launch(Dispatchers.IO) {
            liveLoading.postValue(true)
            try {
                RequestHandler().makeRequest(
                    deferred = RetrofitInstance.ApiClient.apiService.getHomeData(),
                    onSuccess = { data ->
                        Log.i("TAG", "data from viewModel : $data")
                        _homeData.postValue(data)
                    },
                    onError = { message ->
                        Log.i("TAG", message)
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
}
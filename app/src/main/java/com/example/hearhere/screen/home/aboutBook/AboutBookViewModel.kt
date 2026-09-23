package com.example.hearhere.screen.home.aboutBook

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummytask.generic.RequestHandler
import com.example.hearhere.repository.remote.RetrofitInstance
import com.example.hearhere.models.AboutBookModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AboutBookViewModel : ViewModel()
{
    private val _aboutBookData: MutableLiveData<AboutBookModel> = MutableLiveData()
    val aboutBookData: LiveData<AboutBookModel>
        get() = _aboutBookData
    private val liveLoading = MutableLiveData<Boolean>()


    fun getBookData(id : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            liveLoading.postValue(true)
            try {
                RequestHandler().makeRequest(
                    deferred = RetrofitInstance.ApiClient.apiService.getBookData(id),
                    onSuccess = { data ->
                        Log.i("TAG", "data from bookViewModel : $data")
                        _aboutBookData.postValue(data)
                    },
                    onError = { message ->
                        Log.i("TAG", message)
                    }
                )
            } catch (e: HttpException) {

                Log.e("TAG", "Error fetching book Data: ${e.code()}")
            }
            liveLoading.postValue(false)
        }
    }

    fun getLoading(): MutableLiveData<Boolean> {
        return liveLoading
    }
}
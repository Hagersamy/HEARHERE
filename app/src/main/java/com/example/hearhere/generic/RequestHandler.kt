package com.example.dummytask.generic
import android.content.ContentValues.TAG
import android.util.Log
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class RequestHandler {
    suspend fun <T> makeRequest(
        deferred: Deferred<T>,
        onSuccess: (data: T?) -> Unit,
        onError: (message: String) -> Unit
    ) {
        try {
            val response = withContext(Dispatchers.IO) { deferred.await() }
            withContext(Dispatchers.IO) {
                Log.i(TAG,"response : $response")
                onSuccess(response)
            }
        } catch (e: HttpException) {
            withContext(Dispatchers.IO) {
                Log.i(TAG,"error : ${e.message()}")
                if(e.code() == 422){
                    onError("${e.message()} : Please Enter Valid Data!")
                }
                else if(e.code() == 422){

                    onError("This Email is ${e.message()} ")
                }

            }
        }catch (e: IOException) {
            withContext(Dispatchers.IO) {
                onError("Network Error: ${e.message}")
            }
        } catch (e: Exception) {
            withContext(Dispatchers.IO) {
                onError("Unexpected Error: ${e.message}")
            }
        }
    }
}

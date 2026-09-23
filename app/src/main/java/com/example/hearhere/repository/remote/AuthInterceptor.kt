package com.example.hearhere.repository.remote

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.hearhere.repository.local.CachHelper
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context) : Interceptor {

    private var authToken: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = CachHelper.getInstance(context).getToken()
        authToken = token // Store the token for future use
        if(token == "not found"){

            Log.i(ContentValues.TAG, "DAta of home from server : token not found ")
            val request = chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .build()
            return chain.proceed(request)
        }else{

            Log.i(ContentValues.TAG, "DAta of home from server : token ${token} ")
            val request = chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer $token")
                .build()
            return chain.proceed(request)
        }
    }

    fun getToken(): String? {
        return authToken
    }
}
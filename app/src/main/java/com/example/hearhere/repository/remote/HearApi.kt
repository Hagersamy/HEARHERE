package com.example.hearhere.repository.remote

import com.example.hearhere.models.AboutBookModel
import com.example.hearhere.models.HomeModel
import com.example.hearhere.models.LoginModel
import com.example.hearhere.models.LoginResponse
import com.example.hearhere.models.RegisterModel
import com.example.hearhere.models.RegisterResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HearApi {
    @GET("home")
    fun getHomeData() : Deferred<HomeModel>

    @GET("book/{id}")
    fun getBookData(@Path("id") employeeID : Int) : Deferred<AboutBookModel>

    @POST("register")
    fun register(@Body register: RegisterModel) : Deferred<RegisterResponse>

    @POST("login")
    fun login(@Body login: LoginModel) : Deferred<LoginResponse>
}
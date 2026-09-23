package com.example.hearhere.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class LoginResponse (
    @Json(name = "status")
    val status: String?,
    @Json(name = "error")
    val error: String?,
    @Json(name = "token")
    val token: String?,
    @Json(name = "code")
    val code: Int?,
    @Json(name = "message")
    val message: String,
    @Json(name = "errors")
    val errors : ErrorsLogin?
):Parcelable
@JsonClass(generateAdapter = false)
@Parcelize
data class ErrorsLogin(
    @Json(name = "email")
    val email :List<String>?,
    @Json(name = "password")
    val password : List<String>?
):Parcelable


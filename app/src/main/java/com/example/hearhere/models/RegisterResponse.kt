package com.example.hearhere.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class RegisterResponse(
    @Json(name = "status")
    val status: String?,
    @Json(name = "code")
    val code: Int?,
    @Json(name = "message")
    val message: String,
    @Json(name = "errors")
    val errors : Errors?,
    @Json(name = "error")
    val error: String?,

):Parcelable
@JsonClass(generateAdapter = false)
@Parcelize
data class Errors(
    @Json(name = "name")
    val name : List<String>?,
    @Json(name = "email")
    val email :List<String>?,
    @Json(name = "password")
    val password : List<String>?
):Parcelable

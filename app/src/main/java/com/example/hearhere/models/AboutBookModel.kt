package com.example.hearhere.models


import android.os.Parcelable
import com.squareup.moshi.Json
import androidx.annotation.Keep
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class AboutBookModel(
    @Json(name = "code")
    val code: Int ,
    @Json(name = "data")
    val `data`: Data ,
    @Json(name = "status")
    val status: String
):Parcelable

@JsonClass(generateAdapter = false)
@Parcelize
data class Data(
    @Json(name = "author_name")
    val author_name: String ,
    @Json(name = "categories")
    val categories: List<Category>,
    @Json(name = "description")
    val description: String ,
    @Json(name = "file_link")
    val file_link: String ,
    @Json(name = "id")
    val id: Int ,
    @Json(name = "thumbnail_link")
    val thumbnail_link: String ,
    @Json(name = "title")
    val title: String
):Parcelable
@JsonClass(generateAdapter = false)
@Parcelize
data class Category(
    @Json(name = "id")
    val id: Int ,
    @Json(name = "title")
    val title: String
):Parcelable

package com.example.hearhere.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class HomeModel(
    @Json(name = "code")
    val code: Int ,
    @Json(name = "data")
    val data: HomeCollectios ,
    @Json(name = "status")
    val status: String
):Parcelable

@JsonClass(generateAdapter = false)
@Parcelize
data class HomeCollectios(
    @Json(name = "current_page")
    val currentPage: Int,
    @Json(name = "data")
    val data: List<Collection>,
    @Json(name = "first_page_url")
    val firstPageUrl: String,
    @Json(name = "from")
    val from: Int,
    @Json(name = "last_page")
    val lastPage: Int,
    @Json(name = "last_page_url")
    val lastPageUrl: String,
    @Json(name = "links")
    val links: List<Link>,
    @Json(name = "next_page_url")
    val nextPageUrl: String?,
    @Json(name = "path")
    val path: String,
    @Json(name = "per_page")
    val perPage: Int,
    @Json(name = "prev_page_url")
    val prevPageUrl: String?,
    @Json(name = "to")
    val to: Int,
    @Json(name = "total")
    val total: Int
):Parcelable
@JsonClass(generateAdapter = false)
@Parcelize
data class Collection(
    @Json(name = "books")
    val books: List<Book>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String
):Parcelable

@JsonClass(generateAdapter = false)
@Parcelize
data class Book(
    @Json(name = "author_name")
    val author_name: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "pivot")
    val pivot: Pivot,
    @Json(name = "thumbnail_link")
    val thumbnail_link: String,
    @Json(name = "title")
    val title: String
):Parcelable

@JsonClass(generateAdapter = false)
@Parcelize
data class Pivot(
    @Json(name = "book_id")
    val bookId: Int ,
    @Json(name = "collection_id")
    val collectionId: Int ,
    @Json(name = "order")
    val order: Int
):Parcelable
@JsonClass(generateAdapter = false)
@Parcelize
data class Link(
    @Json(name = "active")
    val active: Boolean ,
    @Json(name = "label")
    val label: String ,
    @Json(name = "url")
    val url: String?
):Parcelable
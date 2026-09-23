package com.example.hearhere.models.dbModels

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "books")
@Parcelize
data class DBBookModel (
    val author_name: String,
    val description: String,
    val file_link: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val thumbnail_link: String,
    val title: String
): Parcelable
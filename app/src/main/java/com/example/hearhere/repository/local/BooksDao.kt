package com.example.hearhere.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hearhere.models.dbModels.DBBookModel

@Dao
interface BooksDao {
    // onconfilct tell the database if exist replace the old  by new
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: DBBookModel)

    @Update
    suspend fun updateBook(book : DBBookModel)

    @Delete
    suspend fun deleteBook(book : DBBookModel)
    // to get all notes sortd by id
    @Query("SELECT * FROM books ORDER BY id DESC")
    fun getAllBook() : LiveData<List<DBBookModel>>/*
    // to search in table notes in the two column
    @Query("SELECT * FROM books WHERE noteTitle LIKE :query OR noteDesc LIKE :query")
    fun searchNote(query: String?): LiveData<List<DBBookModel>>*/

}
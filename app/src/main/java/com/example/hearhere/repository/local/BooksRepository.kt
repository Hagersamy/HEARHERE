package com.example.hearhere.repository.local

import com.example.hearhere.models.dbModels.DBBookModel

// repository mean store data correct
class BooksRepository(private val db:BooksDatabase) {
    // call the function getNoteDao where this function return type is NoteDao so we can use inset and other method
    suspend fun insertBook(book: DBBookModel) = db.getBooksDao().insertBook(book)
    suspend fun updateBook(book: DBBookModel) = db.getBooksDao().updateBook(book)
    suspend fun deleteBook(book: DBBookModel) = db.getBooksDao().deleteBook(book)

    fun getAllBook() = db.getBooksDao().getAllBook()
}
package com.example.hearhere.screen.home.aboutBook

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hearhere.models.dbModels.DBBookModel
import com.example.hearhere.repository.local.BooksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DBBooksViewModel ( private val booksRepository: BooksRepository):
    ViewModel() {

    fun addBook(book : DBBookModel){
        viewModelScope.launch(Dispatchers.IO) {
            try {

                booksRepository.insertBook(book)
                Log.i(TAG," save book to data base success :${book.toString()} ")
            }catch (e:Exception){
                Log.i(TAG,"error when save book to data base : $e")
            }
        }
    }

    fun deleteBook(book : DBBookModel){
        viewModelScope.launch {
            booksRepository.deleteBook(book)
        }
    }

    fun updateBook(book : DBBookModel){
        viewModelScope.launch {
            booksRepository.updateBook(book)
        }
    }
    fun  getAllBook() = booksRepository.getAllBook()

   // fun searchNote(query:String?) = notesRepository.searchNote(query)
}
package com.example.hearhere.screen.home.aboutBook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hearhere.repository.local.BooksRepository

class DBBookFactory ( private val booksRepository: BooksRepository):
    ViewModelProvider.Factory {

    override  fun<T : ViewModel> create(modelClass : Class<T>): T {
        return DBBooksViewModel(booksRepository) as T
    }
}
package com.example.mylib

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mylib.model.BookModel
import com.example.mylib.repository.BookRepository

class BookFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = BookRepository.getInstance(application)

    fun insertBook(book: BookModel) {
        repository.insert(book)
    }
}
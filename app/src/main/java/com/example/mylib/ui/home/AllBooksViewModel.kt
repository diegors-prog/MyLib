package com.example.mylib.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mylib.model.BookModel
import com.example.mylib.repository.BookRepository

class AllBooksViewModel(application: Application) : AndroidViewModel(application) {

    private val _repository = BookRepository.getInstance(application.applicationContext)
    private val _listAllBooks = MutableLiveData<List<BookModel>>()
    val books: LiveData<List<BookModel>> = _listAllBooks

    fun getAllBooks() {
        _listAllBooks.value = _repository.getAll()
    }

    fun delete(id: Int) {
        _repository.delete(id)
    }
}
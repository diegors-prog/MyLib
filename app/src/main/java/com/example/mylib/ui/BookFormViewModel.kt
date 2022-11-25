package com.example.mylib.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mylib.model.BookModel
import com.example.mylib.repository.BookRepository

class BookFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = BookRepository.getInstance(application)

    private val bookModel = MutableLiveData<BookModel>()
    val book: LiveData<BookModel> = bookModel

    private val _saveBook = MutableLiveData<String>()
    val saveBook: LiveData<String> = _saveBook

    fun save(book: BookModel) {
        if (book.id == 0) {
            if (repository.insert(book)) {
                _saveBook.value = "Inserção com sucesso"
            } else {
                _saveBook.value = "Falha"
            }
        } else {
            if (repository.update(book)) {
                _saveBook.value = "Atualização com sucesso"
            } else {
                _saveBook.value = "Falha"
            }
        }
    }

    fun get(id: Int) {
        bookModel.value =  repository.getById(id)
    }
}
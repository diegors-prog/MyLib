package com.example.mylib.ui.unread_books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UnreadBooksViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text
}
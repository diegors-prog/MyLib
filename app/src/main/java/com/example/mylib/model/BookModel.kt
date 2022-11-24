package com.example.mylib.model

data class BookModel(
    val id: Int,
    var title: String,
    var description: String,
    var numberOfPages: Int,
    var authorName: String,
    var status: Boolean) {
}
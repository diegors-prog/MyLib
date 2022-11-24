package com.example.mylib.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.mylib.databinding.RowBookBinding
import com.example.mylib.model.BookModel

class BookViewHolder(private val bind: RowBookBinding) : RecyclerView.ViewHolder(bind.root) {

    fun bind(book: BookModel) {
        bind.textTitle.text = book.title
    }
}
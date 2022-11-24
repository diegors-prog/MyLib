package com.example.mylib.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mylib.ViewHolder.BookViewHolder
import com.example.mylib.databinding.RowBookBinding
import com.example.mylib.model.BookModel

class BooksAdapter : RecyclerView.Adapter<BookViewHolder>() {
    private var bookList: List<BookModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val item = RowBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(item)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    override fun getItemCount(): Int {
        return  bookList.count()
    }

    fun updatedBooks(list: List<BookModel>) {
        bookList = list
        notifyDataSetChanged()
    }
}
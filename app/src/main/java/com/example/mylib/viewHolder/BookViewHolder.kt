package com.example.mylib.viewHolder

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.mylib.databinding.RowBookBinding
import com.example.mylib.listener.IOnBookListener
import com.example.mylib.model.BookModel

class BookViewHolder(private val bind: RowBookBinding, private val listener: IOnBookListener) : RecyclerView.ViewHolder(bind.root) {

    fun bind(book: BookModel) {
        bind.textId.text = book.id.toString()
        bind.textTitle.text = book.title
        bind.textStatus.text = if (book.status) "Lido" else "Não lido"

        bind.textTitle.setOnClickListener {
            listener.onClick(book.id)
        }

        bind.textTitle.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de Livro")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton(
                    "Sim"
                ) { dialog, which ->
                    listener.onDelete(book.id)
                }
                .setNegativeButton("Não", null)
                .create()
                .show()

            true
        }
    }
}
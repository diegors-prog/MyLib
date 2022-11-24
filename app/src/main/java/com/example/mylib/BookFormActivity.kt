package com.example.mylib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.mylib.databinding.ActivityBookFormBinding
import com.example.mylib.model.BookModel

class BookFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityBookFormBinding
    private lateinit var bookFormViewModel: BookFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookFormViewModel = ViewModelProvider(this).get(BookFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
        binding.radioRead.isChecked = true
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            val title = binding.inputBookTitle.text.toString()
            val description = binding.inputBookDescription.text.toString()
            val pages = Integer.valueOf(binding.inputBookNumberOfPages.text.toString())
            val authorName = binding.inputBookAuthor.text.toString()
            val status = binding.radioRead.isChecked


            val model = BookModel(0, title, description, pages, authorName, status)
            bookFormViewModel.insertBook(model)
        }
    }
}
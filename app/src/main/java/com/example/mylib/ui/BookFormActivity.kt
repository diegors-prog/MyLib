package com.example.mylib.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mylib.R
import com.example.mylib.constants.DataBaseConstants
import com.example.mylib.databinding.ActivityBookFormBinding
import com.example.mylib.model.BookModel

class BookFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityBookFormBinding
    private lateinit var bookFormViewModel: BookFormViewModel

    private var bookId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookFormViewModel = ViewModelProvider(this).get(BookFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
        binding.radioRead.isChecked = true

        observe()
        loadData()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            val title = binding.inputBookTitle.text.toString()
            val description = binding.inputBookDescription.text.toString()
            val pages = Integer.valueOf(binding.inputBookNumberOfPages.text.toString())
            val authorName = binding.inputBookAuthor.text.toString()
            val status = binding.radioRead.isChecked


            val model = BookModel(bookId, title, description, pages, authorName, status)
            bookFormViewModel.save(model)
            finish()
        }
    }

    private fun observe() {
        bookFormViewModel.book.observe(this) {
            binding.inputBookTitle.setText(it.title)
            binding.inputBookDescription.setText(it.description)
            binding.inputBookNumberOfPages.setText(it.numberOfPages.toString())
            binding.inputBookAuthor.setText(it.authorName)
            if (it.status) {
                binding.radioRead.isChecked = true
            } else {
                binding.radioUnread.isChecked = true
            }
        }

        bookFormViewModel.saveBook.observe(this) {
            if (it != "") {
                Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            bookId = bundle.getInt(DataBaseConstants.BOOK.ID)
            bookFormViewModel.get(bookId)
        }
    }
}
package com.example.mylib.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylib.ui.BookFormActivity
import com.example.mylib.adapter.BooksAdapter
import com.example.mylib.constants.DataBaseConstants
import com.example.mylib.databinding.FragmentAllBooksBinding
import com.example.mylib.listener.IOnBookListener

class AllBooksFragment : Fragment() {

    private var _binding: FragmentAllBooksBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var allBooksViewModel: AllBooksViewModel
    private val adapter = BooksAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        b: Bundle?
    ): View {
        allBooksViewModel =
            ViewModelProvider(this).get(AllBooksViewModel::class.java)

        _binding = FragmentAllBooksBinding.inflate(inflater, container, false)

        // layout
        binding.recyclerBooks.layoutManager = LinearLayoutManager(context)

        // adapter
        binding.recyclerBooks.adapter = adapter

        val listener = object : IOnBookListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, BookFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.BOOK.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                allBooksViewModel.delete(id)
                allBooksViewModel.getAllBooks()
            }
        }

        adapter.attachListener(listener)

        allBooksViewModel.getAllBooks()
        observe()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        allBooksViewModel.getAllBooks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        allBooksViewModel.books.observe(viewLifecycleOwner) {
            adapter.updatedBooks(it)
        }
    }
}
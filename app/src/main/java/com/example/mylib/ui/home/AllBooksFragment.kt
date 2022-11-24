package com.example.mylib.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylib.adapter.BooksAdapter
import com.example.mylib.databinding.FragmentAllBooksBinding

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
        binding.recyclerAllBooks.layoutManager = LinearLayoutManager(context)

        // adapter
        binding.recyclerAllBooks.adapter = adapter

        allBooksViewModel.getAllBooks()
        observe()
        return binding.root
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
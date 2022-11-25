package com.example.mylib.ui.read_books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mylib.databinding.FragmentReadBooksBinding

class ReadBooksFragment : Fragment() {

    private var _binding: FragmentReadBooksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        b: Bundle?
    ): View {
        val readBooksViewModel =
            ViewModelProvider(this).get(ReadBooksViewModel::class.java)

        _binding = FragmentReadBooksBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
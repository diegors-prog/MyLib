package com.example.mylib.ui.unread_books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mylib.databinding.FragmentUnreadBooksBinding

class UnreadBooksFragment : Fragment() {

    private var _binding: FragmentUnreadBooksBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val unreadBooksViewModel =
            ViewModelProvider(this).get(UnreadBooksViewModel::class.java)

        _binding = FragmentUnreadBooksBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        unreadBooksViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.hearhere.screen.library

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hearhere.R
import com.example.hearhere.databinding.FragmentLibraryBinding
import com.example.hearhere.repository.local.BooksDatabase
import com.example.hearhere.repository.local.BooksRepository
import com.example.hearhere.screen.home.aboutBook.DBBookFactory
import com.example.hearhere.screen.home.aboutBook.DBBooksViewModel


class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!
    private var factory : DBBookFactory? = null
    private  var dbBooksViewModel: DBBooksViewModel? = null
    private var bookLibraryAdapter : BookLibraryAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_library, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeLibraryBook()
    }
    private fun observeLibraryBook(){

        factory =  DBBookFactory( BooksRepository(BooksDatabase.invoke(requireContext())))
        dbBooksViewModel = ViewModelProvider(this, factory!!).get(DBBooksViewModel::class.java)
        dbBooksViewModel!!.getAllBook().observe(viewLifecycleOwner){books ->
            Log.i(TAG,"data from database : $books")
            bookLibraryAdapter = BookLibraryAdapter(requireContext(),books,{book ->    Toast.makeText(
                requireContext(),
                " book : ${book.author_name}",
                Toast.LENGTH_SHORT
            ).show()})
            binding.libraryRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = bookLibraryAdapter
            }
        }

    }


}
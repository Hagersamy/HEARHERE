package com.example.hearhere.screen.home.aboutBook

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.hearhere.R
import com.example.hearhere.databinding.FragmentAboutBookBinding
import com.example.hearhere.models.AboutBookModel
import com.example.hearhere.models.dbModels.DBBookModel
import com.example.hearhere.repository.local.BooksDatabase
import com.example.hearhere.repository.local.BooksRepository

class AboutBookFragment : Fragment() {

    private var _binding: FragmentAboutBookBinding? = null
    private val binding get() = _binding!!

    private val aboutBookViewModel: AboutBookViewModel by lazy {
        ViewModelProvider(this)[AboutBookViewModel::class.java]
    }

    private var dataOfBook: AboutBookModel? = null
    private var buttonStaggeredAdapter: ButtonStaggeredAdapter? = null
    private var factory :DBBookFactory? = null
    private  var dbBooksViewModel: DBBooksViewModel ? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_book, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        callServer()
        getBookData()
        buttonBackToHome()
        addToLibrary()

    }

    private fun callServer() {
        val id = requireArguments().getInt("id")
        aboutBookViewModel.getBookData(id)
        observeLoading()
    }

    private fun getBookData() {
        aboutBookViewModel.aboutBookData.observe(viewLifecycleOwner) { bookData ->
            if (bookData != null) {
                dataOfBook = bookData
                assignDataToUi(dataOfBook!!)
            } else {
                Toast.makeText(requireContext(), "data null", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun assignDataToUi(dataOfBook: AboutBookModel) {
        Log.i(ContentValues.TAG, "Data of book from server: $dataOfBook")
        binding.bookTitle.text = dataOfBook.data.title
        binding.authorName.text = dataOfBook.data.author_name

        buttonStaggeredData(dataOfBook)
        Glide.with(requireContext())
            .load(dataOfBook.data.thumbnail_link)
            .into(binding.bookImage)
        binding.descriptionTextView.text = dataOfBook.data.description
    }

    private fun buttonStaggeredData(dataOfBook: AboutBookModel) {
        buttonStaggeredAdapter = ButtonStaggeredAdapter(dataOfBook.data.categories)
        binding.recyclerViewStaggered.apply {
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
            layoutManager = staggeredGridLayoutManager
            adapter = buttonStaggeredAdapter
            isNestedScrollingEnabled = true
        }
    }

    private fun buttonBackToHome() {
        binding.backButton.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }
    private fun addToLibrary(){
        binding.addToLibraryButton.setOnClickListener {
            saveBookToDatabase()
        }
    }
    private fun saveBookToDatabase(){
        factory =  DBBookFactory( BooksRepository(BooksDatabase.invoke(requireContext())))
        dbBooksViewModel = ViewModelProvider(this, factory!!)[DBBooksViewModel::class.java]
        if(dataOfBook != null){
            val book = DBBookModel(
                author_name = dataOfBook!!.data.author_name,
                description = dataOfBook!!.data.description,
                file_link = dataOfBook!!.data.file_link,
                id = dataOfBook!!.data.id,
                thumbnail_link = dataOfBook!!.data.thumbnail_link,
                title = dataOfBook!!.data.title,
            )
            dbBooksViewModel!!.addBook(book)
        }
        else{
            Toast.makeText(requireContext(),"data of book is null",Toast.LENGTH_SHORT).show()
        }
    }
    private fun observeLoading() {
        aboutBookViewModel.getLoading().observe(viewLifecycleOwner) {
                isActive ->
            if(isActive){
                binding.progressBarAboutBook.isEnabled = isActive
            }else{
                binding.progressBarAboutBook.isEnabled = isActive

                binding.progressBarAboutBook.visibility = View.GONE
            }
        }
    }
}

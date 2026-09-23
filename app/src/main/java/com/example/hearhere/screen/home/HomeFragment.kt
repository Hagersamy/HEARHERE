package com.example.hearhere.screen.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hearhere.R
import com.example.hearhere.databinding.FragmentHomeBinding
import com.example.hearhere.models.HomeModel
import com.example.hearhere.models.LastBookLestining

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by lazy {
        // to create object
        ViewModelProvider(this)[HomeViewModel::class.java]

    }
    private val listOfListening by lazy {
        listOf(
            LastBookLestining("book1", "image1", 10),
            LastBookLestining("book2", "image2", 20),
            LastBookLestining("book3", "image3", 30),
            LastBookLestining("book4", "image4", 40),
            LastBookLestining("book5", "image5", 50),
            LastBookLestining("book6", "image6", 60),
            LastBookLestining("book7", "image7", 70),
            LastBookLestining("book8", "image8", 80),
            LastBookLestining("book9", "image9", 90)
        )
    }

    //to use store data come from HomeViewModel and watch this data
    private var data: HomeModel? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel.getHomeData()
        observeHomeData()
        initMyListening()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeHomeData() {
        homeViewModel.homeData.observe(viewLifecycleOwner) { homeData ->
            //to add all data and use it and use it to send to adapter later
            if (homeData != null) {
                data = homeData
                Log.i(TAG, "DAta of home from server :$data ")
                setupHomeRecyclerview(data!!)
                observeLoading()
            } else {
                Toast.makeText(requireContext(), "data null", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupHomeRecyclerview(data: HomeModel) {
        binding.homeRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ContainerBookAdapter(requireContext(), data!!.data.data, { container ->
                // Handle "See All" click
                println("See all clicked for container: ${container.title}") },
                { book -> // Handle book item click
                Toast.makeText(requireContext(), "id of book : ${book.author_name}",
                    Toast.LENGTH_SHORT).show()
                val bundle = bundleOf("id" to book.id)
                this@HomeFragment.findNavController()
                    .navigate(R.id.action_global_aboutBookFragment, bundle)
                       println("Book item clicked: ${book.title}")
            })
        }
    }

    private fun initMyListening() {
        val listeningsAdapter = BookListeningAdapter(requireContext(), listOfListening)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
            adapter = listeningsAdapter
        }
    }

    private fun observeLoading() {
        homeViewModel.getLoading().observe(viewLifecycleOwner) {
              isActive ->
              if(isActive){
                  binding.progressBar.isEnabled = isActive
              }else{
                  binding.progressBar.isEnabled = isActive

                  binding.progressBar.visibility = View.GONE
              }
        }
    }

}

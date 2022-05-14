package com.example.q2retrofit.ui.search

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.q2retrofit.data.PhotoClass
import com.example.q2retrofit.R
import com.example.q2retrofit.data.FlickrAdapter
import com.example.q2retrofit.databinding.FragmentSearchBinding

class FragmentSearch : Fragment(R.layout.fragment_search) {

    val searchViewModel: SearchViewModel by viewModels()


    lateinit var binding: FragmentSearchBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        var edText = binding.searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.txtSearch = edText.text.toString()
                searchViewModel.searchImage()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }

        })

        searchViewModel.searchImage()
        searchViewModel.searchResult.observe(viewLifecycleOwner, Observer {
            var photoList = searchViewModel.searchResult.value
            makeRecyclerView(photoList)
        })


    }

    fun makeRecyclerView(photo: PhotoClass?) {
        val flickrAdapter = photo?.let { FlickrAdapter(requireContext(), it) }
        binding.rcSearch.adapter = flickrAdapter
        binding.rcSearch.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
    }
}

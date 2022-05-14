package com.example.q2retrofit.ui.paging

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.q2retrofit.data.PhotoClass
import com.example.q2retrofit.R
import com.example.q2retrofit.data.FlickrAdapter
import com.example.q2retrofit.data.Photo
import com.example.q2retrofit.databinding.FragmentFlikerphotoBinding


class FragmentFlickr : Fragment(R.layout.fragment_flikerphoto) {

    lateinit var binding: FragmentFlikerphotoBinding
    val flickrViewModel: FlickrViewModel by viewModels()
    lateinit var adapter: FlickrAdapter
    lateinit var listOfPhoto : ArrayList<Photo>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFlikerphotoBinding.bind(view)

        listOfPhoto = flickrViewModel.list
        flickrViewModel.loadImage(flickrViewModel.queryMap)
        flickrViewModel.loadImage.observe(requireActivity(), Observer {

            makeRecyclerView(flickrViewModel.loadImage.value)

        })




        binding.rvPhotoRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    loadMore()
                }
            }


        })

    }

    fun makeRecyclerView(photo: PhotoClass?) {

        val flickrAdapter = FlickrAdapter(requireContext(), photo)
        binding.rvPhotoRecyclerView.adapter = flickrAdapter
        binding.rvPhotoRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    fun loadMore() {
        flickrViewModel.pageNumber++
        flickrViewModel.queryMap["page"] = flickrViewModel.pageNumber.toString()
        flickrViewModel.loadImage(flickrViewModel.queryMap)


    }


}
package com.example.q2retrofit.ui.paging

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.q2retrofit.NetworkManager
import com.example.q2retrofit.data.PhotoClass
import com.example.q2retrofit.data.Photo
import retrofit2.Call
import retrofit2.Response

class FlickrViewModel : ViewModel() {
    lateinit var list : ArrayList<Photo>
    private val _loadImage = MutableLiveData<PhotoClass>()
    val loadImage: LiveData<PhotoClass> = _loadImage
    var pageNumber = 1
    var queryMap = mutableMapOf(
        "api_key" to "1c04e05bce6e626247758d120b372a73",
        "method" to "flickr.photos.getRecent",
        "extras" to "url_s",
        "format" to "json",
        "nojsoncallback" to "1",
        "per_page" to "10",
        "text" to "sea",
        "page" to pageNumber.toString()
    )






    fun loadImage(queryMap: Map<String, String>) {
        val photosList = NetworkManager.service.getphoto(queryMap)
        photosList.enqueue(object : retrofit2.Callback<PhotoClass> {
            override fun onResponse(call: Call<PhotoClass>, response: Response<PhotoClass>) {
                _loadImage.value = response.body()
                Log.d("TAG", "onResponse: ${response.body()}")

            }

            override fun onFailure(call: Call<PhotoClass>, t: Throwable) {
                Log.i("Error", t.message.toString())
            }


        })
    }

    init {
        list = ArrayList<Photo>()
    }

}


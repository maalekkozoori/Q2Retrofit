package com.example.q2retrofit.ui.search

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.q2retrofit.NetworkManager
import com.example.q2retrofit.data.PhotoClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {


    private val _searchResult = MutableLiveData<PhotoClass>()
    val searchResult : LiveData<PhotoClass> = _searchResult

    var txtSearch = "1"


    fun searchImage(){
        val photosList = NetworkManager.service.getphoto(
            mapOf(
                "api_key" to "1c04e05bce6e626247758d120b372a73",
                "method" to "flickr.photos.search",
                "text" to txtSearch,
                "extras" to "url_s",
                "format" to "json",
                "nojsoncallback" to "1",
                "per_page" to "100",
                "page" to "1"
            )
        )
        photosList.enqueue(object : Callback<PhotoClass>{
            override fun onResponse(call: Call<PhotoClass>, response: Response<PhotoClass>) {
                _searchResult.value = response.body()
            }

            override fun onFailure(call: Call<PhotoClass>, t: Throwable) {
                Toast.makeText(FragmentSearch().context, t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}

package com.example.q2retrofit

import com.example.q2retrofit.data.Photo
import com.example.q2retrofit.data.PhotoClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface FlickrService {

/*    @GET("services/rest")
    fun getphoto(@QueryMap map: Map<String, String>): Call<List<Photo>>*/

    @GET("services/rest")
    fun getphoto(@QueryMap map: Map<String,String> ) : Call<PhotoClass>

    @GET("services/rest")
    fun searchImage(@QueryMap map : Map<String,String>): Call<List<Photo>>

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<kotlin.Any>>
}
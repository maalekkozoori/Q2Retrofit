package com.example.q2retrofit

import com.example.q2retrofit.data.Photo

data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<Photo>,
    val total: Int
)
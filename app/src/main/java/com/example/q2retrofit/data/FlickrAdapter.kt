package com.example.q2retrofit.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.q2retrofit.R

class FlickrAdapter(private val context: Context, private val photoList: PhotoClass?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imgView: ImageView
        var tvTitle: TextView

        init {
            imgView = itemView.findViewById(R.id.imageView)
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }

        fun bind(position: Int) {
            tvTitle.text = photoList?.photos?.photo?.get(position)?.title
            Glide.with(itemView.context)
                .load(photoList?.photos?.photo?.get(position)?.url_s)
                .into(imgView)
        }

        fun getImageSrc(url: String) {

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }

    override fun getItemCount(): Int {
        return photoList?.photos?.photo!!.size
    }
}
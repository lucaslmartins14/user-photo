package com.example.users_and_photos.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.users_and_photos.R

import com.example.users_and_photos.model.entity.Photo
import com.example.users_and_photos.model.entity.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photo.view.*
import kotlinx.android.synthetic.main.item_user.view.*

class PhotoAdapter(private val context: Context) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    private var PhotoList: List<Photo> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    fun setPhotos(PhotoList: List<Photo>){
        this.PhotoList = PhotoList
        notifyDataSetChanged()
    }
    override fun getItemCount() = PhotoList.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bindView(PhotoList.get(position))
//        holder.cvUser.setOnClickListener { v->
//            Toast.makeText(context,""+ PhotoList.get(position).name, Toast.LENGTH_LONG).show()
//        }
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto = itemView.iv_photo
        fun bindView(photo: Photo) {
        //    Glide.with(itemView.context).load("https://avatars3.githubusercontent.com/u/32689599?v=4").into(ivPhoto)
            Picasso.with(itemView.context).load("https://via.placeholder.com/150/92c952").into(ivPhoto)
var x = 0
        }
    }
}
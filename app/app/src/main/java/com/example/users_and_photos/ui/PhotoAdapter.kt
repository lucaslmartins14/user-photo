package com.example.users_and_photos.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.users_and_photos.R
import com.example.users_and_photos.model.entity.Photo
import com.example.users_and_photos.utils.CustomPicasso
import kotlinx.android.synthetic.main.item_photo.view.*


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
        holder.cvPhoto.setOnClickListener { v->
            val dialogBuilder =
                AlertDialog.Builder(context)
            val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val dialogView: View = inflater.inflate(R.layout.dialog_zoom, null)
            dialogBuilder.setView(dialogView)

            val imageview = dialogView.findViewById<View>(R.id.iv_zoom) as ImageView
            CustomPicasso.with(context).load(PhotoList.get(position).url).into(imageview)
            val alertDialog = dialogBuilder.create()
            alertDialog.show()
        }
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto = itemView.iv_photo
        val cvPhoto = itemView.cv_photo
        fun bindView(photo: Photo) {
            CustomPicasso.with(itemView.context).load(photo.url).into(ivPhoto)

        }
    }
}
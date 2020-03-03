package com.example.users_and_photos.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.users_and_photos.R
import com.example.users_and_photos.model.entity.Post
import kotlinx.android.synthetic.main.item_user.view.*

class PostAdapter(private val context: Context) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private var PostList: List<Post> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    fun setPosts(GRList: List<Post>) {
        this.PostList = GRList
        notifyDataSetChanged()
    }

    override fun getItemCount() = PostList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindView(PostList.get(position))

    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tv_title
        val tvBody = itemView.tv_body
        fun bindView(post: Post) {
            tvTitle.text = post.title
            tvBody.text = post.body

        }
    }
}
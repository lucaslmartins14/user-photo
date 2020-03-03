package com.example.users_and_photos.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.users_and_photos.R
import com.example.users_and_photos.retrofit.RetrofitInitializer
import com.example.users_and_photos.ui.PostAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PostsActivity : AppCompatActivity() {
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: PostAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        id = intent.getStringExtra("id")!!
        layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.rv_posts)
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        recyclerView.layoutManager = layoutManager
        adapter = PostAdapter(this)
        recyclerView.adapter = adapter

        val callUser = RetrofitInitializer().postService()?.getPosts(id)

        lifecycleScope.launch(Dispatchers.IO) {
            delay(500)
            callUser?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ posts ->
                    adapter.setPosts(posts)
                }, { e ->
                    e.printStackTrace()
                }, {
                    adapter.notifyDataSetChanged()
                })
        }
    }
}

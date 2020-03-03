package com.example.users_and_photos.ui.fragments.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.users_and_photos.R
import com.example.users_and_photos.retrofit.RetrofitInitializer
import com.example.users_and_photos.ui.PhotoAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PhotosFragment : Fragment() {

    lateinit var layoutManager: GridLayoutManager
    lateinit var adapter: PhotoAdapter
    private lateinit var recyclerView : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_photos, container, false)
        layoutManager = GridLayoutManager(this.context, 2)
        recyclerView = root.findViewById(R.id.rv_photos)
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        recyclerView.layoutManager = layoutManager
        adapter = PhotoAdapter(this.context!!)
        recyclerView.adapter = adapter

        val callPhotos = RetrofitInitializer().photoService()!!.getPhotos()

            lifecycleScope.launch(Dispatchers.IO){
                delay(500)
        callPhotos.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ photos->
            var x = photos
            adapter.setPhotos(photos)
        },{e->
            e.printStackTrace()
        },{
            adapter.notifyDataSetChanged()
        })
            }
        return root
    }
}

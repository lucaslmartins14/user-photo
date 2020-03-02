package com.example.users_and_photos.ui.fragments.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.users_and_photos.R
import com.example.users_and_photos.model.entity.User
import com.example.users_and_photos.retrofit.RetrofitInitializer
import com.example.users_and_photos.ui.PhotoAdapter
import com.example.users_and_photos.ui.UserAdapter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import kotlin.Comparator


class UsersFragment : Fragment() {
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: UserAdapter
    private lateinit var recyclerView : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_users, container, false)
        layoutManager = LinearLayoutManager(this.context)
        recyclerView = root.findViewById(R.id.rv_users)
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        recyclerView.layoutManager = layoutManager
        adapter = UserAdapter(this.context!!)
        recyclerView.adapter = adapter

        val callUser = RetrofitInitializer().userService()!!.getUsers()

//            lifecycleScope.launch(Dispatchers.IO){
//                delay(500)
                callUser.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ users->
                    adapter.setUsers(users)
                    Collections.sort(users,
                        Comparator { o1: User, o2: User -> o1.name.compareTo(o2.name) })
                },{e->
                    e.printStackTrace()
                },{
                    adapter.notifyDataSetChanged()
                })
//            }
        return root
    }
}

package com.example.users_and_photos.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.users_and_photos.R
import com.example.users_and_photos.model.entity.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(private val context: Context) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var UserList: List<User> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    fun setUsers(GRList: List<User>){
        this.UserList = GRList
        notifyDataSetChanged()
    }
    override fun getItemCount() = UserList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindView(UserList.get(position))
        holder.cvUser.setOnClickListener { v->
            Toast.makeText(context,""+ UserList.get(position).name,Toast.LENGTH_LONG).show()
        }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId = itemView.tv_id
        val tvName = itemView.tv_name
        val tvEmail = itemView.tv_email
        val tvCompanyName = itemView.tv_company_name
        val tvCity = itemView.tv_city
        val cvUser = itemView.cv_user
        fun bindView(user: User) {
            tvId.text = user.id.toString()
            tvName.text = user.name
            tvEmail.text = user.email
            tvCompanyName.text = user.company.name
            tvCity.text = user.address.city
        }
    }
}
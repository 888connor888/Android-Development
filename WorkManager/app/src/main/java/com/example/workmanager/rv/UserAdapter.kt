package com.example.workmanager.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workmanager.R
import com.example.workmanager.response.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var data:List<User> = ArrayList()
    var onItemClick:((login:String)->Unit)? = null
    inner class UserViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) = with(itemView) {
            logIn.text = user.url
            userName.text = user.login
            Picasso.get().load(user.avatarUrl).into(imageView)
            setOnClickListener {
                onItemClick?.invoke(user.login!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun swapData(data:List<User>){
        this.data = data
        notifyDataSetChanged()
    }
}
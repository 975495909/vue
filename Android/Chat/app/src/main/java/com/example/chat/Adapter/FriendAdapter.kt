package com.example.chat.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.Entity.Friend
import com.example.chat.R


class FriendAdapter(val friendList:List<Friend>)
    :RecyclerView.Adapter<FriendAdapter.ViewHolder>(){

     inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
            val name_F:TextView=view.findViewById(R.id.name_friend)
            val img_F:ImageView=view.findViewById(R.id.img_friend)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.listfriend_item,parent,false)
        return ViewHolder(view)
    }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val friend=friendList[position]
         holder.name_F.text=friend.name_friend
         holder.img_F.setImageResource(friend.img_friend)

     }

     override fun getItemCount(): Int {
         val count=friendList.size
         return count
     }

 }
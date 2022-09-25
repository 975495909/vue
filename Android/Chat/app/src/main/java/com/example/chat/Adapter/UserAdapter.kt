package com.example.chat.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.chat.Entity.User

class UserAdapter(activity: Activity, val resourceId:Int, data:List<User>):
    ArrayAdapter<User>(activity,resourceId,data) {
    //override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

    //}
}
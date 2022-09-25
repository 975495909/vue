package com.example.listview

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class StarAdapter(activity: Activity,val resourceId: Int,data:List<Star>)
    :ArrayAdapter<Star>(activity,resourceId,data){
    inner class ViewHolder(val starImage: ImageView,val starname:TextView)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view:View
        val viewHolder:ViewHolder
        if(convertView==null){
            view=LayoutInflater.from(context).inflate(resourceId,parent,false)
            val starImage:ImageView=view.findViewById(R.id.star_img)
            val starName:TextView=view.findViewById(R.id.star_name)
            viewHolder=ViewHolder(starImage,starName )
            view.tag=viewHolder
        }else{
            view=convertView
            viewHolder=view.tag as ViewHolder
        }

        val star=getItem(position) //获取当前子项定位
        if(star!=null){
            viewHolder.starImage.setImageResource(star.ImageId)
            viewHolder.starname.text=star.userName
        }
        return view
    }
}

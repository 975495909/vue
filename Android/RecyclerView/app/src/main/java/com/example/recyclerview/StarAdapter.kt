package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/*自定义适配器，新建一个类starAdapter，让这个适配器继承RecyclerView.Adapter
并将泛型指定为starAdapter.ViewHolder
*/
class StarAdapter(val starList:List<Star>)
    :RecyclerView.Adapter<StarAdapter.ViewHolder>()  {
    /*定义一个内部类ViewHolder，继承RecyclerView.ViewHolder,
    在ViewHolder的主构造函数中传入一个View参数,view参数就是RecyclerView子项的最外层布局，在类里可以获取布局文件中的控件实例*/
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
            val starImage:ImageView=view.findViewById(R.id.starImage)
            val starName:TextView=view.findViewById(R.id.starName)
        }
    // onCreateViewHolder()用于创建一个ViewHolder实例，把加载出来的布局传入构造函数中
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.star_item,parent,false)
        return ViewHolder(view)
    }
    //onBindViewHolder()用于对RecyclerView子项的数据地进行赋值，在每个子项被滚入屏幕时执行
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val star=starList[position]
        holder.starImage.setImageResource(star.ImageId)
        holder.starName.text=star.userName
    }
    //getItemCount()方法告诉RecyclerView一共有多少子项，直接返回数据源的长度
    override fun getItemCount(): Int {
        val count=starList.size
        return count
    }
}
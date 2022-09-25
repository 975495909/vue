package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //private val data=listOf("钟南山","于敏","申纪兰","孙家栋","李延年","张富清","袁隆平",
     //   "黄旭华","屠呦呦","周兴铭","叶培建","王赤","戴永久","刘少军")
    val starList=ArrayList<Star>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    //    val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
     //   list.adapter=adapter
        initStars()
        val starAdapter=StarAdapter(this,R.layout.item,starList)
        list.adapter=starAdapter
        list.setOnItemClickListener(){
            parent,view,position,id->
            val star=starList[position]
            Toast.makeText(this,star.userName, Toast.LENGTH_LONG).show()
        }
    }
    fun initStars(){
        repeat(8){
            starList.add(Star(R.drawable.s1_zhongnanshan,"钟南山"))
            starList.add(Star(R.drawable.s2_yumin,"于敏"))
        }

    }
}
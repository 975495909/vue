package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.title.view.*

class TitleLayout(context:Context,attr:AttributeSet):LinearLayout(context,attr) {
    init {
        LayoutInflater.from(context).inflate(R.layout.title,this)

        btn_back.setOnClickListener(){
            Toast.makeText(context,"返回按钮!",Toast.LENGTH_SHORT).show()
        }
        btn_mov.setOnClickListener(){
            Toast.makeText(context,"编辑按钮!",Toast.LENGTH_SHORT).show()
        }
    }

}
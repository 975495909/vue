package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val  data=intent.getStringExtra("FirstData")
        showText.text=data

        btn1.setOnClickListener(){
            val intent= Intent()
            intent.putExtra("data_return","我是MainActivity的数据，按按钮纽")
            setResult(RESULT_OK,intent)
            finish()
        }

    }

    override fun onBackPressed() {
        val intent=Intent()
        intent.putExtra("data_return","你好呀我是main的数据,按返回键")
        setResult(RESULT_OK,intent)
        finish()
    }


}
package com.example.activitytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val data=intent.getStringExtra("first_data")
        showText.text=data

        btn1.setOnClickListener(){
            val intent= Intent()
            intent.putExtra("return_data","我是SecondActivity的数据（按钮版）")
            setResult(RESULT_OK,intent)
            finish()
        }
    }

    override fun onBackPressed() {
        val intent=Intent()
        intent.putExtra("return_data","你好呀我是SecondActivity的数据（返回键）")
        setResult(RESULT_OK,intent)
        finish()
    }
}
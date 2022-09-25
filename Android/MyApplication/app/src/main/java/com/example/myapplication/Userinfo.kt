package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_userinfo.*

class Userinfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userinfo)
        val oneUser=intent.getSerializableExtra("userInfo")as?Users
        userInfo.text="欢迎"+oneUser?.userName.toString()+"\n你的密码是"+oneUser?.userPwd.toString()+"\n你的邮箱是"+oneUser?.email.toString()
    }
}
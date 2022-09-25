package com.example.chat.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chat.R
import android.widget.TextView
import android.content.Intent
import android.view.View
import com.example.chat.MainActivity
import java.util.*

class RegSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_success)

        //获取上个页面传来的用户名
        val data = intent.getStringExtra("Username")
        val dataTextView = findViewById<View>(R.id.regsuccuss_username) as TextView
        dataTextView.text = data

        //注册成功页面停留3秒后自动转到登录页面
        val localIntent = Intent(this, MainActivity::class.java) //你要转向的Activity
        val timer = Timer()
        val tast: TimerTask = object : TimerTask() {
            override fun run() {
                startActivity(localIntent) //执行
            }
        }
        timer.schedule(tast, (1000 * 3).toLong()) //3秒后
    }
}
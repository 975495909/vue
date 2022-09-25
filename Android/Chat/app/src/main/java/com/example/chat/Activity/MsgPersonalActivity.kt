package com.example.chat.Activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chat.R
import com.example.chat.kt.SQLiteHelper
import kotlinx.android.synthetic.main.activity_msg_personal.*
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.activity_user.username

class MsgPersonalActivity : AppCompatActivity() {
    //数据库
    val dbHelper= SQLiteHelper(this,"User.db",1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_msg_personal)

        //静态头像
        userimg1.setImageResource(R.drawable.me_img)

        user_back.setOnClickListener(){//返回按钮显示跳转到我页面
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        //在上个页面获取phone值
        val Phone=intent.getStringExtra("phone")
        val db=dbHelper.writableDatabase
        //数据库根据phone值定位用户信息，并显示
        val cursor=db.query("tbUserMsg", null,"phone=?", arrayOf(Phone),null,null,null)
        if(cursor.moveToFirst()){
            do{
                val username1=cursor.getString(cursor.getColumnIndex("username"))
                val phone1=cursor.getString(cursor.getColumnIndex("phone"))
                val email1=cursor.getString(cursor.getColumnIndex("email"))
                val sex1=cursor.getString(cursor.getColumnIndex("sex"))
                val city1=cursor.getString(cursor.getColumnIndex("city"))
                val school1=cursor.getString(cursor.getColumnIndex("school"))
                val bday1=cursor.getString(cursor.getColumnIndex("bday"))
                Log.d(this.toString(), "用户名：$username1，手机：$phone1，邮箱：$email1，性别：$sex1")
                username.text=username1.toString()
                phone.text=phone1.toString()
                email.text=email1.toString()
                sex.text=sex1.toString()
                city.text=city1.toString()
                school.text=school1.toString()
                birdata.text=bday1.toString()
            }while (cursor.moveToNext())
        }
        cursor.close()

    }
}
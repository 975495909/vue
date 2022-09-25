package com.example.chat.Activity

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.INFO
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.chat.MainActivity
import com.example.chat.R
import com.example.chat.kt.SQLiteHelper
import kotlinx.android.synthetic.main.activity_com_dialog.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.logging.Level.INFO

class UserActivity : AppCompatActivity() {

    //数据库
    val dbHelper= SQLiteHelper(this,"User.db",1)

    private var sp_log: SharedPreferences?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        supportActionBar?.hide() //隐藏默认标题

        btn_list.setOnClickListener() {//聊天按钮显示跳转到聊天页面
            val intent = Intent(this, ListfriendActivity::class.java)
            startActivity(intent)
        }

        //静态头像
        userimg.setImageResource(R.drawable.me_img)

        //用文件形式保存的用户名展示
        //username.text=showFile_username()
        //username.setText("Moon Chair Blade")

        //用SharedPreferences来获取Phone_ing文件的账号phone来定位用户的用户名
        sp_log=getSharedPreferences("Phone_ing", MODE_PRIVATE)
        var phone=sp_log?.getString("phone","").toString()
        //数据库根据phone值定位用户信息，并显示
        val db=dbHelper.writableDatabase
        val cursor=db.query("tbUserMsg", null,"phone=?", arrayOf(phone),null,null,null)
        if(cursor.moveToFirst()){
            do{
                val username1=cursor.getString(cursor.getColumnIndex("username"))
                Log.d(this.toString(), "用户名：$username1")
                username.text=username1.toString()
            }while (cursor.moveToNext())
        }
        cursor.close()

        btn_mod_user.setOnClickListener (){ //个人信息按钮显示跳转到个人信息页面
            val intent = Intent(this, MsgPersonalActivity::class.java)
            //将phone值传给下一个页面
            intent.putExtra("phone",phone)
            startActivity(intent)
        }


        btn_exit_user.setOnClickListener() {//退出按钮显示是否退出弹窗
            val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
            val view: View = LayoutInflater.from(this).inflate(R.layout.activity_com_dialog, null)
            val btn_yes: Button = view.findViewById(R.id.dia_yes)
            btn_yes.setOnClickListener(){//确认退出跳转到登录页面
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            val btn_no: Button = view.findViewById(R.id.dia_no)
            btn_no.setOnClickListener(){
                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)
            }
            dialog.setView(view).create().show()
        }

    }

    //用文件形式保存的用户名展示
    fun showFile_username():String{
        val content=StringBuilder()
        try {
            val input=openFileInput("file_username")
            val reader=BufferedReader(InputStreamReader(input))
            reader.use{
                it.forEachLine {
                    content.append(it)
                }
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
        return content.toString()
    }

}







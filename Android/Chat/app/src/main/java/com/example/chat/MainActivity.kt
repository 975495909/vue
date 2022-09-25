package com.example.chat

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.chat.Activity.ListfriendActivity
import com.example.chat.Activity.RegActivity
import com.example.chat.kt.SQLiteHelper
import kotlinx.android.synthetic.main.activity_chat_box.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user.*

class MainActivity : AppCompatActivity() {
//数据库
    val dbHelper= SQLiteHelper(this,"User.db",1)

    //用SharedPreferences来储存账号phone来定位登录后的信息
    private var sp_log: SharedPreferences?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide() //隐藏默认标题

        reg.setOnClickListener(){//注册按钮显示跳转到注册页面
            val intent=Intent(this,RegActivity::class.java)
            startActivity(intent)
        }

   //用SharedPreferences来储存账号密码完成自动输入账号密码
        sp_log=getSharedPreferences("Msg_Log", MODE_PRIVATE)


        btn_autologin.setOnClickListener(){//自动填写Msg_Log的账号密码
            passwd.setText(sp_log?.getString("password","").toString())
            userid.setText(sp_log?.getString("username","").toString())
        }

        btn_login.setOnClickListener(){//登录按钮显示跳转到好友列表页面

            var userId=userid.text.toString()
            var passWd=passwd.text.toString()
            var flag=0  //判定格式有无错误

            if(userId.length==0){
                userid.setError("用户名不能为空！")
                flag=1
            }else if(userId.length!=11){
                userid.setError("用户名格式错误！")
                flag=1
            }
            if(passWd.length==0){
                passwd.setError("密码不能为空！")
                flag=1
            }else if((passWd.length>0&&passWd.length<6)||passWd.length>12){
                passwd.setError("密码大于5且小于13个字符！")
            }

            if(flag==0){//账号密码格式无误

               /* //判断账号和密码是否匹配
                if(userId==sp_log?.getString("username","")&&
                    passWd==sp_log?.getString("password","")) {

                     //存储用sharep存储账号
                     sp_log=getSharedPreferences("Phone_ing", MODE_PRIVATE)
                    val editor=sp_log?.edit()
                    editor?.putString("phone",userId)
                    editor?.commit()

                    val intent=Intent(this,ListfriendActivity::class.java)
                    startActivity(intent)
                }

                else{
                    Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_LONG).show()

                }*/
                var password: String? =null
                val db=dbHelper.writableDatabase
                //从注册成功存入数据库的信息定位输出的手机号是否存在，
                val cursor=db.query("tbUserMsg", null,"phone=?", arrayOf(userId),null,null,null)
                if(cursor.moveToFirst()){
                    do{
                        password=cursor.getString(cursor.getColumnIndex("password"))
                        Log.d(this.toString(), "密码：$password")

                    }while (cursor.moveToNext())
                }
                cursor.close()
                //如果密码为空则不存在账号
                if (password==null){
                    Toast.makeText(this, "账号不存在", Toast.LENGTH_LONG).show()
                }
                else if(password!=passWd){
                    Toast.makeText(this, "账号或密码错误", Toast.LENGTH_LONG).show()
                }
                else{
                    //用SharedPreferences存储账号，方便登录成功后的定位
                    sp_log=getSharedPreferences("Phone_ing", MODE_PRIVATE)
                    val editor=sp_log?.edit()
                    editor?.putString("phone",userId)
                    editor?.commit()
                    //登录成功跳转好友列表页面
                    val intent=Intent(this,ListfriendActivity::class.java)
                    startActivity(intent)
                }
            }
        }

    }
}
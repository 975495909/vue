package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_uiactivity.*

class UIActivity : AppCompatActivity(),View.OnClickListener,RadioGroup.OnCheckedChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uiactivity)
        supportActionBar?.hide() //隐藏默认标题


        val city_data= listOf("广州","佛山","深圳","东莞")
        val adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,city_data)
        spinner_city.adapter=adapter

        val school_data= listOf("广东东软学院","深圳大学","华南师范大学","广东理工学院")
        val adapter1= ArrayAdapter(this,android.R.layout.simple_list_item_1,school_data)
        edt_school.setAdapter(adapter1)

         btn_signup.setOnClickListener(){
             //Toast.makeText(this,"你点击了注册按钮！",
              //   Toast.LENGTH_LONG).show()

             var username=edt_username.text.toString()
             var pwd1=edt_passwd.text.toString()
             var pwd2=edt_passwd2.text.toString()
             val email=edt_email.text.toString()


             if((username.length<4)||(username.length>10)){
                 edt_username.setError("用户账号必须为4-10个字符！")
             }else if(username.contains(" ")){
                 edt_username.setError("用户名不包含空格！")
             }
             else if (username.contains("")){
                 edt_username.setError("用户名不能为空！")
             }

             if(pwd1.contains("")){
                 edt_passwd.setError("密码不能为空！")
             }else if (pwd1!=pwd2){
                 edt_passwd2.setError("两次密码不一致！")
             }

             var emailStr=Regex("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
             if (emailStr.matches(email)==false){
                 img_email.setImageResource(R.drawable.error)
                 edt_email.setError("邮箱格式错误！")
             }else if (emailStr.matches(email)==true){
             img_email.setImageResource(R.drawable.right)
             }

             val oneUser=Users(username,pwd1,email)
             val intent=Intent(this,Userinfo::class.java)
             val userInfo=intent.putExtra("userInfo",oneUser)
             startActivity(intent)

         }
        btn_clear.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_clear->{
                Toast.makeText(this,"你点击了清除按钮！",
                Toast.LENGTH_LONG).show()
            }
        }
    }
    fun onClickLogin(v:View?){
        when(v?.id){
            R.id.btn_login->{
                Toast.makeText(this,"你点击了登录按钮！",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onCheckedChanged(group: RadioGroup?, rgbtnid:Int) {
        when(rgbtnid){
            R.id.rgbtn_boy->{
                Toast.makeText(this,
                    "你是男孩儿",Toast.LENGTH_SHORT).show()
            }
            R.id.rgbtn_girl->{
                Toast.makeText(this,
                    "你是女孩儿",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
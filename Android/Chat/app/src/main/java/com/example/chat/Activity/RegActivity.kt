package com.example.chat.Activity

import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.chat.MainActivity
import com.example.chat.R
import com.example.chat.kt.SQLiteHelper
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_chat_box.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_reg.*
import kotlinx.android.synthetic.main.activity_reg.passwd
import org.w3c.dom.Text
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter
import java.util.*
import java.util.Calendar as Calendar


class RegActivity : AppCompatActivity() {

    //自动登录用SharedPreferences来储存账号密码
    private var sp_log:SharedPreferences?=null

    //数据库,更新版本号+1
    val dbHelper=SQLiteHelper(this,"User.db",1)
    //val dbHelper=SQLiteHelper(this,"UserMsg.db",4)

    //全局定义出生日期，方便数据库获取该数据
    var mDate: String? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        supportActionBar?.hide() //隐藏默认标题


        log.setOnClickListener(){//登录按钮显示跳转到登录页面
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        //Spinner控件
        val city_data= listOf("","广州","佛山","深圳","东莞","北京","南京","杭州","重庆")
        val adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,city_data)
        city.adapter=adapter
        //city.setSelection(0)//设置默认值为0
        var selected_city: String? =null
        city.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{//获取选中值
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selected_city=city_data[position]
                //Toast.makeText(this@RegActivity, "选中的城市：$selected_city",Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        //AutoCompleteTextView控件
        val school_data= listOf("广东东软学院","深圳大学","华南师范大学","广东理工学院","清华大学","北京大学")
        val adapter1= ArrayAdapter(this,android.R.layout.simple_list_item_1,school_data)
        school.setAdapter(adapter1)



        btn_reg.setOnClickListener(){//注册按钮显示跳转到注册成功页面

            var flag=0  //判定格式有无错误
            var userName=username.text.toString()
            var passWd=passwd.text.toString()
            var R_passWd=r_passwd.text.toString()
            var Phone=phone.text.toString()
            var Email=email.text.toString()
            var Sex=findViewById<View>(sex.checkedRadioButtonId) as RadioButton
            var selected_school=school.text.toString()


           /*sex.setOnCheckedChangeListener{ group,checkedId ->
                Sex=findViewById<View>(checkedId) as RadioButton
                Toast.makeText(this,"性别"+Sex.text,Toast.LENGTH_LONG).show()
            }*/


            /*city.onItemSelectedListener=object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val info = city.getSelectedItem() as String
                    Toast.makeText(this@RegActivity, info, Toast.LENGTH_LONG).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }*/

            if(userName.length==0){
                username.setError("用户名不能为空！")
                flag=1
            }else if(userName.length>10){
                username.setError("用户名小于11个字符！")
                flag=1
            }

            if(passWd.length==0){
                passwd.setError("密码不能为空！")
                flag=1
            }else if((passWd.length>0&&passWd.length<6)||passWd.length>12){
                passwd.setError("密码大于5且小于13个字符！")
            }

            if(R_passWd.length==0){
                r_passwd.setError("确认密码不能为空！")
                flag=1
            }else if(R_passWd!=passWd){
                r_passwd.setError("两次密码不一致！")
            }

            if(Phone.length==0){
                phone.setError("手机号不能为空！")
                flag=1
            }else if(Phone.length!=11){
                phone.setError("手机号格式错误！")
                flag=1
            }

            var emailStr=Regex("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
            if(Email.length==0){
                email.setError("邮箱不能为空！")
                flag=1
            }else if (emailStr.matches(Email)==false){
                email.setError("邮箱格式错误！")
                flag=1
            }

            if(flag==0){  //注册信息格式无误

                //Toast.makeText(this@RegActivity, "出生日期：$mDate",Toast.LENGTH_LONG).show()
               // Toast.makeText(this@RegActivity, "选中的学校：$selected_school",Toast.LENGTH_SHORT).show()
                //勾选自动登录，将手机号和密码存入Msg_Log文件中
                val cb_log:CheckBox=cb_login
                if(cb_log.isChecked){//判断是否有勾选自动登录
                    sp_log=getSharedPreferences("Msg_Log", MODE_PRIVATE)
                    val editor=sp_log?.edit()
                    editor?.putString("username",Phone)
                    editor?.putString("password",passWd)
                    editor?.commit()
                }

                //用文件存储的方式存储信息
                fileSave_username(userName)


                //将注册信息录入数据库
                val db=dbHelper.writableDatabase
                val value1=ContentValues().apply {
                    put("username",userName)
                    put("password",passWd)
                    put("phone",Phone)
                    put("email",Email)
                    put("sex",Sex.text.toString())
                    put("city",selected_city.toString())
                    put("school",selected_school)
                    put("bday",mDate.toString())
                }
                db.insert("tbUserMsg",null,value1)

                //注册成功，将用户名传给下一个页面
                val intent= Intent(this,RegSuccessActivity::class.java)
                intent.putExtra("Username",userName)
                startActivity(intent)
            }
        }
    }

    fun buttonFunc(view: View) {//出生日期
        when (view.id) {
            R.id.birdata -> {
                // 日期选择器
                val ca = Calendar.getInstance()
                var mYear = ca[Calendar.YEAR]
                var mMonth = ca[Calendar.MONTH]
                var mDay = ca[Calendar.DAY_OF_MONTH]

                val datePickerDialog = DatePickerDialog(
                    this,
                    { _, year, month, dayOfMonth ->
                        mYear = year
                        mMonth = month
                        mDay = dayOfMonth
                        mDate = "${year}年${month + 1}月${dayOfMonth}日"
                        // 将选择的日期赋值给TextView
                        birdata.text = mDate
                    },
                    mYear, mMonth, mDay
                )
                datePickerDialog.show()
            }
        }
    }

    fun fileSave_username(inputEditText: String){//用文件存储的方式存储信息
        try {
            val output=openFileOutput("file_username",Context.MODE_PRIVATE)
            val writer=BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inputEditText)
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
    }

}


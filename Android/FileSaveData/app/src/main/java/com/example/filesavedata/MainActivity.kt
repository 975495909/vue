package com.example.filesavedata

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fileSaveData.setOnClickListener(){
            val data=editText.text.toString()//通过按钮拿到xml的值
            fileSave(data)
        }

        showFileData.setOnClickListener(){
            showData.text=showData()
        }

        btn_spfsSaveData.setOnClickListener(){
            val name=edt_name.text.toString()
            val age=edt_age.text.toString()
            val editor=getSharedPreferences("shareData",Context.MODE_PRIVATE).edit()
            //调用SharePreferences对象的edit（）方法获取一个Editor对象
            editor.putString("name",name)
            editor.putString("age",age)
            //editor.putInt("ageint",20)
            //editor.putBoolean("boolean",false)
            //editor.putFloat("float",5.0f)
            //向SharePreferences.editor对象中添加各种类型的数据
            editor.apply()
            //调用apply（）方法将添加的数据提交，存储数据
        }

        btn_showspfsData.setOnClickListener(){
            var show=getSharedPreferences("shareData",Context.MODE_PRIVATE)
            val name=show.getString("name","")
            val age=show.getString("age","")
            //val ageint=show.getInt("ageint",0)
            //val boolean=show.getBoolean("boolean",false)
            //val float=show.getFloat("float",0f)
            tv_showData.text=name+age
            //+ageint+boolean+float
        }

        btn_SQL.setOnClickListener(){
            val it=Intent(this,SQLiteSavaData::class.java)
            startActivity(it)
        }
    }
    fun fileSave(inputText: String){//保存文件
        try {
            val output=openFileOutput("fileData",Context.MODE_APPEND)
            val writer=BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inputText)
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
    }

    fun showData():String{//读出文件
        val content=StringBuilder()
        try {
            val input=openFileInput("fileData")
            val reader=BufferedReader(InputStreamReader(input))
            reader.use{
                it.forEachLine{
                    content.append(it)
                }
            }

        }catch (e:IOException){
            e.printStackTrace()
        }
        return content.toString()
    }
}

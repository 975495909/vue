package com.example.activitytest

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_second.view.*
import kotlinx.android.synthetic.main.activity_study3.*
import org.w3c.dom.Text

class Study3Activity : AppCompatActivity() ,RadioGroup.OnCheckedChangeListener{

    val dbHelper=MySQLiteHelper(this,"User.db",2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study3)

        dbHelper.writableDatabase



        btn_log.setOnClickListener(){

            val db=dbHelper.writableDatabase
            val value1=ContentValues().apply {
                put("username",username.text.toString())
                put("password",passwd.text.toString())
               // put("sex",Sex.text.toString())
            }
            db.insert("Msg1",null,value1)
        }

    }
    override fun onCheckedChanged(group: RadioGroup?, rgid:Int) {
        when(rgid){
            R.id.sex_boy->{
                Toast.makeText(
                    this,
                    "你是nan" , Toast.LENGTH_SHORT
                ).show()
            }
            R.id.sex_girl->{
                Toast.makeText(this,
                    "你是nv",Toast.LENGTH_SHORT).show()
            }
        }
    }

}
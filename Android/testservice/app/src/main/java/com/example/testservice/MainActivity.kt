package com.example.testservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val updateText=1
    val handler=object :Handler(){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                updateText->{
                    textView.text="dslsls"
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_changeTextView.setOnClickListener(){
            thread {
                val msg=Message()
                msg.what=updateText
                handler.sendMessage(msg)
               // textView.text="lalalala"
            }
        }
    }
}
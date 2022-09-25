package com.example.threadtgh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val updateText=1//定义一个Int变量，用于更新TextView这个动作
    val handler=object:Handler(){
        //创建一个Handler对象(有发送信息与处理消息作用）
        override fun handleMessage(msg: Message) {
            //重写父类的handMessage()方法,用来收接子线程hander.sendMessage()传递过来的信息。
            when(msg.what){
                updateText->changeText.text="广东东软学院软件工程系欢迎你！"
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_changeContent.setOnClickListener{
            thread {
                val msg=Message()
                //创建一个Message对象，并将what字段的值指定为updateText
                msg.what=updateText
                handler.sendMessage(msg)
              //changeText.text="广东东软学院软件工程系欢迎你！"
            }
        }
    }
}
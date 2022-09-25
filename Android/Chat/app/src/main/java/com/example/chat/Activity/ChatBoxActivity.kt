package com.example.chat.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat.Adapter.MsgAdapter
import com.example.chat.Entity.Msg
import com.example.chat.R
import kotlinx.android.synthetic.main.activity_chat_box.*

class ChatBoxActivity : AppCompatActivity(),View.OnClickListener {

    //定义聊天信息适配器
    private val msgList = ArrayList<Msg>()

    private lateinit var adapter: MsgAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_box)

        supportActionBar?.hide() //隐藏默认标题

        //接收好友列表里的好友名称
        val data=intent.getStringExtra("name_friend")
        box_name_friend.text=data

        chat_back.setOnClickListener(){//返回按钮显示跳转到好友列表
            val intent=Intent(this,ListfriendActivity::class.java)
            startActivity(intent)
        }

      //静态
        initMsg()
        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        if(!::adapter.isInitialized){
            adapter= MsgAdapter((msgList))
        }
        recyclerView.adapter=adapter
        send.setOnClickListener(this)

    }
    override fun onClick(v:View?){
        when(v){
            send->{
                val content=inputText.text.toString()
                if(content.isNotEmpty()){
                    val msg=Msg(content,Msg.TYPE_SENT)
                    msgList.add(msg)
                    adapter.notifyItemInserted(msgList.size-1) //当有新消息时，刷新RecylerView中的显示
                    recyclerView.scrollToPosition(msgList.size-1) //将 RecyclerView定位到最后一行
                    inputText.setText("")//清空输入框中的内容
                }
            }
        }
    }

    private fun initMsg(){
        val msg1=Msg("你好兄弟！",Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2=Msg("你好兄弟！",Msg.TYPE_SENT)
        msgList.add(msg2)
        val msg3=Msg("吃饭了吗？！",Msg.TYPE_RECEIVED)
        msgList.add(msg3)
    }

}
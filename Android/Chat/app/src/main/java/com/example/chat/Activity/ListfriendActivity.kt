package com.example.chat.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.Adapter.FriendAdapter
import com.example.chat.Entity.Friend
import com.example.chat.R
import kotlinx.android.synthetic.main.activity_chat_box.*
import kotlinx.android.synthetic.main.activity_listfriend.*
import kotlinx.android.synthetic.main.activity_listfriend.friendView
import kotlinx.android.synthetic.main.listfriend_item.*
import kotlinx.android.synthetic.main.listfriend_item.view.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.lang.Exception
import kotlin.concurrent.thread

class ListfriendActivity : AppCompatActivity() {

    //定义好友列表的适配器
    private val friendList=ArrayList<Friend>()
    var adapter:FriendAdapter?=null

    //天气情况初始化
    private var city: String? =null
    private var province: String?=null
    private var weather: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listfriend)
        supportActionBar?.hide() //隐藏默认标题

        btn_user.setOnClickListener(){//我按钮显示跳转到客户页面
            val intent= Intent(this,UserActivity::class.java)
            startActivity(intent)
        }

        day.setOnClickListener(){//天气按钮弹出天气情况
            sendRequestWithOkHttp()
        }

        //静态
        initFriend()

        val layoutManager=LinearLayoutManager(this)

        friendView.layoutManager=layoutManager
        adapter=FriendAdapter(friendList)
        friendView.adapter=adapter


    }
    //好友点击事件
    fun onItemClick(view:View){
        val childAdapterPosition:Int=friendView.getChildAdapterPosition(view) //调出”第几个好友“
        Toast.makeText(this, "双击和"+friendList[childAdapterPosition].name_friend+"聊天"
            , Toast.LENGTH_SHORT).show()

        view.setOnClickListener(){//好友按钮显式跳转到聊天页面,并且传输好友名称为聊天页面标题
            val intent= Intent(this,ChatBoxActivity::class.java)
            intent.putExtra("name_friend",friendList[childAdapterPosition].name_friend)
            startActivity(intent)
        }
    }


    private fun initFriend(){
        repeat(1){
            friendList.add(Friend("芳芳",R.drawable.friend1))
            friendList.add(Friend("二牛牛",R.drawable.friend5))
            friendList.add(Friend("屠呦呦UU",R.drawable.friend10))
            friendList.add(Friend("张三张三",R.drawable.friend3))
            friendList.add(Friend("EatBigApple",R.drawable.friend7))
            friendList.add(Friend("李四蛋",R.drawable.friend4))
            friendList.add(Friend("话画虎画画",R.drawable.friend6))
            friendList.add(Friend("Lvy Baba",R.drawable.friend8))
            friendList.add(Friend("钟南山",R.drawable.friend9))
            friendList.add(Friend("拉拉辣辣腊",R.drawable.friend2))
        }

    }

    private fun sendRequestWithOkHttp(){
        thread {
            try {
                //创建一个OKHttpClient的实例
                val client= OkHttpClient()
                //发起一条HTTP请求前，创建一个Request对象
                val request = Request.Builder()
                    .url("http://192.168.208.2:85/day.json")
                    .addHeader("Content-Type", "charset=UTF-8")
                    .build()
                //调用newCall（）方法创建一个call对象
                // 并调用execute（）发送请求并获取服务器返回的数据
                val response=client.newCall(request).execute()
                //response.body返回具体内容
                val responseData=response.body?.string()
                if (responseData !=null){
                    parseJSONWithJSONObject(responseData)
                    Toast.makeText(this, "所在位置：$province$city，天气：$weather",Toast.LENGTH_LONG).show()
                }
            }catch (e: Exception){
                e.printStackTrace()
                //在子线程可调用Toast的异常情况处理
                Looper.prepare()
                Toast.makeText(this, "所在位置：$province$city，天气：$weather",Toast.LENGTH_LONG).show()
                Looper.loop()
            }
        }
    }

    //使用JSONObject解析JSON文件
    private  fun parseJSONWithJSONObject(jsonData:String){
        try {
            val jsonArray= JSONArray(jsonData)
            //定义一个JSON数组
            for(i in 0 until jsonArray.length()){
                //for循环对数组进行遍历，而数组中每一个元素都是一个JSONObject对象
                val jsonObject=jsonArray.getJSONObject(i)
                //每一个JSONObject对象中包含了city、province、weather这些数据
                //只需调用该对象的getString()方法将这些数据取出
                city=jsonObject.getString("city")
                province=jsonObject.getString("province")
                weather=jsonObject.getString("weather")


                Log.d("jSONObject","city is $city")
                Log.d("jSONObject","province is $province")
                Log.d("jSONObject","weather is $weather")
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

}
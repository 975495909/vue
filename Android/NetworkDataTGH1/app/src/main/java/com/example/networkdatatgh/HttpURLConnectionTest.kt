package com.example.networkdatatgh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_http_urlconnection_test.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread


class HttpURLConnectionTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_urlconnection_test)
        btn_sendRequest.setOnClickListener{
           //sendRequestWithHttpURLConnectin()
            sendRequestWithOkHttp()

        }

    }
    private fun sendRequestWithHttpURLConnectin(){
        thread{
            var connection:HttpURLConnection?=null
            try{
                val response=StringBuilder()
                val url= URL("https://www.baidu.com")
                connection=url.openConnection() as HttpURLConnection
                connection.requestMethod="GET"
                connection.connectTimeout=8000
                connection.readTimeout=8000
                val input=connection.inputStream
                val reader=BufferedReader(InputStreamReader(input))
                reader.use{
                    reader.forEachLine {
                        response.append(it)
                    }
                }
               showResponse(response.toString())
            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                connection?.disconnect()
            }

        }
    }
    private fun sendRequestWithOkHttp(){
        thread {
            try {
                //创建一个OKHttpClient的实例
                val client=OkHttpClient()
                //发起一条HTTP请求前，创建一个Request对象
                val request = Request.Builder()
                    //.url("http://www.baidu.com")
                    //.url("http://10.0.2.2/get_data.xml")
                    .url("http://10.0.2.2/get_data.json")
                    //.addHeader("Connection","close")
                    .build()
                //调用newCall（）方法创建一个call对象
                // 并调用execute（）发送请求并获取服务器返回的数据
                val response=client.newCall(request).execute()
                //response.body返回具体内容
                val responseData=response.body?.string()
                if (responseData !=null){
                    //showResponse(responseData)
                    //parseXMLWithPull(responseData)
                    //parseJSONWithJSONObject(responseData)
                    parseJSONWithGSON(responseData)

                }
            }catch (e: java.lang.Exception){
                e.printStackTrace()
            }
        }
    }
    private fun showResponse(response: String) {
        runOnUiThread {
            // 在这里进行UI操作，将结果显示到界面上
            responseText.text=response
        }
    }
    //xmlPull方式来解析XML
    private fun parseXMLWithPull(xmlData:String){
        try {
            val factory=XmlPullParserFactory.newInstance()
            //创建一个XmlPullParserFactory实例
            val xmlPullParser=factory.newPullParser()
            //通过实例得到一个XmlPullParser对象
            xmlPullParser.setInput(StringReader(xmlData))
            //调用setInput()方法将服务器返回的XML数据设置进去
            var eventType=xmlPullParser.eventType
            //得到当前的解析事件
            var id=""
            var name=""
            var version=""
            while (eventType!=XmlPullParser.END_DOCUMENT){
                //当解析事件不等于XmlPullParser.END_DOCUMENT
                //说明解析工作还没有完成，调用.next()方法可以获取下一个解析事件
                val nodeName=xmlPullParser.name
                when(eventType){
                    XmlPullParser.START_TAG->{
                        when(nodeName){
                            "id" -> id=xmlPullParser.nextText()
                            "name"-> name=xmlPullParser.nextText()
                            "version" -> version=xmlPullParser.nextText()
                        }
                    }
                    XmlPullParser.END_TAG->{
                      if("app"==nodeName) {
                          Log.d("xmlPull","id is $id")
                          Log.d("xmlPull","name is $name")
                          Log.d("xmlPull","version is $version")
                      }
                    }
                }
                eventType=xmlPullParser.next()
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    //使用JSONObject解析JSON文件
    private  fun parseJSONWithJSONObject(jsonData:String){
       try {
           val jsonArray=JSONArray(jsonData)
           //定义一个JSON数组
           for(i in 0 until jsonArray.length()){
               //for循环对数组进行遍历，而数组中每一个元素都是一个JSONObject对象
               val jsonObject=jsonArray.getJSONObject(i)
               //每一个JSONObject对象中包含了id,name,version这些数据
               // 只需调用该对象的getString()方法将这些数据取出
               val id=jsonObject.getString("id")
               val name=jsonObject.getString("name")
               val version=jsonObject.getString("version")
               Log.d("jSONObject","id is $id")
               Log.d("jSONObject","name is $name")
               Log.d("jSONObject","version is $version")
           }
       }catch (e:Exception){
         e.printStackTrace()
       }
    }
    //使用GSON方法
    private fun parseJSONWithGSON(jsonData:String){
        val gson=Gson()
        val str="{“name”:”zhangsan”,”age”:20}"
        var people: People? =null
        people=gson.fromJson(str,People::class.java)
        Log.d("gson","name is ${people.name}")
        Log.d("gson","age is ${people.age}")
       val typeOf=object:TypeToken<List<App>>(){}.type
        //TypeToken是google提供的一个解析Json数据的类库中一个类
        //Gson通过借助TypeToken获取泛型参数的类型的方法
        //TypeToken<List<App>>(){}加上大括号表示你new了一个匿名内部类的对象。
        //这个匿名类继承自TypeToken类
        val appList=gson.fromJson<List<App>>(jsonData,typeOf)
        for(app in appList){
            Log.d("gson","id is ${app.id}")
            Log.d("gson","name is ${app.name}")
            Log.d("gson","version is ${app.version}")
        }
    }

}
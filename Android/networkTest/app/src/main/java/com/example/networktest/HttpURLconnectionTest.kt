package com.example.networktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_http_urlconnection_test.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class HttpURLconnectionTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_urlconnection_test)

        btn_sendHttpURLConnection.setOnClickListener(){
            //sendHttpURLConnection()
            sendRequestWithOkHttp()
        }

    }
    private  fun sendHttpURLConnection(){
        thread {
            var connection:HttpURLConnection?=null
            try {
                val url=URL("https://www.baidu.com")
                connection=url.openConnection() as HttpURLConnection
                connection.requestMethod="GET"
                connection.readTimeout=5000
                connection.readTimeout=5000
                val response=StringBuilder()
                val input=connection.inputStream
                val reader=BufferedReader(InputStreamReader(input))
                reader.use {
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
    private fun showResponse(response:String){
        runOnUiThread{
            textView.text=response
        }
    }
    //okhttp
    private  fun sendRequestWithOkHttp(){
        thread {
            try {
                val client=OkHttpClient()
                val request=Request.Builder()
                    .url("https://www.baidu.com")
                    .build()
                val response=client.newCall(request).execute()
                val responseData=response.body?.string()
                if(responseData!=null){
                    showResponse(responseData)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}
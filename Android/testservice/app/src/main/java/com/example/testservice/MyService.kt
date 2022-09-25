
package com.example.testservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread

class MyService : Service() {

    override fun onCreate() {
        Log.d("MyService","服务已创建")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MyService","服务已启动")
        return super.onStartCommand(intent, flags, startId)
        thread {

        }
    }

    override fun onDestroy() {
        Log.d("MyService","服务已关闭")
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
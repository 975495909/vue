package com.example.threadtgh

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread

class MyService : Service() {
    private val mBinder=DownloadBinder()
    class DownloadBinder:Binder(){
        fun startDownload(){
            Log.d("MyService","开始下载...")
        }
        fun getprogress():Int{
            Log.d("MyService","进度查询...")
            return 0
        }
    }

    override fun onBind(intent: Intent): IBinder {
        //onBind是Service类唯一的抽象方法，信息交流渠道；Activity可以通过该方法去管理Service
        return mBinder
        //它返回一个用来与service交互的IBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("MyService","服务已创建！")
        val manager=getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel=NotificationChannel("my_service","前台服务通知",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent=Intent(this,NotificationTest::class.java)
        val pi=PendingIntent.getActivity(this,0,intent,0)
        val notification=NotificationCompat.Builder(this,"my_service")
            .setContentTitle("通知标题")
            .setContentText("这里是通知的内容。。。")
            .setSmallIcon(R.drawable.small_icon)
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.large_icon))
            .setContentIntent(pi)
            .build()
        startForeground(1,notification)
    }
    //Service创建时调用onCreate()方法
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
     thread {  }
        
    }
    //Service启动时调用onStartCommand()方法，
    // 将要执行动作的逻辑写在该方法里

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService","服务已销毁！")
    }
    //Service销毁时调用onDestroy()
    //以上3个方法是Service中最常用的3个方法

}
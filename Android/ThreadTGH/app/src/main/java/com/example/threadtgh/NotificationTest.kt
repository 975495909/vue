package com.example.threadtgh

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_notification_test.*

class NotificationTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_test)
        val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
          val channel=NotificationChannel("normal","Normal",NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }
        btn_sendNotice.setOnClickListener{
            //下面使用NotificationCompat类构建一个Notification对象，
            //这个类构造器创建的Notification对象在所有android系统版本上都能正常工作
           val intent=Intent(this,ShowNotification::class.java)
            val pi=PendingIntent.getActivity(this,0,intent,0)
            val notification=NotificationCompat.Builder(this,"normal")
                .setContentTitle("通知标题")
                .setContentText("这是通知的内容。。。")
                .setSmallIcon(R.drawable.small_icon)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.large_icon))
                .setContentIntent(pi)
                .build()
            manager.notify(1,notification)
        }
    }
}
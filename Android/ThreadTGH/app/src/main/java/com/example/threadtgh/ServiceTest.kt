package com.example.threadtgh

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import kotlinx.android.synthetic.main.activity_service_test.*

class ServiceTest : AppCompatActivity() {
lateinit var downloadBinder:MyService.DownloadBinder
private val connection=object :ServiceConnection{
    //创建一个匿名类ServiceConnection，创建一个服务连接
    // 实现必须重写两个方法：onServiceConnected()方法，在Activity与Service成功绑定的时候调用。
    override fun onServiceConnected(name: ComponentName, service: IBinder?) {
        downloadBinder=service as MyService.DownloadBinder
        //Service类向下转型得到一个DownloadBinder实例
        downloadBinder.startDownload()
        downloadBinder.getprogress()
    }
    override fun onServiceDisconnected(name: ComponentName) {
    //onServiceDisconnected()当Service进程崩溃或者被杀掉的时候才会调用
    }
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_test)
        btn_startService.setOnClickListener{
            val intent= Intent(this,MyService::class.java)
            startService(intent)//启动Service
        }
        btn_stopService.setOnClickListener{
            val intent=Intent(this,MyService::class.java)
            stopService(intent)//停止Service
        }
        btn_bindService.setOnClickListener{
            val intent=Intent(this,MyService::class.java)
            bindService(intent,connection,Context.BIND_AUTO_CREATE)
            //绑定Service
        }
        btn_unbindService.setOnClickListener{
            unbindService(connection)
            //解绑Service
        }
        btn_IntentService.setOnClickListener{
            Log.d("ServiceTest","Thread id is ${Thread.currentThread().name}")
            val intent=Intent(this,MyIntentService::class.java)
            startService(intent)
        }
    }
}
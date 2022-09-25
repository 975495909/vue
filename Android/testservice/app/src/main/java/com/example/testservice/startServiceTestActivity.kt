package com.example.testservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_start_service_test.*

class startServiceTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_service_test)
        btn_startService.setOnClickListener(){
            val intent=Intent(this,MyService::class.java)
            startService(intent)
        }
        btn_stopService.setOnClickListener(){
            val intent=Intent(this,MyService::class.java)
            stopService(intent)
        }
    }
}
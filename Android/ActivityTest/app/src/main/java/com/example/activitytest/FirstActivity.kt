package com.example.activitytest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toast.makeText
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        btn1.setOnClickListener(){
            makeText(this,"吐司",
              Toast.LENGTH_SHORT).show()
        }

        btn2.setOnClickListener(){
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        btn3.setOnClickListener(){
            val intent=Intent("com.example.activitytest.action_start")
            intent.addCategory("com.example.activitytest.my_category")
            startActivity(intent)
        }

        btn4.setOnClickListener {
            val intent=Intent(Intent.ACTION_DIAL)
            intent.data= Uri.parse("tel:10086")
            startActivity(intent)
        }

        btn5.setOnClickListener(){
            val intent=Intent(Intent.ACTION_VIEW)
            intent.data= Uri.parse("http://www.baidu.com")
            startActivity(intent)
        }

        btn6.setOnClickListener(){
            val data="Hi，我是FirstActivity传过来的数据(显式）"
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("first_data",data)
            startActivity(intent)
        }

        btn7.setOnClickListener(){
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent,1)
        }

        btn8.setOnClickListener() {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data= Uri.parse("smsto:555");
            startActivity(intent)
        }

        btn9.setOnClickListener() {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

        btn10.setOnClickListener() {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data= Uri.parse("content://contacts/people");
            startActivity(intent)
        }

        btn_study3.setOnClickListener(){
            val intent = Intent(this, Study3Activity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item -> Toast.makeText(this,
                "你点击了添加按钮",Toast.LENGTH_LONG).show()
            R.id.mod_item -> Toast.makeText(this,
                "你点击了移除按钮",Toast.LENGTH_LONG).show()
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1->if(resultCode== RESULT_OK){
                val returnData=data?.getStringExtra("return_data")
                return_data.text=returnData
                Log.d("FirstActivity",returnData.toString())
            }
        }
    }
}
package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_first.*





class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
       // val btn1:Button=findViewById(R.id.btn1)
        btn1.setOnClickListener(){
            makeText(this,"你点击了按钮",
                Toast.LENGTH_SHORT).show()
        }
        btn2.setOnClickListener(){
            val data="你好，我是firstActivity传过来的数据"
            val intent =Intent(this, MainActivity::class.java)
            intent.putExtra("FirstData",data)
            //startActivity(intent)
            startActivityForResult(intent,1)
        }

        btn3.setOnClickListener(){
            val intent=Intent("com.example.myapplication.action_start")
            intent.addCategory("com.example.myapplication.my_category")
            startActivity(intent)
        }
        btn4.setOnClickListener(){
            val intent=Intent(Intent.ACTION_VIEW)
            intent.data= Uri.parse("http://www.baidu.com")
            startActivity(intent)
        }

        btn5.setOnClickListener {
            val intent=Intent(Intent.ACTION_DIAL)
            intent.data= Uri.parse("tel:10086")
            startActivity(intent)
        }

        btn6.setOnClickListener {
            val intent=Intent(Intent.ACTION_VIEW)
            intent.data= Uri.parse("geo:38.899533,-77.036476")
            startActivity(intent)
        }

        btn7.setOnClickListener {
            val intent=Intent(Intent.ACTION_DIAL)
            intent.data= Uri.parse("tel:10086")
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
                val returnData=data?.getStringExtra("data_return")
                return_data.text=returnData
                Log.d("FirstActivity",returnData.toString())
            }
        }
    }

}
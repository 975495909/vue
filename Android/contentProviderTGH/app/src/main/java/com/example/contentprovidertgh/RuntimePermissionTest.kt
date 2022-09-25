package com.example.contentprovidertgh

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_runtime_permission_test.*
import java.util.jar.Manifest

class RuntimePermissionTest : AppCompatActivity() {

    private val contactsList = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    val dbHelper=SQLite(this,"PhoneMsg.db",1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_runtime_permission_test)
        btn_makecall.setOnClickListener{
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)
            !=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),1)
                            }else{
                                call()
                            }
        }

        btn_readcall.setOnClickListener(){

            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.READ_CONTACTS), 2)
            } else {
                readpeople()
            }

        }

        btn_sendsms.setOnClickListener(){
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.SEND_SMS), 3)
            } else {
                sendSms()
            }

        }

        btn_camera.setOnClickListener(){
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.CAMERA), 4)
            } else {
                openCamera()
            }
        }

        btn_showpeople.setOnClickListener(){
            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contactsList)
            contactsView.adapter = adapter
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.READ_CONTACTS), 5)
            } else {
                readContacts()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1->{
                if(grantResults.isNotEmpty()){
                    if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        call()//打开电话
                    }
                    else if (grantResults[1]==PackageManager.PERMISSION_GRANTED){
                        readpeople()//打开通讯录
                    }
                    else if (grantResults[2]==PackageManager.PERMISSION_GRANTED){
                        sendSms()//打开短信
                    }
                    else if(grantResults[3]==PackageManager.PERMISSION_GRANTED){
                        openCamera() //打开相机
                    }
                    else if(grantResults[4]==PackageManager.PERMISSION_GRANTED){
                        readContacts()//获取通讯录信息并录入数据库
                    }
                }else{
                    Toast.makeText(this,"你无权限!",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun call(){
        try {
            val intent=Intent(Intent.ACTION_CALL)
            intent.data= Uri.parse("tel:10086")
            startActivity(intent)
        }catch (e:SecurityException){
            e.printStackTrace()
        }
    }

    private  fun readContacts(){
        val db=dbHelper.writableDatabase
        //查询联系人数据
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        null,null,null,null)?.apply {
            while (moveToNext()){
                //获取联系人姓名
                val displayName=getString(getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                ))
                //获取联系人手机号
                val number=getString(getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                ))
                contactsList.add("$displayName\n$number")
                //存入数据库
                val value1=ContentValues().apply {
                    put("name",displayName.toString())
                    put("number",number.toString())
                }
                db.insert("People",null,value1)
            }
            adapter.notifyDataSetChanged()
            close()
        }
    }

    private fun readpeople(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.data= Uri.parse("content://contacts/people");
        startActivity(intent)
    }

    private  fun sendSms(){
        try {
            val intent=Intent(Intent.ACTION_SENDTO)
            intent.data= Uri.parse("smsto:10086")
            startActivity(intent)
        }catch (e:SecurityException){
            e.printStackTrace()
        }
    }

    private fun openCamera(){
        try {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }catch (e:SecurityException){
            e.printStackTrace()
        }
    }
}
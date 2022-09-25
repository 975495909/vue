package com.example.filesavedata

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sqlite_sava_data.*

class SQLiteSavaData : AppCompatActivity() {
    //更新版本号要+1
    val dbHelper=MySQLiteHelper(this,"BookStore.db",3)

    //查看数据库优化
    private val bookList = ArrayList<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_sava_data)

        //查看数据库优化
        initBook()
        val adapter=BookAdpter(this,R.layout.book_item,bookList)
        ListView.adapter=adapter//绑定ListView


        //实例化一个DatabaseHelper对象，传入相关参数
        btn_createDatabase.setOnClickListener(){
            dbHelper.writableDatabase
            //调用writableDatabase实例方法，完成数据库的创建
            Toast.makeText(this,"创建数据库成功", Toast.LENGTH_SHORT).show()
        }
        btn_addData.setOnClickListener(){//添加数据
            val db=dbHelper.writableDatabase
            val values1=ContentValues().apply {
                put("name","第一行方方")
                put("author","芳芳")
                put("pages","700")
                put("price",99)
            }
            db.insert("Book",null,values1)
        }
        btn_updateData.setOnClickListener{//修改数据
            val db=dbHelper.writableDatabase
            val values=ContentValues()
            values.put("price",56)
            db.update("book",values,
                "author=?", arrayOf("郭霖"))
        }
        btn_delData.setOnClickListener{//删除数据
            val db=dbHelper.writableDatabase
            db.delete("book","pages=?",arrayOf("700"))
        }

        btn_selectData.setOnClickListener(){  //未优化型的查看数据
            val db=dbHelper.writableDatabase
            val cursor=db.query("book",null,null,null,null,null,null)
            tv_selectData.text=""
            if(cursor.moveToFirst()){
                do{
                    val name=cursor.getString(cursor.getColumnIndex("name"))
                    val author=cursor.getString(cursor.getColumnIndex("author"))
                    val pages=cursor.getInt(cursor.getColumnIndex("pages"))
                    val price=cursor.getDouble(cursor.getColumnIndex("price"))
                    tv_selectData.text=tv_selectData.text.toString()+ "书名："  +name+
                            "作者："+author+"总页数："+pages+"价格："+price+"\n"
                }while (cursor.moveToNext())
            }
            cursor.close()
        }
    }

    private fun initBook(){
        val db = dbHelper.writableDatabase
        val cursor = db.query("Book", null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                //遍历cursor对象，取出数据
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val author = cursor.getString(cursor.getColumnIndex("author"))
                val page = cursor.getInt(cursor.getColumnIndex("pages"))
                val price = cursor.getDouble(cursor.getColumnIndex("price"))
                //showData.text=showData.text.toString()+"\n"+name+author+price+page
                bookList.add(Book(id, name, author, page.toInt(), price.toDouble()))

            } while (cursor.moveToNext())
        }
        cursor.close()
    }
}
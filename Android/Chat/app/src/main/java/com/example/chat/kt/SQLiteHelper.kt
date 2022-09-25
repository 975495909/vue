package com.example.chat.kt

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(val context: Context,name:String,version: Int)
    : SQLiteOpenHelper(context,name,null,version){
        private val createtbUserMsg="create table tbUserMsg(" +
                "id integer primary key autoincrement," +
                "username text," +
                "password text," +
                "phone text," +
                "email text," +
                "sex text," +
                "city text," +
                "school text," +
                "bday text)"

/* private val createUserMsg1="create table UserMsg1(" +
         "id integer primary key autoincrement," +
         "username text," +
         "password text," +
         "phone text,"+
         "email text,"+
         "sex text)"*/

 override fun onCreate(db: SQLiteDatabase?) {
     db?.execSQL(createtbUserMsg)
 }

 override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
     onCreate(db)
 }
}
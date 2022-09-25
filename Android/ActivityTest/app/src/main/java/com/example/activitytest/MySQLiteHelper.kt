package com.example.activitytest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MySQLiteHelper (val context: Context, name:String, version:Int):
    SQLiteOpenHelper(context,name,null,version) {

    private val createMsg1="create table Msg1 (" +
            " id integer primary key autoincrement," +
            "username text," +
            "password text," +
            "sex text)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createMsg1)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }
}
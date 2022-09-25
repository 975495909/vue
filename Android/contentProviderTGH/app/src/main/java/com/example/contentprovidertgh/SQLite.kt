package com.example.contentprovidertgh

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLite (val context: Context,name:String,version:Int):
        SQLiteOpenHelper(context,name,null,version) {

    private  val createPeople="create table People (" +
            " id integer primary key autoincrement," +
            "name text," +
            "number text)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createPeople)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }

}

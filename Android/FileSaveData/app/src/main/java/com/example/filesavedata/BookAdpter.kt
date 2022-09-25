package com.example.filesavedata

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class BookAdpter(activity: Activity, val resourceId:Int, data:List<Book>):
    ArrayAdapter<Book>(activity,resourceId,data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view= LayoutInflater.from(context).inflate(resourceId,parent,false)
        val bookId: TextView =view.findViewById(R.id.bookId)
        val bookName: TextView =view.findViewById(R.id.bookName)
        val bookAuthor: TextView =view.findViewById(R.id.bookAuthor)
        val bookPage: TextView =view.findViewById(R.id.bookPage)
        val bookPrice: TextView =view.findViewById(R.id.bookPrice)
        val book=getItem(position)
        if(book!=null){
            bookId.text=book.bookId.toString()
            bookName.text=book.name
            bookAuthor.text=book.author
            bookPage.text=book.page.toString()
            bookPrice.text=book.price.toString()
        }
        return view
    }
}
package com.example.networkdatatgh

import java.io.UnsupportedEncodingException
import kotlin.Throws
import kotlin.jvm.JvmStatic

object ss {
    @Throws(UnsupportedEncodingException::class)
    @JvmStatic
    fun main(args: Array<String>) {

        //将ascii码转换成utf8
        val t = "\u661f\u671f\u56db"
        val utf8 = String(t.toByteArray(charset("UTF-8")))
        println(utf8)
        //val unicode = String(utf8.toByteArray(), "UTF-8")
        //println(unicode)
        //val gbk = String(unicode.toByteArray(charset("GBK")))
       // println(gbk)
    }

}
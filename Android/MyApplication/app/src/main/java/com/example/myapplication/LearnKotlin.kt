package com.example.myapplication

import java.util.*
import kotlin.math.max

fun main(){
    println("hello world!")
    val a: Int = 8
    var b: Int =3
   // b += 5
   // println("a="+a+" b="+b)
    val value1= largenum(a,b)
    val value2=smallnum(a,b)
    println("the max number is "+value1)
    println("the mix number is "+value2)

    val sc=Scanner(System.`in`)
    println("查询成绩，请输入姓名：")
    var name = sc.next();
    println(name+"的分数： "+getScore(name))
}


fun largenum(num1: Int, num2: Int):Int{
    return max(num1,num2)
}
//fun largenum(num1: Int, num2: Int)=max(num1,num2)

fun smallnum(num1:Int,num2:Int):Int{
    val value = if(num1 > num2){
        num2
    }else{
        num2
    }
    return value
}

fun getScore(name:String) =when (name)
{
    "陈泽芳"->99
    "刘鑫"->66
    else ->0
}
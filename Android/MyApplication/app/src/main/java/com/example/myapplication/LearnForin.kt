package com.example.myapplication

fun main(){
    for(i in 0..10) print(i)
println()
    //2的公因数
    for(i in 0 until 10 step 2) print(i)
println()
    //倒叙
    for (i in 10 downTo 0) print(i)
println()

    val p=Person()
    p.name = "刘鑫"
    p.age = 19
    p.eat()

    val s=Student()
    s.grade=5
    s.sno="19215120536"
    s.meg()

    var  list= mutableListOf("1","2","3")//可变长度的类型的集合
    list.add("666")
    for(num in list) println(num)

    var maxLengthNum=""
    for(num in list){
        for (num in list){
            if(num.length>maxLengthNum.length){
                maxLengthNum=num
            }
        }
    }
    println("max length num is "+maxLengthNum)


}
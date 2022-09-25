package com.example.myapplication


open class Person(var name: String = "", var age: Int= 0) {

    fun  eat (){
        println( "."+ age +"的"+ name +"吃着香香！")
    }

    open fun person(){
        this.name = name
        this.age = age

    }


}



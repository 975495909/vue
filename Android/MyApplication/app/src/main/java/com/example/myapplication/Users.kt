package com.example.myapplication

import java.io.Serializable

data class Users(val userName:String,val userPwd:String,
                 val email:String): Serializable{

}
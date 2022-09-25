package com.example.networkdatatgh

data class Weather (val code:String,val updateTime:String,val now:Now){
    data class Now (val obsTime:String,val text:String)
}
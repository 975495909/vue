package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val starList=ArrayList<Star>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initStars()

        //设置布局方式

        val layoutManager=LinearLayoutManager(this)

        //（列表布局方式）
        //layoutManager.orientation=LinearLayoutManager.HORIZONTAL//横向滚动
        /*创建一个LinearLayoutManager对象，设置到RecyclerView当中。
       layoutManager设置RecyclerView*/
       //recyclerView.layoutManager=layoutManager
       //val adapter=StarAdapter(starList)
       //recyclerView.adapter=adapter

       //GridLayoutManager(网格布局方式)
        gridView.layoutManager=GridLayoutManager(this,5)
        val adapter1=StarAdapter(starList)
        gridView.adapter=adapter1
        gridView.itemAnimator=DefaultItemAnimator()//设置动画

        //StaggeredGridLayoutManager（瀑布流布局方式）
        //gridView.layoutManager=StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL)
       // val adapter2=StarAdapter(starList)
        //gridView.adapter=adapter2
   }
   private fun initStars(){
       repeat(6){
           starList.add(Star("钟南山",R.drawable.s1_zhongnanshan))
           starList.add(Star("于敏",R.drawable.s2_yumin))
           starList.add(Star("申纪兰",R.drawable.s3_shenjilan))
           starList.add(Star("孙家栋",R.drawable.s4_sunjiadong))
           starList.add(Star("李延年",R.drawable.s5_liyannian))
           starList.add(Star("张富清",R.drawable.s6_zhangfuqing))
           starList.add(Star("袁隆平",R.drawable.s7_yuanlongping))
           starList.add(Star("黄旭华",R.drawable.s8_huangxuhua))
           starList.add(Star("屠呦呦",R.drawable.s9_tuyouyou))
           starList.add(Star("周兴铭",R.drawable.s10ys_zhuoxingming))
           starList.add(Star("叶培建",R.drawable.s11ys_yepeijian))
           starList.add(Star("王赤",R.drawable.s12_ys_wangchi))
           starList.add(Star("戴永久",R.drawable.s13_ys_daiyongjiu))
           starList.add(Star("刘少军",R.drawable.s14_ys_liushaojun))
       }
   }

}
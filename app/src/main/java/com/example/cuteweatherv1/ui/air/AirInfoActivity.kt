package com.example.cuteweatherv1.ui.air

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.repository.air.AirSectionsAdapter
import com.example.cuteweatherv1.repository.air.DealAriInfoJson
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_air_info.*
import java.text.FieldPosition

/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：空气质量切换界面的activity函数
 */
class AirInfoActivity : AppCompatActivity() {
    //设置默认城市
    var city = "beijing"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_air_info)
        val sectionsPagerAdapter = AirSectionsAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)

        getApi()
        //设置viewPager的监听函数
        viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            //如果页面被选择，则调用此函数，进行api的调用
            override fun onPageSelected(position: Int) {
                val dealAriInfoJson = DealAriInfoJson()
                dealAriInfoJson.deal(position,city)
            }
        })

        //设置界面的adapter和标题
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        //点击返回按钮的处理
        backiv.setOnClickListener {
            finish()
        }
    }

    //对定位地址进行处理，同时对api接口进行调用
    fun getApi(){
        MyLocation.instance.city.observe(this, object : Observer<String> {
            override fun onChanged(t: String) {
                city = t
                val dealAriInfoJson = DealAriInfoJson()
                dealAriInfoJson.deal(0,city)
            }
        })
    }
}

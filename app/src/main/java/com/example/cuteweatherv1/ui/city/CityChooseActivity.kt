package com.example.cuteweatherv1.ui.city

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.cuteweatherv1.R
import com.zaaach.citypicker.CityPicker
import com.zaaach.citypicker.adapter.OnPickListener
import com.zaaach.citypicker.model.City
import com.zaaach.citypicker.model.HotCity
import com.zaaach.citypicker.model.LocatedCity

class CityChooseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_choose)
        val hotCities = ArrayList<HotCity>()
        hotCities.add(HotCity("北京", "北京", "101010100"))
        hotCities.add(HotCity("上海", "上海", "101020100"))
        hotCities.add(HotCity("广州", "广东", "101280101"))
        hotCities.add(HotCity("深圳", "广东", "101280601"))
        hotCities.add(HotCity("杭州", "浙江", "101210101"))

        CityPicker.from(this) //activity或者fragment
            .enableAnimation(true)	//启用动画效果，默认无
            .setAnimationStyle(0)	//自定义动画
            .setLocatedCity(LocatedCity("杭州", "浙江", "101210101"))  //APP自身已定位的城市，传null会自动定位（默认）
            .setHotCities(hotCities)	//指定热门城市
            .setOnPickListener(object : OnPickListener {
                override fun onPick(position: Int, data: City){
                    Toast.makeText(getApplicationContext(), data.getName(), Toast.LENGTH_SHORT).show()
                }

                override fun onCancel() {
                    //取消选择
                    finish()
                }

                override fun onLocate() {
                    //定位接口，需要APP自身实现，这里模拟一下定位
                    Handler().postDelayed(Runnable{
                        fun run() {
                            //定位完成之后更新数据
                        }
                    }, 3000)

                }
            }).show()
    }
}

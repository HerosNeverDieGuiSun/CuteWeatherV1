package com.example.cuteweatherv1.ui.city

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.widget.Toast
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.repository.city.CityRepository
import com.example.cuteweatherv1.repository.city.DealCityInfo
import com.example.cuteweatherv1.repository.city.data.CityInfo
import com.example.cuteweatherv1.ui.MainActivity
import com.zaaach.citypicker.CityPicker
import com.zaaach.citypicker.adapter.OnPickListener
import com.zaaach.citypicker.model.City
import com.zaaach.citypicker.model.HotCity
import com.zaaach.citypicker.model.LocateState
import com.zaaach.citypicker.model.LocatedCity

/**
 * Created by 胡婵娟.
 * 内容：城市选择页面处理
 */
class CityChooseActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        setContentView(R.layout.activity_city_choose)
        val hotCities = ArrayList<HotCity>()
        hotCities.add(HotCity("北京", "北京", "101010100"))
        hotCities.add(HotCity("上海", "上海", "101020100"))
        hotCities.add(HotCity("广州", "广东", "101280101"))
        hotCities.add(HotCity("深圳", "广东", "101280601"))
        hotCities.add(HotCity("杭州", "浙江", "101210101"))
        hotCities.add(HotCity("南京", "江苏", "101210101"))
        hotCities.add(HotCity("苏州", "江苏", "101210101"))
        hotCities.add(HotCity("天津", "天津", "101210101"))
        hotCities.add(HotCity("合肥", "安徽", "101210101"))

        CityPicker.from(this) //activity或者fragment
            .enableAnimation(true)	//启用动画效果，默认无
            .setAnimationStyle(0)	//自定义动画
            .setLocatedCity(LocatedCity(MyLocation.instance.city.value!!, "", ""))  //APP自身已定位的城市，传null会自动定位（默认）
            .setHotCities(hotCities)	//指定热门城市
            .setOnPickListener(object : OnPickListener {
                override fun onPick(position: Int, data: City){
                    if (!DealCityInfo().isAddRepeated(data.name)) {
                        CityRepository.instance.data.add(
                            CityInfo(data.name, "", "", "", "", "", "", "")
                        )
                        val edit = sharedPreferences.edit()
                        var content = sharedPreferences.getString("city", "")
                        if (content != "") {
                            content += ","
                        }
                        content += data.name
                        edit.putString("city", content).commit()
                        finish()
                    } else {
                        finish()
                        Toast.makeText(applicationContext, "不能重复添加同一城市哦", Toast.LENGTH_SHORT).show()
                    }
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

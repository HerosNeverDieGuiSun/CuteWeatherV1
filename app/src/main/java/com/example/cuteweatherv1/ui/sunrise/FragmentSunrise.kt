package com.example.cuteweatherv1.ui.sunrise


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.repository.sunrise.SunriseJson
import com.example.cuteweatherv1.repository.sunrise.data.SaveSunrise
import com.example.cuteweatherv1.ui.sunrise.library.SetSunrise


import com.example.cuteweatherv1.ui.sunrise.library.SunriseView
import kotlinx.android.synthetic.main.fragment_sunrise.*
import java.util.*


/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：日出日落Fragment
 */
class FragmentSunrise : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sunrise, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val sunriseJson = SunriseJson()
        //设置位置信息
        MyLocation.instance.city.observe(this, object : Observer<String> {
            override fun onChanged(t: String) {
                sunriseJson.deal(t)
            }
        })

        SaveSunrise.instance.sun.observe(this,object : Observer<ArrayList<String>> {
            override fun onChanged(t: ArrayList<String>?) {
                Tsunrise.text = SaveSunrise.instance.sun.value!!.get(0)
                Tsunset.text = SaveSunrise.instance.sun.value!!.get(1)
                var sunriseHour = SaveSunrise.instance.sun.value!!.get(0).substring(0,2)
                var sunsetHour = SaveSunrise.instance.sun.value!!.get(1).substring(0,2)
                var sunriseMin = SaveSunrise.instance.sun.value!!.get(0).substring(3,5)
                var sunsetMin = SaveSunrise.instance.sun.value!!.get(1).substring(3,5)
                riseAndSetDeal(sunriseHour,sunsetHour,sunriseMin,sunsetMin)
            }

        })
    }

    //日出日落时间处理函数
    fun riseAndSetDeal(sunriseHour:String,sunsetHour:String,sunriseMin:String,sunsetMin:String){
        val textSunrise = SetSunrise()
        //判断日出和日落的分钟是否大于30，一共四种情况
        if (sunriseMin.toInt()> 30){
            if (sunsetMin.toInt()>30){
                textSunrise.deal(sun,sunriseHour.toInt()+1,sunsetHour.toInt()+1)
            } else {
                textSunrise.deal(sun,sunriseHour.toInt()+1,sunsetHour.toInt())
            }
        } else {
            if (sunsetMin.toInt()>30){
                textSunrise.deal(sun,sunriseHour.toInt(),sunsetHour.toInt()+1)
            } else {
                textSunrise.deal(sun,sunriseHour.toInt(),sunsetHour.toInt())
            }
        }
    }


}

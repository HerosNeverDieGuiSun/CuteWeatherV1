package com.example.cuteweatherv1.ui.briefly

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.repository.daily.DailyOperate
import com.example.cuteweatherv1.repository.hourly.HourlyOperate
import com.example.cuteweatherv1.ui.briefly.data.Result
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlin.jvm.internal.Ref

/**
 * 时间：2019/7/4 11:14
 * 作者：程弋
 * 描述: 天气简略信息
 */

class FragmentWeather : Fragment() {

    companion object {
        fun newInstance() = FragmentWeather()
    }

    private val repository = Reposition.instance
    private lateinit var viewModel: FragmentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentWeatherViewModel::class.java)

        MyLocation.instance.city.observe(this, Observer<String> { t ->
            //使用新数据更新界面
            viewModel.getBriefInfo()
            //DailyOperate.instance.getDailyData()
            //HourlyOperate.instance.getHourlyInfo()
        })

        DailyOperate.instance.dailyInfo.observe(this, Observer<List<com.example.cuteweatherv1.repository.daily.data.Result> > {
            var dayInfo = it?.get(0)?.daily?.get(0)
            high_temp.text = dayInfo?.high + "°"
            low_temp.text = dayInfo?.low + "°"
        })

        MyLocation.instance.briefInfo.observe(this, Observer<Result> {
            city.text = it?.location?.name
            date_today.text = it?.lastUpdate?.substring(0, 10)
            temp_now.text = it?.now?.temperature + "°"
            feel_like.text = it?.now?.feelsLike + "°"
            weather.text = it?.now?.text
            humidity.text = it?.now?.humidity + "%"
            wind_direction.text = it?.now?.windDirection
            wind_speed.text = it?.now?.windSpeed + " km/h"

            when (it?.now?.text) {
                "晴" -> weatherIcon.setImageResource(R.drawable.weather_sunny_big)
                "多云", "晴间多云" -> weatherIcon.setImageResource(R.drawable.weather_cloudy_big)
                "阴" -> weatherIcon.setImageResource(R.drawable.weather_overcast_big)
                "阵雨" -> weatherIcon.setImageResource(R.drawable.weather_rain_shower_big)
                "雷阵雨" -> weatherIcon.setImageResource(R.drawable.weather_thundershower_big)
                "小雨" -> weatherIcon.setImageResource(R.drawable.weather_rain_light_big)
                "中雨" -> weatherIcon.setImageResource(R.drawable.weather_rain_midle_big)
                "大雨" -> weatherIcon.setImageResource(R.drawable.weather_rain_heavy_big)
                "暴雨", "大暴雨" -> weatherIcon.setImageResource(R.drawable.weather_rain_storm_big)
                "雨夹雪" -> weatherIcon.setImageResource(R.drawable.weather_rain_snow_big)
                "小雪" -> weatherIcon.setImageResource(R.drawable.weather_snow_light_big)
                "中雪" -> weatherIcon.setImageResource(R.drawable.weather_snow_midle_big)
                "大雪" -> weatherIcon.setImageResource(R.drawable.weather_snow_heavy_big)
                "暴雪" -> weatherIcon.setImageResource(R.drawable.weather_snow_storm_big)
                "沙尘暴", "浮尘", "扬沙", "强沙尘暴" -> weatherIcon.setImageResource(R.drawable.weather_sand_storm_big)
                "雾" -> weatherIcon.setImageResource(R.drawable.weather_fog_big)
                "霾" -> weatherIcon.setImageResource(R.drawable.weather_fog_haze_big)
                "龙卷风" -> weatherIcon.setImageResource(R.drawable.weather_tornado_big)
                else -> weatherIcon.setImageResource(R.drawable.weather_no_result_big)
            }
        })
    }
}

package com.example.cuteweatherv1.ui.hourly

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.adapter.hourly.HourlyAdapter
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.repository.hourly.Hourly
import com.example.cuteweatherv1.repository.hourly.HourlyOperate
import com.example.cuteweatherv1.repository.hourly.data.Result
import kotlinx.android.synthetic.main.fragment_hourly.*

class FragmentHourly : Fragment() {

    companion object {
        fun newInstance() = FragmentHourly()
    }

    private lateinit var adapter: HourlyAdapter
    private lateinit var viewModel: FragmentHourlyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hourly, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentHourlyViewModel::class.java)

        MyLocation.instance.city.observe(this, Observer<String> {
            viewModel.getHourlyInfo()
        })

        HourlyOperate.instance.hourlyInfo.observe(this, Observer<List<Result> > {
            val res = it?.get(0)?.hourly
            val data = ArrayList<Hourly>()

            for (r in res!!) {
                val time = r.time.substring(11, 16)
                data.add(Hourly(time, setImage(r.text), r.temperature + "°C"))
            }
            val first = res[0]
            data.set(0, Hourly("现在", setImage(first.text), first.temperature + "°C"))

            adapter = HourlyAdapter(activity!!.applicationContext, R.layout.hourly_item, data)

            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.HORIZONTAL, false)
        })
    }

    private fun setImage(daily : String?): Int {
        when (daily) {
            "晴" -> return R.drawable.weather_sunny_grey
            "多云" -> return R.drawable.weather_cloudy_grey
            "阴" -> return R.drawable.weather_overcast_grey
            "阵雨" -> return R.drawable.weather_rain_shower_grey
            "雷阵雨" -> return R.drawable.weather_thundershower_grey
            "小雨" -> return R.drawable.weather_rain_light_grey
            "中雨" -> return R.drawable.weather_rain_midle_grey
            "大雨" -> return R.drawable.weather_rain_heavy_grey
            "暴雨", "大暴雨" -> return R.drawable.weather_rain_storm_grey
            "雨夹雪" -> return R.drawable.weather_rain_snow_grey
            "小雪" -> return R.drawable.weather_snow_light_grey
            "中雪" -> return R.drawable.weather_snow_midle_grey
            "大雪" -> return R.drawable.weather_snow_heavy_grey
            "暴雪" -> return R.drawable.weather_snow_storm_grey
            "沙尘暴", "浮尘", "扬沙", "强沙尘暴" -> return R.drawable.weather_sand_storm_grey
            "雾" -> return R.drawable.weather_fog_grey
            "霾" -> return R.drawable.weather_fog_haze_grey
            "龙卷风" -> return R.drawable.weather_tornado_grey
            else -> return R.drawable.weather_no_result_grey
        }
    }
}

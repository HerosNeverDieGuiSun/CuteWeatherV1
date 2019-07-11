package com.example.cuteweatherv1.ui.daily

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.repository.daily.DailyOperate
import com.example.cuteweatherv1.repository.daily.data.Result
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.fragment_daily.*
import kotlinx.android.synthetic.main.fragment_daily.date_today
import java.text.SimpleDateFormat


class FragmentDaily : Fragment() {

    companion object {
        fun newInstance() = FragmentDaily()
    }

    private lateinit var lineChart : LineChart

    private lateinit var viewModel: FragmentDailyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daily, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentDailyViewModel::class.java)
        lineChart = chart

        MyLocation.instance.city.observe(this, Observer<String> {
            viewModel.getDailyInfo()
        })

        DailyOperate.instance.dailyInfo.observe(this, Observer<List<Result> > {

            val dayInfo1 = it?.get(0)?.daily?.get(0)
            val dayInfo2 = it?.get(0)?.daily?.get(1)
            val dayInfo3 = it?.get(0)?.daily?.get(2)
            val dayInfo4 = it?.get(0)?.daily?.get(3)
            val dayInfo5 = it?.get(0)?.daily?.get(4)

            // 星期几
            val format = SimpleDateFormat("yyyy-MM-dd")
            val date1 = DailyOperate.instance.parseDateToWeek(format.parse(dayInfo3?.date))
            val date2 = DailyOperate.instance.parseDateToWeek(format.parse(dayInfo4?.date))
            val date3 = DailyOperate.instance.parseDateToWeek(format.parse(dayInfo5?.date))
            day1.text = date1
            day2.text = date2
            day3.text = date3

            // 日期
            date_today.text = dayInfo1?.date?.substring(5, 10)
            date_tomorrow.text = dayInfo2?.date?.substring(5, 10)
            date_day1.text = dayInfo3?.date?.substring(5, 10)
            date_day2.text = dayInfo4?.date?.substring(5, 10)
            date_day3.text = dayInfo5?.date?.substring(5, 10)

            // 天气图标
            setImage(dayInfo1?.textDay, image_today)
            setImage(dayInfo2?.textDay, image_tomorrow)
            setImage(dayInfo3?.textDay, image_day1)
            setImage(dayInfo4?.textDay, image_day2)
            setImage(dayInfo5?.textDay, image_day3)

            // 天气名称
            weather_today.text = dayInfo1?.textDay
            weather_tomorrow.text = dayInfo2?.textDay
            weather_day1.text = dayInfo3?.textDay
            weather_day2.text = dayInfo4?.textDay
            weather_day3.text = dayInfo5?.textDay

            // 画图
            val entryHigh = ArrayList<Entry>()
            val entryLow = ArrayList<Entry>()

            entryHigh.add(Entry(0f, dayInfo1?.high?.toFloat()!!))
            entryHigh.add(Entry(1f, dayInfo2?.high?.toFloat()!!))
            entryHigh.add(Entry(2f, dayInfo3?.high?.toFloat()!!))
            entryHigh.add(Entry(3f, dayInfo4?.high?.toFloat()!!))
            entryHigh.add(Entry(4f, dayInfo5?.high?.toFloat()!!))

            entryLow.add(Entry(0f, dayInfo1?.low?.toFloat()!!))
            entryLow.add(Entry(1f, dayInfo2?.low?.toFloat()!!))
            entryLow.add(Entry(2f, dayInfo3?.low?.toFloat()!!))
            entryLow.add(Entry(3f, dayInfo4?.low?.toFloat()!!))
            entryLow.add(Entry(4f, dayInfo5?.low?.toFloat()!!))

            val dataSetLow = LineDataSet(entryLow, "低温")
            val dataSetHigh = LineDataSet(entryHigh, "高温")
            dataSetLow.color = resources.getColor(R.color.colorPrimary)
            dataSetLow.valueTextColor = resources.getColor(R.color.textColor)
            dataSetLow.valueTextSize = 14F

            dataSetHigh.color = resources.getColor(R.color.colorAccent)
            dataSetHigh.valueTextColor = resources.getColor(R.color.textColor)
            dataSetHigh.valueTextSize = 14F

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(dataSetLow)
            dataSets.add(dataSetHigh)

            val lineData = LineData(dataSets)
            lineChart.data = lineData

            lineChart.setTouchEnabled(false)
            lineChart.description.isEnabled = false
            lineChart.xAxis.isEnabled = false
            lineChart.axisLeft.isEnabled = false
            lineChart.axisRight.isEnabled = false
            //lineChart.animateX(1500)
            lineChart.invalidate()
        })
    }

    private fun setImage(daily : String?, id : ImageView) {
        when (daily) {
            "晴" -> id.setImageResource(R.drawable.weather_sunny_grey)
            "多云" -> id.setImageResource(R.drawable.weather_cloudy_grey)
            "阴" -> id.setImageResource(R.drawable.weather_overcast_grey)
            "阵雨" -> id.setImageResource(R.drawable.weather_rain_shower_grey)
            "雷阵雨" -> id.setImageResource(R.drawable.weather_thundershower_grey)
            "小雨" -> id.setImageResource(R.drawable.weather_rain_light_grey)
            "中雨" -> id.setImageResource(R.drawable.weather_rain_midle_grey)
            "大雨" -> id.setImageResource(R.drawable.weather_rain_heavy_grey)
            "暴雨", "大暴雨" -> id.setImageResource(R.drawable.weather_rain_storm_grey)
            "雨夹雪" -> id.setImageResource(R.drawable.weather_rain_snow_grey)
            "小雪" -> id.setImageResource(R.drawable.weather_snow_light_grey)
            "中雪" -> id.setImageResource(R.drawable.weather_snow_midle_grey)
            "大雪" -> id.setImageResource(R.drawable.weather_snow_heavy_grey)
            "暴雪" -> id.setImageResource(R.drawable.weather_snow_storm_grey)
            "沙尘暴", "浮尘", "扬沙", "强沙尘暴" -> id.setImageResource(R.drawable.weather_sand_storm_grey)
            "雾" -> id.setImageResource(R.drawable.weather_fog_grey)
            "霾" -> id.setImageResource(R.drawable.weather_fog_haze_grey)
            "龙卷风" -> id.setImageResource(R.drawable.weather_tornado_grey)
            else -> id.setImageResource(R.drawable.weather_no_result_grey)
        }
    }
}

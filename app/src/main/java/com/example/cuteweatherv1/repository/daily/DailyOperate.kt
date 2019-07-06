package com.example.cuteweatherv1.repository.daily

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.repository.daily.data.DailyInfo
import com.example.cuteweatherv1.repository.daily.data.Result
import com.example.cuteweatherv1.ui.daily.DailyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * 时间：2019/7/5 11:22
 * 作者：程弋
 * 描述: 逐日天气单例
 */
class DailyOperate {
    private constructor()

    object Holder {
        val INSTANCE = DailyOperate()
    }
    companion object {
        val instance = Holder.INSTANCE
    }

    val dailyInfo = MutableLiveData<List<Result> >()

    fun getDailyData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Reposition.BASEURL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(DailyService::class.java)
        service.getInfo(Reposition.KEY, MyLocation.instance.city.value.toString(), 0, 5)?.enqueue(
            object : Callback<DailyInfo> {
                override fun onResponse(call: Call<DailyInfo>, response: Response<DailyInfo>) {
                    dailyInfo.value = response.body()?.results
                }

                override fun onFailure(call: Call<DailyInfo>, t: Throwable) {
                    Log.e("mylog", t.message)
                }
            }
        )
    }

    fun parseDateToWeek(date: Date): String {

        //获取默认选中的日期的年月日星期的值，并赋值
        val calendar = Calendar.getInstance()//日历对象
        calendar.time = date//设置当前日期

        val yearStr = calendar.get(Calendar.YEAR).toString() + ""//获取年份
        val month = calendar.get(Calendar.MONTH) + 1//获取月份
        val monthStr = if (month < 10) "0$month" else month.toString() + ""
        val day = calendar.get(Calendar.DATE)//获取日
        val dayStr = if (day < 10) "0$day" else day.toString() + ""
        val week = calendar.get(Calendar.DAY_OF_WEEK)
        var weekStr = ""
        when (week) {
            1 -> weekStr = "周日"
            2 -> weekStr = "周一"
            3 -> weekStr = "周二"
            4 -> weekStr = "周三"
            5 -> weekStr = "周四"
            6 -> weekStr = "周五"
            7 -> weekStr = "周六"
            else -> {
            }
        }
        return weekStr
    }
}
package com.example.cuteweatherv1.repository.hourly

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.repository.hourly.data.HourlyData
import com.example.cuteweatherv1.repository.hourly.data.Result
import com.example.cuteweatherv1.ui.hourly.HourlyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 时间：2019/7/6 10:05
 * 作者：程弋
 * 描述: 每小时天气获取数据单例
 */
class HourlyOperate {
    private constructor()

    object Holder {
        val INSTANCE = HourlyOperate()
    }
    companion object {
        val instance = Holder.INSTANCE
    }

    val hourlyInfo = MutableLiveData<List<Result> >()

    fun getHourlyInfo() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Reposition.BASEURL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(HourlyService::class.java)
        service.getInfo(Reposition.KEY, MyLocation.instance.city.value.toString(), 0, 24)?.
            enqueue(object : Callback<HourlyData> {
                override fun onResponse(call: Call<HourlyData>, response: Response<HourlyData>) {
                    hourlyInfo.value = response.body()?.results
                }

                override fun onFailure(call: Call<HourlyData>, t: Throwable) {
                    Log.e("mylog", t.message)
                }
            })


    }

}
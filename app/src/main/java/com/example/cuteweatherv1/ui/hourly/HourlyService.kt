package com.example.cuteweatherv1.ui.hourly

import retrofit2.Call
import com.example.cuteweatherv1.repository.hourly.data.HourlyData
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 时间：2019/7/6 09:53
 * 作者：程弋
 * 描述: 每小时天气请求数据接口
 */
interface HourlyService {
    @GET("v3/weather/hourly.json")
    fun getInfo(@Query("key") key : String, @Query("location") location : String,
                @Query("start") start : Int, @Query("hours") hours : Int): Call<HourlyData>
}
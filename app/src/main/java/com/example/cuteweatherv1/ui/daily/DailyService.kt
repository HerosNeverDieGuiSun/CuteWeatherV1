package com.example.cuteweatherv1.ui.daily

import com.example.cuteweatherv1.repository.daily.data.DailyInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 时间：2019/7/5 11:25
 * 作者：程弋
 * 描述: 逐日天气信息接口
 */
interface DailyService {
    @GET("v3/weather/daily.json")
    fun getInfo(@Query("key") key : String, @Query("location") location : String,
                @Query("start") start : Int, @Query("days") days : Int): Call<DailyInfo>
}
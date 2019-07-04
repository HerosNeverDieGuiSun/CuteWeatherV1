package com.example.cuteweatherv1.ui.briefly

import com.example.cuteweatherv1.ui.briefly.data.BriefInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 时间：2019/7/4 11:27
 * 作者：程弋
 * 描述: 获取天气简略信息的接口
 */
interface BriefService {
    @GET("v3/weather/now.json")
    fun getInfo(@Query("key") key : String, @Query("location") location : String): Call<BriefInfo>
}
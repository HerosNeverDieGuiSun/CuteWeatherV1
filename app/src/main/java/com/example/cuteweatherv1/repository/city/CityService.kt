package com.example.cuteweatherv1.repository.city

import com.example.cuteweatherv1.repository.city.data.City
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by 胡婵娟.
 * 内容：获取天气信息接口
 */
interface CityService {
    @GET("v3/weather/now.json")
    fun getInfo(@Query("key") key : String, @Query("location") location : String): Call<City>
}
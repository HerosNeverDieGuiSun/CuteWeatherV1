package com.example.cuteweatherv1.location

import com.example.cuteweatherv1.location.data.LocationData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 时间：2019/7/4 18:43
 * 作者：程弋
 * 描述: 获取定位数据接口
 */
interface LocationService {
    @GET("geo/")
    fun getInfo(@Query("key") key : String,
                @Query("lat") latitude : String,
                @Query("lng") longitude : String,
                @Query("type") type : Int): Call<LocationData>
}
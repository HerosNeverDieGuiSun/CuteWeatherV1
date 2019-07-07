package com.example.cuteweatherv1.repository.air

import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.repository.air.data.AirData.AirData
import com.example.cuteweatherv1.repository.air.data.AirInfoData
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：每日空气质量和实时空气质量api接口函数
 */
interface AirInfoServ {
    @GET(Reposition.AIRDAILY)
    fun getInfo(@Query("key") key:String, @Query("location") location: String): Call<AirInfoData>

    @GET(Reposition.AIR)
    fun getAirOnTime(@Query("key") key:String, @Query("location") location: String):Call<AirData>

}
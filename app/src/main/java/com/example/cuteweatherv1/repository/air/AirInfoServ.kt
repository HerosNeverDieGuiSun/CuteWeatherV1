package com.example.cuteweatherv1.repository.air

import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.repository.air.data.AirData.AirData
import com.example.cuteweatherv1.repository.air.data.AirInfoData
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface AirInfoServ {
    @GET(Reposition.AIRDAILY)
    fun getInfo(@Query("key") key:String, @Query("location") location: String): Call<AirInfoData>

    @GET(Reposition.Air)
    fun getAirOnTime(@Query("key") key:String, @Query("location") location: String):Call<AirData>

}
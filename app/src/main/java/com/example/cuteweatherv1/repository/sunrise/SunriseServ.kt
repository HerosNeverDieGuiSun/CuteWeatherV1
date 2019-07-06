package com.example.cuteweatherv1.repository.sunrise

import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.repository.sunrise.data.SunriseData
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface SunriseServ {
    @GET(Reposition.SUNRISE)
    fun getinfo(@Query("key") key:String, @Query("location") location: String):Call<SunriseData>
}
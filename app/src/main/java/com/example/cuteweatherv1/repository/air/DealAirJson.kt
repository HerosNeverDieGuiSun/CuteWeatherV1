package com.example.cuteweatherv1.repository.air

import android.util.Log
import androidx.lifecycle.Observer
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.repository.air.data.AirData.AirData
import com.example.cuteweatherv1.repository.air.data.AirData.SaveAir
import com.example.cuteweatherv1.repository.air.data.AirInfoData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class DealAirJson {
    fun deal(position:String){
        var temp: String = "beijing"
        if (position != null){
            temp = position
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(Reposition.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(AirInfoServ::class.java)
        Log.e("my","$temp")
        service.getAirOnTime(Reposition.KEY,temp)
            .enqueue(object : Callback<AirData> {
                override fun onFailure(call: Call<AirData>, t: Throwable) {
                    Log.e("my","获取数据失败")
                }

                override fun onResponse(call: Call<AirData>, response: Response<AirData>) {
                    SaveAir.instance.airNum.value = response.body()?.results?.get(0)?.air!!.city.aqi.toInt()
                    SaveAir.instance.pm25.value = response.body()?.results?.get(0)?.air!!.city.pm25
                    SaveAir.instance.pm10.value = response.body()?.results?.get(0)?.air!!.city.pm10
                    SaveAir.instance.time.value = response.body()?.results?.get(0)?.air!!.city.lastUpdate

                }

            })
    }
}
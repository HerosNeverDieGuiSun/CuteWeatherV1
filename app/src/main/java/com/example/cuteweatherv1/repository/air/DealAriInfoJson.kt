package com.example.cuteweatherv1.repository.air

import android.util.Log
import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.repository.air.data.AirInfoData
import com.example.cuteweatherv1.repository.air.data.Daily
import com.example.cuteweatherv1.repository.air.data.SaveAirDaily
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.FieldPosition

/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：每日空气质量api处理函数
 */
class DealAriInfoJson {
    fun deal(position: Int,city:String){
        val retrofit = Retrofit.Builder()
            .baseUrl(Reposition.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(AirInfoServ::class.java)
        service.getInfo(Reposition.KEY,city)
            .enqueue(object : Callback<AirInfoData> {
                override fun onFailure(call: Call<AirInfoData>, t: Throwable) {
                    Log.e("my","获取数据失败")
                }
                override fun onResponse(call: Call<AirInfoData>, response: Response<AirInfoData>) {
                    var list = ArrayList<String>()
                    list.add(response.body()?.results?.get(0)?.daily?.get(position)!!.aqi)
                    list.add(response.body()?.results?.get(0)?.daily?.get(position)!!.so2)
                    list.add(response.body()?.results?.get(0)?.daily?.get(position)!!.co)
                    list.add(response.body()?.results?.get(0)?.daily?.get(position)!!.no2)
                    list.add(response.body()?.results?.get(0)?.daily?.get(position)!!.o3)
                    list.add(response.body()?.results?.get(0)?.daily?.get(position)!!.pm10)
                    list.add(response.body()?.results?.get(0)?.daily?.get(position)!!.pm25)
                    SaveAirDaily.instance.info.value = list
            }

            })
    }
}
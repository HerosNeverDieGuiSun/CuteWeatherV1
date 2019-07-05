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

class DealAriInfoJson {
    fun deal(position: Int,city:String){
        val retrofit = Retrofit.Builder()
            .baseUrl(Reposition.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(AirInfoServ::class.java)
        Log.e("my","city = "+city)
        service.getInfo(Reposition.KEY,city)
            .enqueue(object : Callback<AirInfoData> {
                override fun onFailure(call: Call<AirInfoData>, t: Throwable) {
                    Log.e("my","获取数据失败")
                }
                override fun onResponse(call: Call<AirInfoData>, response: Response<AirInfoData>) {
                    val temp = response.body()?.results?.get(0)?.daily?.get(position)!!.aqi
                    SaveAirDaily.instance.airNum.value = temp.toInt()
                    SaveAirDaily.instance.no2.value = response.body()?.results?.get(0)?.daily?.get(position)!!.no2
                    SaveAirDaily.instance.co.value = response.body()?.results?.get(0)?.daily?.get(position)!!.co
                    SaveAirDaily.instance.so2.value = response.body()?.results?.get(0)?.daily?.get(position)!!.so2
                    SaveAirDaily.instance.pm25.value = response.body()?.results?.get(0)?.daily?.get(position)!!.pm25
                    SaveAirDaily.instance.pm10.value = response.body()?.results?.get(0)?.daily?.get(position)!!.pm10
                    SaveAirDaily.instance.o3.value = response.body()?.results?.get(0)?.daily?.get(position)!!.o3
//                    for (i in 0 until 5){
//                        var aqi = response.body()?.results?.get(0)?.daily?.get(i)!!.aqi
//                        var co = response.body()?.results?.get(0)?.daily?.get(i)!!.co
//                        var time = response.body()?.results?.get(0)?.daily?.get(i)!!.date
//                        var no2 = response.body()?.results?.get(0)?.daily?.get(i)!!.no2
//                        var o3 = response.body()?.results?.get(0)?.daily?.get(i)!!.o3
//                        var pm10 = response.body()?.results?.get(0)?.daily?.get(i)!!.pm10
//                        var pm25 = response.body()?.results?.get(0)?.daily?.get(i)!!.pm25
//                        var quality = response.body()?.results?.get(0)?.daily?.get(i)!!.quality
//                        var so2 = response.body()?.results?.get(0)?.daily?.get(i)!!.so2
//                        val tempdaily = Daily(aqi,co,time,no2,o3,pm10,pm25,quality,so2)
//                        if (SaveAirDaily.instance.dailyList.size<5){
//                            Log.e("MyLog","---------------$aqi")
//                            SaveAirDaily.instance._index.value = aqi.toInt()
//
//                        }
//                    }

                }

            })
//        var l = SaveAirDaily.instance.dailyList[1].aqi
//        Log.e("my","---"+l)
    }
}
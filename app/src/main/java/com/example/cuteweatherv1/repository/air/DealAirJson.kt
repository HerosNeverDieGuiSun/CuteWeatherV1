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
import kotlin.collections.ArrayList
/**
*创建者：zzd
*时间：2019/7/6
*功能：空气质量Card中API具体处理函数
*/
class DealAirJson {
    fun deal(position:String){
        //设置默认城市
        var temp: String = "beijing"
        if (position != "nul"){
            temp = position
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(Reposition.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(AirInfoServ::class.java)
        service.getAirOnTime(Reposition.KEY,temp)
            .enqueue(object : Callback<AirData> {
                override fun onFailure(call: Call<AirData>, t: Throwable) {
                    Log.e("my","获取数据失败")
                }

                override fun onResponse(call: Call<AirData>, response: Response<AirData>) {
                    //保存文本信息至单例函数中
                    var list = ArrayList<String>()
                    list.add(response.body()?.results?.get(0)?.air!!.city.aqi)
                    list.add(response.body()?.results?.get(0)?.air!!.city.pm10)
                    list.add(response.body()?.results?.get(0)?.air!!.city.pm25)
                    list.add(response.body()?.results?.get(0)?.air!!.city.lastUpdate)
                    SaveAir.instance.airInfo.value = list
                }

            })
    }
}
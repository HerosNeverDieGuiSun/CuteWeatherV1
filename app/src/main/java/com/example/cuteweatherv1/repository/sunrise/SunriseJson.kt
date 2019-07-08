package com.example.cuteweatherv1.repository.sunrise

import android.util.Log
import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.repository.sunrise.data.SaveSunrise
import com.example.cuteweatherv1.repository.sunrise.data.SunriseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：日出日落api的Json处理函数
 */
class SunriseJson {
    fun deal(position:String){
        //设置地理位置
        var temp: String = "beijing"
        if (position != "nul"){
            temp = position
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(Reposition.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(SunriseServ::class.java)
        service.getinfo(Reposition.KEY,temp)
            .enqueue(object : Callback<SunriseData> {
                override fun onFailure(call: Call<SunriseData>, t: Throwable) {
                    Log.e("my","获取数据失败")
                }

                override fun onResponse(call: Call<SunriseData>, response: Response<SunriseData>) {
                    var array = ArrayList<String>()
                    array.add(response.body()?.results?.get(0)?.sun!!.get(0).sunrise)
                    array.add(response.body()?.results?.get(0)?.sun!!.get(0).sunset)
                    SaveSunrise.instance.sun.value = array
                }

            })
    }
}



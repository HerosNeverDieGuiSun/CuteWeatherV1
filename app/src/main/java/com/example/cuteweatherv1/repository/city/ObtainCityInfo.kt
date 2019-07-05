package com.example.cuteweatherv1.repository.city

import android.util.Log
import com.example.cuteweatherv1.repository.city.data.City
import com.example.cuteweatherv1.repository.city.data.CityInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by 胡婵娟.
 * 内容：处理天气信息
 */
class ObtainCityInfo {
    var cityInfo = CityInfo("", "11111", "", "0", "", "", "", "")

    fun getInfo(cityName: String) {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.seniverse.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CityService::class.java)


        service.getInfo("SAc5cXnjG7dhZBOf_", cityName)
            .enqueue(object : Callback<City> {
                override fun onFailure(call: Call<City>, t: Throwable) {
                    Log.e("MyLog", "获取数据失败")
                }

                override fun onResponse(call: Call<City>, response: Response<City>) {
                    Log.e("MyLog", "获取数据中......")

                    Log.e("开始", cityInfo.path.toString())
                    Log.e("开始", cityInfo.temperature.toString())
                    cityInfo = CityInfo(
                        cityName,
                        response.body()?.results?.get(0)?.location?.path.toString(),
                        response.body()?.results?.get(0)?.now?.text.toString(),
                        response.body()?.results?.get(0)?.now?.temperature.toString(),
                        response.body()?.results?.get(0)?.now?.humidity.toString(),
                        response.body()?.results?.get(0)?.now?.wind_direction.toString(),
                        response.body()?.results?.get(0)?.now?.wind_scale.toString(),
                        response.body()?.results?.get(0)?.now?.feels_like.toString()
                        )
//                    Log.e("结束", cityInfo.path.toString())
//                    Log.e("结束", cityInfo.temperature.toString())
                }
            })

        Thread.sleep(300)
        Log.e("结束", cityInfo.path.toString())
        Log.e("结束", cityInfo.temperature.toString())
    }
}
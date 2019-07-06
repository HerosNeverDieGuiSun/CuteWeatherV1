package com.example.cuteweatherv1.location

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cuteweatherv1.location.data.LocationData
import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.ui.briefly.BriefService
import com.example.cuteweatherv1.ui.briefly.data.BriefInfo
import com.example.cuteweatherv1.ui.briefly.data.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 时间：2019/7/4 15:36
 * 作者：程弋
 * 描述: 获取定位服务
 */
class MyLocation {
    private constructor()

    object Holder {
        val INSTANCE = MyLocation()
    }
    companion object {
        val instance: MyLocation = Holder.INSTANCE
    }

    var latitude = ""
    var longitude = ""
    var defaultCity = MutableLiveData<Boolean>()
    val city = MutableLiveData<String>()
    val briefInfo = MutableLiveData<Result>()

    fun getLocation() {
        Log.e("mylog", "$latitude $longitude")
        val retrofit = Retrofit.Builder()
            .baseUrl(Reposition.GPSURL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(LocationService::class.java)
        service.getInfo(Reposition.GPSKEY, MyLocation.instance.latitude, MyLocation.instance.longitude, 1)?.
            enqueue(object : Callback<LocationData> {
                override fun onResponse(call: Call<LocationData>, response: Response<LocationData>) {
                    city.value = response.body()?.result?.ext?.city.toString().dropLast(1)
                }
                override fun onFailure(call: Call<LocationData>, t: Throwable) {
                    Log.e("mylog",t.message)
                }
            })

    }

    fun getBriefInfo() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Reposition.BASEURL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(BriefService::class.java)
        service.getInfo(Reposition.KEY, MyLocation.instance.city.value.toString())?.enqueue(
            object : Callback<BriefInfo> {
                override fun onResponse(call: Call<BriefInfo>, response: Response<BriefInfo>) {
                    briefInfo.value = response.body()?.results?.get(0)
                }

                override fun onFailure(call: Call<BriefInfo>, t: Throwable) {
                    Log.e("mylog", t.message)
                }
            }
        )
    }

}
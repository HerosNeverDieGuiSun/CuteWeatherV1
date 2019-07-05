package com.example.cuteweatherv1.ui.briefly

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cuteweatherv1.location.LocationService
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.location.data.LocationData
import com.example.cuteweatherv1.repository.Reposition
import com.github.promeg.pinyinhelper.Pinyin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 时间：2019/7/4 11:14
 * 作者：程弋
 * 描述: 获取天气简略信息
 */

class FragmentWeatherViewModel : ViewModel() {
    private val repository = MyLocation.instance
    val city = MutableLiveData<String>()

    fun getLocation() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apis.juhe.cn/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(LocationService::class.java)
        service.getInfo("755cd948c3e6001929399bff71202268", repository.latitude, repository.longitude, 1)?.
            enqueue(object : Callback<LocationData> {
                override fun onResponse(call: Call<LocationData>, response: Response<LocationData>) {
                    val res = response.body()?.result?.ext?.city.toString().dropLast(1)
                    var pinyin = ""
                    for (c in res.indices) {
                        pinyin += Pinyin.toPinyin(res[c])
                    }
                    Log.e("mylog", "城市 $pinyin")
                    city.value = pinyin
                }

                override fun onFailure(call: Call<LocationData>, t: Throwable) {
                    Log.e("MyLog",t.message)
                }
            })
    }
}

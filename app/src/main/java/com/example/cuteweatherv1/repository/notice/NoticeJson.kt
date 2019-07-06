package com.example.cuteweatherv1.repository.notice

import android.util.Log
import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.repository.notice.data.NoticeData
import com.example.cuteweatherv1.repository.notice.data.SaveNotice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NoticeJson {
    fun deal () {
        val retrofit = Retrofit.Builder()
            .baseUrl(Reposition.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(NoticeServ::class.java)
        service.getinfo(Reposition.KEY,"唐山")
            .enqueue(object : Callback<NoticeData> {
                override fun onFailure(call: Call<NoticeData>, t: Throwable) {
                    Log.e("my","获取数据失败")
                }

                override fun onResponse(call: Call<NoticeData>, response: Response<NoticeData>) {
                    if(response.body()?.results?.get(0)?.alarms!!.size != 0){
                        SaveNotice.instance.description.value = response.body()?.results?.get(0)?.alarms!!.get(0).description
//                        Log.e("my","////////"+SaveNotice.instance.description.value)
                    }

                }

            })
    }
}
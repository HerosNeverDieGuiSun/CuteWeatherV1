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

/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：灾害预警信息api的Json处理函数
 */
class NoticeJson {
    fun deal (position:String) {
        //设置api调用时的位置
        var temp: String = "beijing"
        if (position != "nul"){
            temp = position
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(Reposition.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(NoticeServ::class.java)
        service.getinfo(Reposition.KEY,temp)
            .enqueue(object : Callback<NoticeData> {
                override fun onFailure(call: Call<NoticeData>, t: Throwable) {
                    Log.e("my","获取数据失败")
                }

                override fun onResponse(call: Call<NoticeData>, response: Response<NoticeData>) {
                    if(response.body()?.results?.get(0)?.alarms!!.size != 0){
                        SaveNotice.instance.description.value = response.body()?.results?.get(0)?.alarms!!.get(0).description
                    }

                }

            })
    }
}
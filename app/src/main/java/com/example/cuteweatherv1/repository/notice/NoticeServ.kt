package com.example.cuteweatherv1.repository.notice

import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.repository.notice.data.NoticeData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：灾害预警信息的api接口函数
 */
interface NoticeServ {
    @GET(Reposition.TIP)
    fun getinfo(@Query("key") key:String, @Query("location") location: String): Call<NoticeData>
}
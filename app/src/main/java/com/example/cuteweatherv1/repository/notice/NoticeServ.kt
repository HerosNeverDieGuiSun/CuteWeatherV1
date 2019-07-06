package com.example.cuteweatherv1.repository.notice

import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.repository.notice.data.NoticeData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticeServ {
    @GET(Reposition.TIP)
    fun getinfo(@Query("key") key:String, @Query("location") location: String): Call<NoticeData>
}
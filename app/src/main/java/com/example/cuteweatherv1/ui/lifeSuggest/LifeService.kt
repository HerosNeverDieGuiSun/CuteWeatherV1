package com.example.cuteweatherv1.ui.lifeSuggest

import retrofit2.Call
import com.example.cuteweatherv1.data.lifeSuggestion.LifeSuggestion
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by 钱坤
 * 内容：主页面生活建议模块
 */
interface LifeService {
    @GET("v3/life/suggestion.json")
    fun getInfo(@Query("key") key : String, @Query("location") location : String): Call<LifeSuggestion>

}
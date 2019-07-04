package com.example.cuteweatherv1.ui.lifeSuggest

import retrofit2.Call
import com.example.cuteweatherv1.data.lifeSuggestion.LifeSuggestion
import retrofit2.http.GET
import retrofit2.http.Query


interface LifeService {
    @GET("v3/life/suggestion.json")
    fun getInfo(@Query("key") key : String, @Query("location") location : String): Call<LifeSuggestion>

}
package com.example.cuteweatherv1.repository

import android.location.LocationManager
import com.example.cuteweatherv1.ui.briefly.BriefService
import com.example.cuteweatherv1.ui.briefly.data.BriefInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Reposition {

    object Holder {
        val INSTANCE = Reposition()
    }
    companion object {
        val instance = Holder.INSTANCE
        const val KEY = "SAc5cXnjG7dhZBOf_"
        const val BASEURL = "https://api.seniverse.com/"
        const val AIRDAILY = "v3/air/daily.json"
    }



}
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
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.seniverse.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun getBriefInfo(): BriefInfo? {

        val service =retrofit.create(BriefService::class.java)
        //service.getInfo("SAc5cXnjG7dhZBOf_", )
        return null
    }

}
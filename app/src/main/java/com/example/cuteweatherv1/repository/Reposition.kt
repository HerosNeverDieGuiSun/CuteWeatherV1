package com.example.cuteweatherv1.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cuteweatherv1.location.LocationService
import com.example.cuteweatherv1.location.data.LocationData
import com.example.cuteweatherv1.ui.briefly.BriefService
import com.example.cuteweatherv1.ui.briefly.data.BriefInfo
import com.github.promeg.pinyinhelper.Pinyin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Reposition {
    object Holder {
        val INSTANCE = Reposition()
    }
    companion object {
        val instance = Holder.INSTANCE
    }

}
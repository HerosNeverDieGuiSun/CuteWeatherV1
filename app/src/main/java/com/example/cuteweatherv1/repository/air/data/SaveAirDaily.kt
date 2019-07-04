package com.example.cuteweatherv1.repository.air.data

import android.util.Log
import androidx.lifecycle.MutableLiveData

class SaveAirDaily {
    val dailyList = ArrayList<Daily>(5)
    val airNum = MutableLiveData<Int>()
    val no2 = MutableLiveData<String>()
    val so2 = MutableLiveData<String>()
    val pm25 =MutableLiveData<String>()
    val o3 =MutableLiveData<String>()
    val co = MutableLiveData<String>()
    val pm10 = MutableLiveData<String>()
    fun saveOne(daily: Daily){
        dailyList.add(daily)
    }




//    fun saveOne(aqi:String,co:String,time:String,no2:String,o3:String,pm10:String,pm25:String,quality:String,so2:String){
//        dailyList.add(Daily(aqi,co,time,no2,o3,pm10,pm25,quality,so2))
//    }
    private constructor(){
        //无
    }
    object Holder {
        val INSTANCE = SaveAirDaily()
    }
    companion object {
        val instance: SaveAirDaily = Holder.INSTANCE
    }
}
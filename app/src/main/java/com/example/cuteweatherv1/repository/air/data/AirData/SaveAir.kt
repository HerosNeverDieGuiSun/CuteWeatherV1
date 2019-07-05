package com.example.cuteweatherv1.repository.air.data.AirData

import androidx.lifecycle.MutableLiveData

class SaveAir {
    val airNum = MutableLiveData<Int>()
    val pm10 = MutableLiveData<String>()
    val pm25 = MutableLiveData<String>()
    val time = MutableLiveData<String>()
    private constructor(){
        //无
    }
    object Holder {
        val INSTANCE = SaveAir()
    }
    companion object {
        val instance: SaveAir = Holder.INSTANCE
    }
}
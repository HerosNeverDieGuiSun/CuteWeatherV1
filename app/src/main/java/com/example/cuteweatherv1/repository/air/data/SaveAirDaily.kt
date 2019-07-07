package com.example.cuteweatherv1.repository.air.data

import androidx.lifecycle.MutableLiveData

/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：保存每日空气质量，单例模式
 */
class SaveAirDaily {

    val info = MutableLiveData<ArrayList<String>>()
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
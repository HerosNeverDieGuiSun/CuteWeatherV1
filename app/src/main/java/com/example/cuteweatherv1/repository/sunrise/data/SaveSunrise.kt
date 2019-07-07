package com.example.cuteweatherv1.repository.sunrise.data

import androidx.lifecycle.MutableLiveData

/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：保存日出日落api信息，单例模式
 */
class SaveSunrise {

    val sun = MutableLiveData<ArrayList<String>>()

    private constructor(){
        //无
    }
    object Holder {
        val INSTANCE = SaveSunrise()
    }
    companion object {
        val instance: SaveSunrise = Holder.INSTANCE
    }
}
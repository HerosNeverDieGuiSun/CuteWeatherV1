package com.example.cuteweatherv1.repository.air.data.AirData

import androidx.lifecycle.MutableLiveData
/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：空气质量信息保存
 */
class SaveAir {
    //把信息存储在arrayList中，位置：0->aqi,1->pm10,2->pm2.5,3->time
    val airInfo = MutableLiveData<ArrayList<String>>()

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
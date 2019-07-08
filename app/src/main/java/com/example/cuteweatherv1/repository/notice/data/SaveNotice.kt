package com.example.cuteweatherv1.repository.notice.data

import androidx.lifecycle.MutableLiveData

/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：保存灾害预警信息，单例模式
 */
class SaveNotice {

    val description = MutableLiveData<String>()

    private constructor(){
        //无
    }
    object Holder {
        val INSTANCE = SaveNotice()
    }
    companion object {
        val instance: SaveNotice = Holder.INSTANCE
    }
}
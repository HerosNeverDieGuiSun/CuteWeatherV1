package com.example.cuteweatherv1.repository.notice.data

import androidx.lifecycle.MutableLiveData

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
package com.example.cuteweatherv1.repository.sunrise.data

import androidx.lifecycle.MutableLiveData

class SaveSunrise {

    val sun = MutableLiveData<ArrayList<String>>()
    val sunset = MutableLiveData<String>()

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
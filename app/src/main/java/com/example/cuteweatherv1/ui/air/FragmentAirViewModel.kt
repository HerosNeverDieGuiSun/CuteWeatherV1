package com.example.cuteweatherv1.ui.air

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel;
import com.example.cuteweatherv1.repository.air.data.SaveAirDaily

class FragmentAirViewModel : ViewModel() {

    private val num = SaveAirDaily.instance.airNum
    private val so2 = SaveAirDaily.instance.so2
//    val text: LiveData<String> = Transformations.map(_index) {
//        "Hello world from section: $it"
//    }

    val airNum:LiveData<Int> = Transformations.map(num){
//        SaveAirDaily.instance.dailyList[(it- 1)].aqi.toInt()
        it
    }
    val so2num:LiveData<String> = Transformations.map(SaveAirDaily.instance.so2){
        //        SaveAirDaily.instance.dailyList[(it- 1)].aqi.toInt()
        it
    }
    val conum:LiveData<String> = Transformations.map(SaveAirDaily.instance.co){
        //        SaveAirDaily.instance.dailyList[(it- 1)].aqi.toInt()
        it
    }
    val no2num:LiveData<String> = Transformations.map(SaveAirDaily.instance.no2){
        //        SaveAirDaily.instance.dailyList[(it- 1)].aqi.toInt()
        it
    }
    val o3num:LiveData<String> = Transformations.map(SaveAirDaily.instance.o3){
        //        SaveAirDaily.instance.dailyList[(it- 1)].aqi.toInt()
        it
    }
    val pm10num:LiveData<String> = Transformations.map(SaveAirDaily.instance.pm10){
        //        SaveAirDaily.instance.dailyList[(it- 1)].aqi.toInt()
        it
    }
    val pm25num:LiveData<String> = Transformations.map(SaveAirDaily.instance.pm25){
        //        SaveAirDaily.instance.dailyList[(it- 1)].aqi.toInt()
        it
    }
    fun setIndex(index: Int) {
        num.value = index
    }
}

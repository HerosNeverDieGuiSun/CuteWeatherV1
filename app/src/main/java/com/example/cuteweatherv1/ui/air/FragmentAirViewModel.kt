package com.example.cuteweatherv1.ui.air

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel;
import com.example.cuteweatherv1.repository.air.data.SaveAirDaily

/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：每日空气质量ViewModel
 */
class FragmentAirViewModel : ViewModel() {

    private var num = 0

    //转换函数
    val info:LiveData<ArrayList<String>> = Transformations.map(SaveAirDaily.instance.info){
        it
    }

    fun setIndex(index: Int) {
        num = index
    }

}

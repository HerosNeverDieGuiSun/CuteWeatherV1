package com.example.cuteweatherv1.ui.briefly

import androidx.lifecycle.ViewModel
import com.example.cuteweatherv1.location.MyLocation

/**
 * 时间：2019/7/4 11:14
 * 作者：程弋
 * 描述: 获取天气简略信息
 */

class FragmentWeatherViewModel : ViewModel() {
    private val repository = MyLocation.instance

    fun getLocate() {
        repository.getLocation()
    }

    fun getBriefInfo() {
        repository.getBriefInfo()
    }
}

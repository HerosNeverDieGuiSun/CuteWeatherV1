package com.example.cuteweatherv1.ui.hourly

import androidx.lifecycle.ViewModel;
import com.example.cuteweatherv1.repository.hourly.HourlyOperate

class FragmentHourlyViewModel : ViewModel() {
    private val repository = HourlyOperate.instance

    fun getHourlyInfo() {
        repository.getHourlyInfo()
    }
}

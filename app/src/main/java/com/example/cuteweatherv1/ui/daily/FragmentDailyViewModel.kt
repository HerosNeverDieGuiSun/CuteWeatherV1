package com.example.cuteweatherv1.ui.daily

import androidx.lifecycle.ViewModel;
import com.example.cuteweatherv1.repository.daily.DailyOperate

class FragmentDailyViewModel : ViewModel() {
    private val repository = DailyOperate.instance

    fun getDailyInfo() {
        repository.getDailyData()
    }
}

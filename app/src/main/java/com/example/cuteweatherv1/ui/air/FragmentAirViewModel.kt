package com.example.cuteweatherv1.ui.air

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel;

class FragmentAirViewModel : ViewModel() {
    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    val airNum:LiveData<Int> = Transformations.map(_index){
        it*10+50
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}

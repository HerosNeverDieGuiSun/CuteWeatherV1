package com.example.cuteweatherv1.ui.lifeSuggest

import androidx.lifecycle.ViewModel;
import com.example.cuteweatherv1.repository.lifeSuggestion.SuggestRepositoy
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by 钱坤
 * 内容：主页面生活建议模块
 */

class FragmentLifeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val repository = SuggestRepositoy.instance
    fun getInfo(){
        repository.getSuggestionInfo()
    }

}

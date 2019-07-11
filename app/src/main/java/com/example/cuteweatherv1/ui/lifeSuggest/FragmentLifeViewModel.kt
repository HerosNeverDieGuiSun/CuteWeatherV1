package com.example.cuteweatherv1.ui.lifeSuggest

import androidx.lifecycle.ViewModel;
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by 钱坤
 * 内容：主页面生活建议模块
 */

class FragmentLifeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.seniverse.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val lifeService = retrofit.create(LifeService::class.java)











}

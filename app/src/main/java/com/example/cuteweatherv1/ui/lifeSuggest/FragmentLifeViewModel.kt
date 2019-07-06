package com.example.cuteweatherv1.ui.lifeSuggest

import android.util.Log
import androidx.lifecycle.ViewModel;
import com.example.cuteweatherv1.data.lifeSuggestion.LifeSuggestion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.sax.EndElementListener
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/* 开发者：--钱坤
   内容：主页面生活建议模块
 */

class FragmentLifeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.seniverse.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val lifeService = retrofit.create(LifeService::class.java)











}

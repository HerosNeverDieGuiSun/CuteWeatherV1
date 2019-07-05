package com.example.cuteweatherv1.ui.lifeSuggest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.data.lifeSuggestion.LifeSuggestion
import kotlinx.android.synthetic.main.activity_suggest_info.*
import kotlinx.android.synthetic.main.fragment_life.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuggestInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggest_info)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.seniverse.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val lifeService = retrofit.create(LifeService::class.java)
        lifeService.getInfo("SAc5cXnjG7dhZBOf_","nanjing")
            .enqueue(object : Callback<LifeSuggestion> {
                override fun onFailure(call: Call<LifeSuggestion>, t: Throwable) {
                    Log.e("MyLog","获取数据失败")
                }

                override fun onResponse(call: Call<LifeSuggestion>, response: Response<LifeSuggestion>) {
                    titleWear.text = "穿衣"
                    infoWear.text = response.body()?.results?.get(0)?.suggestion?.dressing?.details!! ?: "无"
                    suggestWear.text = response.body()?.results?.get(0)?.suggestion?.dressing?.brief!! ?: "无"
                }
            })
    }
}

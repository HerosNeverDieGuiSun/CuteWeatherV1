package com.example.cuteweatherv1.ui.lifeSuggest


/**
 * Created by 钱坤
 * 内容：生活建议详细信息页面
 */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.data.lifeSuggestion.LifeSuggestion
import kotlinx.android.synthetic.main.activity_suggest_info.*
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
                    titleBeer.text = "啤酒"
                    infoBeer.text = response.body()?.results?.get(0)?.suggestion?.beer?.details!! ?: "无"
                    suggestBeer.text = response.body()?.results?.get(0)?.suggestion?.beer?.brief!! ?: "无"
                    titleCold.text = "风寒"
                    infoCold.text = response.body()?.results?.get(0)?.suggestion?.chill?.details!! ?: "无"
                    suggestCold.text = response.body()?.results?.get(0)?.suggestion?.chill?.brief!! ?: "无"
                    titleDate.text = "约会"
                    infoDate.text = response.body()?.results?.get(0)?.suggestion?.dating?.details!! ?: "无"
                    suggestDate.text = response.body()?.results?.get(0)?.suggestion?.dating?.brief!! ?: "无"
                    titleFishing.text = "钓鱼"
                    infoFishing.text = response.body()?.results?.get(0)?.suggestion?.fishing?.details!! ?: "无"
                    suggestFishing.text = response.body()?.results?.get(0)?.suggestion?.fishing?.brief!! ?: "无"
                    titleHeart.text = "心情"
                    infoHeart.text = response.body()?.results?.get(0)?.suggestion?.mood?.details!! ?: "无"
                    suggestHeart.text = response.body()?.results?.get(0)?.suggestion?.mood?.brief!! ?: "无"
                    titleNight.text = "夜生活"
                    infoNight.text = response.body()?.results?.get(0)?.suggestion?.night_life?.details!! ?: "无"
                    suggestNight.text = response.body()?.results?.get(0)?.suggestion?.night_life?.brief!! ?: "无"
                    titleShopping.text = "购物"
                    infoShopping.text = response.body()?.results?.get(0)?.suggestion?.shopping?.details!! ?: "无"
                    suggestShopping.text = response.body()?.results?.get(0)?.suggestion?.shopping?.brief!! ?: "无"
                    titleTour.text = "旅游"
                    infoTour.text = response.body()?.results?.get(0)?.suggestion?.travel?.details!! ?: "无"
                    suggestTour.text = response.body()?.results?.get(0)?.suggestion?.travel?.brief!! ?: "无"
                    titleTranslate.text = "交通"
                    infoTranslate.text = response.body()?.results?.get(0)?.suggestion?.traffic?.details!! ?: "无"
                    suggestTranslate.text = response.body()?.results?.get(0)?.suggestion?.traffic?.brief!! ?: "无"



                }
            })
    }
}

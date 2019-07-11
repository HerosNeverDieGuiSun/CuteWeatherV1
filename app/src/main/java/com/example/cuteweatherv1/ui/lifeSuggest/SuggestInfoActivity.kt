package com.example.cuteweatherv1.ui.lifeSuggest


/**
 * Created by 钱坤
 * 内容：生活建议详细信息页面
 */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.adapter.hourly.HourlyAdapter
import com.example.cuteweatherv1.adapter.suggestion.SuggestionAdapter
import com.example.cuteweatherv1.adapter.suggestion.SuggestionItemClickListener
import com.example.cuteweatherv1.repository.lifeSuggestion.LifeSuggestions
import com.example.cuteweatherv1.repository.lifeSuggestion.data.LifeSuggestion
import kotlinx.android.synthetic.main.activity_suggest_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SuggestInfoActivity : AppCompatActivity() {
    lateinit var suggestionAdapter:SuggestionAdapter

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
                    val data =  ArrayList<LifeSuggestions>()
                    data.add(LifeSuggestions(R.drawable.wear3,"穿衣",
                        response.body()?.results?.get(0)?.suggestion?.dressing?.brief!! ?: "无",
                        response.body()?.results?.get(0)?.suggestion?.dressing?.details!! ?: "无"))
                    data.add(LifeSuggestions(R.drawable.beer3,"啤酒",
                        response.body()?.results?.get(0)?.suggestion?.beer?.brief!! ?: "无",
                        response.body()?.results?.get(0)?.suggestion?.beer?.details!! ?: "无"))
                    data.add(LifeSuggestions(R.drawable.cold3,"风寒",
                        response.body()?.results?.get(0)?.suggestion?.chill?.brief!! ?: "无",
                        response.body()?.results?.get(0)?.suggestion?.chill?.details!! ?: "无"))
                    data.add(LifeSuggestions(R.drawable.date3,"约会",
                        response.body()?.results?.get(0)?.suggestion?.dating?.brief!! ?: "无",
                        response.body()?.results?.get(0)?.suggestion?.dating?.details!! ?: "无"))
                    data.add(LifeSuggestions(R.drawable.fishing3,"钓鱼",
                        response.body()?.results?.get(0)?.suggestion?.fishing?.brief!! ?: "无",
                        response.body()?.results?.get(0)?.suggestion?.fishing?.details!! ?: "无"))
                    data.add(LifeSuggestions(R.drawable.heart3,"心情",
                        response.body()?.results?.get(0)?.suggestion?.mood?.brief!! ?: "无",
                        response.body()?.results?.get(0)?.suggestion?.mood?.details!! ?: "无"))
                    data.add(LifeSuggestions(R.drawable.night3,"夜生活",
                        response.body()?.results?.get(0)?.suggestion?.night_life?.brief!! ?: "无",
                        response.body()?.results?.get(0)?.suggestion?.night_life?.details!! ?: "无"))
                    data.add(LifeSuggestions(R.drawable.shopping3,"购物",
                        response.body()?.results?.get(0)?.suggestion?.shopping?.brief!! ?: "无",
                        response.body()?.results?.get(0)?.suggestion?.shopping?.details!! ?: "无"))
                    data.add(LifeSuggestions(R.drawable.tour3,"旅游",
                        response.body()?.results?.get(0)?.suggestion?.travel?.brief!! ?: "无",
                        response.body()?.results?.get(0)?.suggestion?.travel?.details!! ?: "无"))
                    data.add(LifeSuggestions(R.drawable.translate3,"交通",
                        response.body()?.results?.get(0)?.suggestion?.traffic?.brief!! ?: "无",
                        response.body()?.results?.get(0)?.suggestion?.traffic?.details!! ?: "无"))
                    suggestionAdapter = SuggestionAdapter(applicationContext, R.layout.suggestion_item, data)
                    suggestRV.adapter = suggestionAdapter

                }
            })
    }
}
